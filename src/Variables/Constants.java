package Variables;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Constants {
    private static final String m_Token = "NDgwMTEwMTc2MjM4NTY3NDc0.DljkDg.i1M4lY81PuBbrIGkaQmVB7zqMrk";
    private static final String m_Prefix = "b1g.";

    public static String token()
    {
        return m_Token;
    }
    public static String prefix(){
        return m_Prefix;
    }
}
