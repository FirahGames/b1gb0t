package Handlers;

import Command.Command;
import net.dv8tion.jda.core.entities.MessageType;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CommandHandler {
    private Vector<Command> m_Commands = new Vector<Command>();

    public void regCommand(Command cmd) {
        m_Commands.add(cmd);
    }


    public Vector<Command> registeredCmds() {
        return m_Commands;
    }

    void handleMessage(GuildMessageReceivedEvent event) {
        if(event.getMessage().getType() != MessageType.DEFAULT)return;
        String[] args = event.getMessage().getContentRaw().split(" ");
        registeredCmds().forEach(cmd ->{
            List<String> aliases = new ArrayList<>(cmd.cmdNames());
            aliases.add(cmd.cmdName());
            aliases.forEach(a -> {
                if (args[0].equalsIgnoreCase(a))
                    cmd.cmdFunc(event, event.getMessage(), event.getMessage().getContentRaw(), event.getAuthor(), event.getGuild(), args);
            });
        });
    }
}
