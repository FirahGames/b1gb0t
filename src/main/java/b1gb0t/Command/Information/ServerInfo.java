package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class ServerInfo extends AbstractCommand {
    public ServerInfo(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "serverinfo";
    }

    @Override
    public String commandUsage() {
        return "serverinfo";
    }

    @Override
    public String commandDescription() {
        return "Finds info about the current server.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"thisserver", "serveri", "infoserver"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setThumbnail(guild.getIconUrl());
        embedBuilder.setAuthor("Guild: " + guild.getName(), "https://discordapp.com", guild.getIconUrl());
        embedBuilder.addField("ID", guild.getId(), true);
        embedBuilder.addField("Region", guild.getRegion().getEmoji() + guild.getRegion().getName(), true);
        embedBuilder.addField("Owner", guild.getOwner().getEffectiveName() + "#" + guild.getOwner().getUser().getDiscriminator(), true);
        embedBuilder.addField("Creation Date", guild.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
        embedBuilder.addField("Security", guild.getVerificationLevel().toString(), true);
        embedBuilder.addField("Channel Count", guild.getTextChannels().size() + guild.getVoiceChannels().size() + "", true);
        embedBuilder.addField("Role Count", guild.getRoles().size() + "", true);
        embedBuilder.addField("Members Count", guild.getMembers().size() + "", true);
        embedBuilder.addField("Emoji Count", guild.getEmotes().size() + "", true);
        embedBuilder.addField("Default Channel", guild.getDefaultChannel().getAsMention(), true);
        embedBuilder.addField("System Channel", guild.getSystemChannel().getAsMention(), true);
        embedBuilder.addField("AFK Timeout", guild.getAfkTimeout().getSeconds() / 60 + " Minutes", true);
        event.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
