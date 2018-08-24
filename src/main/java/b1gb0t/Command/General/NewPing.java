package b1gb0t.Command.General;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.temporal.ChronoUnit;

public class NewPing extends AbstractCommand {
    public NewPing() { super(); }
    @Override
    public String commandDescription() {
        return "Returns the ping.";
    }

    @Override
    public String commandUsage() {
        return "ping";
    }

    @Override
    public String commandName() {
        return "ping";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"bing", "pong", "peng", "pung"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        String vowel = "aeiou".charAt((int)(Math.random()*5))+"";
        event.getChannel().sendMessage(":ping_pong: P" + vowel + "ng!\nWebsocket: " + event.getJDA().getPing() + "ms").queue(m -> m.editMessage(m.getContentRaw() + "\nREST API: " + message.getCreationTime().until(m.getCreationTime(), ChronoUnit.MILLIS) + "ms").queue());
    }
}
