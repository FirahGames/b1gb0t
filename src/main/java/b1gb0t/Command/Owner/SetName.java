package b1gb0t.Command.Owner;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class SetName  extends AbstractCommand {
    public SetName(){
        category = CommandCat.CREATOR;
    }

    @Override
    public String commandName() {
        return "setname";
    }

    @Override
    public String commandUsage() {
        return "setname <name>";
    }

    @Override
    public String commandDescription() {
        return "Sets the bots name.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{ "botname", "corename"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        if(!message.getAuthor().getId().equals(BotVars.ownerId())) {
            event.getChannel().sendMessage("You aren't the owner!\n Owner ID: " + event.getJDA().getUserById(BotVars.ownerId()).getId() + "\nYour ID: " + message.getAuthor().getId()).queue();
            return;
        }
        event.getJDA().getSelfUser().getManager().setName(String.join(" ", args)).queue();
        event.getChannel().sendMessage("Set the bot's name to **" + String.join(" ", args) + "**").queue();

    }
}
