package Command.Core;

import Command.Command;
import Connection.Connection;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Help extends Command {
    @Override
    public String cmdName() {
        return "help";
    }

    @Override
    public String cmdDesc() {
        return "Displays available commands";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "help", Constants.prefix() + "?", Constants.prefix() + "cmds", Constants.prefix() + "commands");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String m, User author, Guild server, String... args) {
        HashMap<String, List<Command>> categories = new HashMap<>();
        Connection.getCommandHandler().registeredCmds().forEach(c ->{
            Class<? extends Command> clazz = c.getClass();
            String category = clazz.getPackage().getName();
            category = category.split("\\.")[category.split("\\.").length-1];
            List<Command> edit = categories.get(category);
            if (edit == null)
                edit = new ArrayList<>();
            edit.add(c);
            categories.put(category, edit);
        });
        EmbedBuilder eBuilder = new EmbedBuilder();
        eBuilder.setTitle("b1g b0t help");
        eBuilder.setThumbnail(Constants.image());
        eBuilder.setColor(Color.GREEN);
        categories.forEach((cat, cmds)->{
            StringBuilder line = new StringBuilder();
            cmds.forEach(cmd->{
                line.append("`").append(cmd.cmdName()).append("` - " ).append(cmd.cmdDesc()).append("\n");
            });
            eBuilder.addField(cat.toString(), line.toString(), false);

        });
        event.getChannel().sendMessage(eBuilder.build()).queue();
    }
}
