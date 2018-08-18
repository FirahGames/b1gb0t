package Command.Utility;

import Command.Command;
import Connection.Connection;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Ping extends Command {
    @Override
    public String cmdName() {
        return "ping";
    }

    @Override
    public String cmdDesc() {
        return "Returns ping time.";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "ping");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String raw, User author, Guild server, String... args) {
        event.getChannel().sendMessage(":ping_pong: Pong! " + event.getJDA().getPing() + "ms").queue();
    }
}
