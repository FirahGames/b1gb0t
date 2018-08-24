package b1gb0t.Command.Core;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class Shutdown extends AbstractCommand {
    public Shutdown() { super(); }
    @Override
    public String commandName() {
        return "shutdown";
    }

    @Override
    public String commandUsage() {
        return "shutdown";
    }

    @Override
    public String commandDescription() {
        return "Makes the bot shut down.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{ "shutoff", "poweroff" , "\uD83D\uDD0C", "stop"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setAuthor("Shutting Down", null, BotVars.image());
        embedBuilder.setDescription("Bye! :wave:");
        event.getChannel().sendMessage(embedBuilder.build()).queue();
        event.getJDA().shutdown();
        System.exit(0);
    }
}
