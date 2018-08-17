package Handlers;

import Command.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Vector;

public class CommandHandler {
    private Vector<Command> m_Commands = new Vector<Command>();
    public void regCommand(Command cmd){
        m_Commands.add(cmd);
    }
    public void handleMessage(GuildMessageReceivedEvent event){

    }
}
