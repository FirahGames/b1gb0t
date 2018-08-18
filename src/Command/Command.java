package Command;

import java.util.Collections;
import java.util.List;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Command {
    public Command() {
    }

    public String cmdName() {
        return null;
    }

    public String cmdDesc() {
        return null;
    }

    public List<String> cmdNames() {
        return Collections.emptyList();
    }

    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String m, User author, Guild server, String... args) {
    }
}
