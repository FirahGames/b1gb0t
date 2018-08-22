package b1gb0t.Command.Owner;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.WriterConfig;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SetGame extends AbstractCommand {
    public SetGame(){
        category = CommandCat.CREATOR;
    }
    private void resaveJSON(String name) {
        var user = Json.object().add("prefix", BotVars.prefix()).add("id", BotVars.ownerId()).add("token", BotVars.token()).add("image", BotVars.image()).add("game", name);
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
        return "setgame";
    }

    @Override
    public String commandUsage() {
        return "setgame <watching || playing || listening> <game>";
    }

    @Override
    public String commandDescription() {
        return "Sets the bots game.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"listening", "watching", "playing"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {

            if(!message.getAuthor().getId().equals(BotVars.ownerId())) {
                event.getChannel().sendMessage("You aren't the owner!\n Owner ID: " + event.getJDA().getUserById(BotVars.ownerId()).getId() + "\nYour ID: " + message.getAuthor().getId()).queue();
                return;
            }
            if(args.length < 2) {
                event.getChannel().sendMessage("Missing arguments!").queue();
                return;
            }
            String[] game = Arrays.copyOfRange(args, 1, args.length);
            if(args[0].equalsIgnoreCase("listening")){
                event.getJDA().getPresence().setGame(Game.listening(String.join(" ", game)));
                event.getChannel().sendMessage("Set game to **Listening to " + String.join(" ",game) + "**").queue();
            }
            else if(args[0].equalsIgnoreCase("watching")){
                event.getJDA().getPresence().setGame(Game.watching(String.join(" ", game)));
                event.getChannel().sendMessage("Set game to **Watching " + String.join(" ",game) + "**").queue();
            }
            else if(args[0].equalsIgnoreCase("playing")){
                event.getJDA().getPresence().setGame(Game.playing(String.join(" ", game)));
                event.getChannel().sendMessage("Set game to **Playing " + String.join(" ",game) + "**").queue();
            }
            resaveJSON(String.join(" ", args));

    }
}
