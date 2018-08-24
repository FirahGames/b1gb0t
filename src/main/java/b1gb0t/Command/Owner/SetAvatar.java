package b1gb0t.Command.Owner;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Util.ImageUtil;
import b1gb0t.Variables.BotVars;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.WriterConfig;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class SetAvatar extends AbstractCommand {
    public SetAvatar(){ super(); }
    private void resaveJSON(String name) {
        var user = Json.object().add("prefix", BotVars.prefix()).add("id", BotVars.ownerId()).add("token", BotVars.token()).add("image", name).add("game", BotVars.game());
        String json = user.toString(WriterConfig.PRETTY_PRINT);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("config.json"));
            writer.write(json);
            writer.close();
            BotVars.setImage(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String commandName() {
        return "setimage";
    }

    @Override
    public String commandUsage() {
        return "setimage <image url>";
    }

    @Override
    public String commandDescription() {
        return "Sets the bot's avatar.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"setavi", "setavatar", "botavatar"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        if (!message.getAuthor().getId().equals(BotVars.ownerId())) {

            event.getChannel().sendMessage("You aren't the owner!\n Owner ID: " + event.getJDA().getUserById(BotVars.ownerId()).getId() + "\nYour ID: " + message.getAuthor().getId()).queue();
            return;
        }
        if(!message.getAttachments().isEmpty()) {
            try {
                InputStream s = ImageUtil.imageFromUrl(message.getAttachments().get(0).getUrl());
                if (s == null) {
                    event.getChannel().sendMessage("Invalid URL! ").queue();

                }
                event.getJDA().getSelfUser().getManager().setAvatar(Icon.from(s)).queue(
                        v -> event.getChannel().sendMessage("Changed AVI!").queue(),
                        t -> event.getChannel().sendMessage("Failed to change AVI!").queue());
                resaveJSON(message.getAttachments().get(0).getUrl());

            } catch (IOException e) {
                event.getChannel().sendMessage("Failed to change AVI! Error:"  + e.getMessage()).queue();
            }
        }
        else if (args.length == 1) {
            InputStream s = ImageUtil.imageFromUrl(String.join(" ", args));
            if (s == null) {
                event.getChannel().sendMessage("Invalid URL! ").queue();

            } else {
                try {

                    event.getJDA().getSelfUser().getManager().setAvatar(Icon.from(s)).queue(
                            v -> event.getChannel().sendMessage("Changed AVI!").queue(),
                            t -> event.getChannel().sendMessage("Failed to change AVI!").queue());
                    resaveJSON(args[0]);

                } catch (IOException e) {
                    event.getChannel().sendMessage("Failed to change AVI! Error:"  + e.getMessage()).queue();
                }
            }
        }
    }
}
