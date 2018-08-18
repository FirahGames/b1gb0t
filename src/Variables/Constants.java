package Variables;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Constants {
    private static final String m_Token = "smh my head";
    private static final String m_Prefix = "b1g.";
    private static final String m_Image = "https://cdn.discordapp.com/emojis/465450499278372865.png?v=1";
    public static String token()
    {
        return m_Token;
    }
    public static String prefix(){
        return m_Prefix;
    }
    public static String image(){
        return m_Image;
    }
}
