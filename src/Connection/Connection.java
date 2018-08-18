package Connection;

import Command.Utility.Info;
import Command.Utility.Ping;
import Handlers.CommandHandler;
import Handlers.EventHandler;
import Variables.Constants;
import Command.Core.Help;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import javax.security.auth.login.LoginException;

public class Connection {

    private static JDA m_Bot;
    private static CommandHandler m_Commands = new CommandHandler();
    public static CommandHandler getCommandHandler()
    {
        return m_Commands;
    }

    public static long getPing(){
        return m_Bot.getPing();
    }
    public static void main(String[] args){

        JDA discord = null;
        try {
            regCommands();
            discord = new JDABuilder(AccountType.BOT).setToken(Constants.token()).setGame(Game.watching("DireDan fail")).addEventListener(new EventHandler()).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void regCommands(){
        m_Commands.regCommand(new Help());
        m_Commands.regCommand(new Ping());
        m_Commands.regCommand(new Info());
    }
}
