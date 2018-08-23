package b1gb0t.Command.Core;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class Invite extends AbstractCommand {
    public Invite(){
        category= CommandCat.CORE;

    }

    @Override
    public String commandName() {
        return "invite";
    }

    @Override
    public String commandUsage() {
        return "invite";
    }

    @Override
    public String commandDescription() {
        return "Sends a link to invite the bot to your server!";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{ "invitebot", "botinvite" };
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setAuthor("Invitation", event.getJDA().asBot().getInviteUrl(Permission.ADMINISTRATOR), BotVars.image());
        event.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
