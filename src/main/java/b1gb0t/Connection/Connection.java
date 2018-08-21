package b1gb0t.Connection;

import b1gb0t.Handlers.CommandHandler;
import b1gb0t.Handlers.EventHandler;
import b1gb0t.Variables.Constants;
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
            m_Commands.loadCommands();
            discord = new JDABuilder(AccountType.BOT).setToken(Constants.token()).setGame(Game.listening("to b1g mus1c")).addEventListener(new EventHandler()).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
