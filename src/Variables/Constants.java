package Variables;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Constants {
    private final String m_Token = "R E D A C T E D";
    private final String m_Prefix = "b1g.";

    public String token()
    {
        return m_Token;
    }
    public String prefix(){
        return m_Prefix;
    }
}
