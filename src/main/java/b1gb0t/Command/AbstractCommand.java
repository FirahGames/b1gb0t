package b1gb0t.Command;

import b1gb0t.Enums.CommandCat;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public abstract class AbstractCommand {
    public AbstractCommand() {}
    protected CommandCat category = CommandCat.UNKNOWN;
    public abstract String commandName();
    public abstract String commandUsage();
    public abstract String commandDescription();
    public abstract String[] commandAliases();
    public abstract void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String args[]);
    public final CommandCat getCommandCategory() {
        return category;
    }
    public void setCommandCategory(CommandCat newCategory) {
        category = newCategory;
    }

}
