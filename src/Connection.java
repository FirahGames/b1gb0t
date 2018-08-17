import Handlers.CommandHandler;
import Handlers.EventHandler;
import Variables.Constants;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Connection {
    private static JDA m_Bot;
    private static CommandHandler m_Commands;
    public static void main(String[] args){
        JDA discord = null;
        try {
            discord = new JDABuilder(AccountType.BOT).setToken(Constants.token()).setGame(Game.watching("DireDan fail")).addEventListener(new EventHandler()).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
