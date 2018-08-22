package b1gb0t.Command.Owner;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.WriterConfig;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SetPrefix extends AbstractCommand {
    public SetPrefix(){
        category = CommandCat.CREATOR;
    }
    private void resaveJSON(String name) {
        var user = Json.object().add("prefix", name).add("id", BotVars.ownerId()).add("token", BotVars.token()).add("image", BotVars.image()).add("game", BotVars.game());
        String json = user.toString(WriterConfig.PRETTY_PRINT);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("config.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String commandName() {
        return "setprefix";
    }

    @Override
    public String commandUsage() {
        return "setprefix <prefix>";
    }

    @Override
    public String commandDescription() {
        return "Sets the bots prefix";
    }

    @Override
    public String[] commandAliases() {
        return new String[] { "prefix", "botprefix" };
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        if(!message.getAuthor().getId().equals(BotVars.ownerId())) {
            event.getChannel().sendMessage("You aren't the owner!\n Owner ID: " + event.getJDA().getUserById(BotVars.ownerId()).getId() + "\nYour ID: " + message.getAuthor().getId()).queue();
            return;
        }
        BotVars.setPrefix(String.join(" ", args));
        resaveJSON(String.join(" ", args));

    }
}
