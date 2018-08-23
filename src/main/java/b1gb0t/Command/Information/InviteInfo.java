package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InviteInfo extends AbstractCommand {
    public InviteInfo(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "inviteinfo";
    }

    @Override
    public String commandUsage() {
        return "inviteinfo <invite>";
    }

    @Override
    public String commandDescription() {
        return "Resolves an invite and extracts info from it.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"iinfo", "invitatoninfo"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {

        if(args.length <= 1)
        {
            Invite inv;
            if(args[0].startsWith("https://discord.gg/"))
                args[0] = args[0].replaceAll("https://discord.gg/", "");
            inv = Invite.resolve(event.getJDA(), args[0], true).complete();

            var embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(BotVars.color());
            embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
            embedBuilder.setTimestamp(Instant.now());
            embedBuilder.setThumbnail(inv.getGuild().getIconUrl());
            embedBuilder.setAuthor("Invite: " + inv.getGuild().getName(), null, inv.getGuild().getIconUrl());
            embedBuilder.addField("Link", inv.getURL(), true);
            embedBuilder.addField("Code", inv.getCode(), true);
            embedBuilder.addField("Guild", inv.getGuild().getName(), true);
            embedBuilder.addField("Guild ID", inv.getGuild().getId(), true);
            embedBuilder.addField("Channel", "#" + inv.getChannel().getName(), true);
            embedBuilder.addField("Channel ID", inv.getChannel().getId(), true);
            embedBuilder.addField("Inviter", inv.getInviter().getName() + "#" + inv.getInviter().getDiscriminator(), true);
            embedBuilder.addField("Inviter ID", inv.getInviter().getId(), true);
            embedBuilder.addField("Member Count", ":frog: " + inv.getGuild().getOnlineCount() + " Online | :new_moon: " + inv.getGuild().getMemberCount() + " Total", true);
            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

}
