package b1gb0t.Command.Core;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Connection.Connection;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Help extends AbstractCommand {
    public Help(){
        category = CommandCat.CORE;
    }
    @Override
    public String commandName() {
        return "help";
    }

    @Override
    public String commandUsage() {
        return "help (Command)";
    }

    @Override
    public String commandDescription() {
        return "Gives you command information.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{ "commands" };
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        Map<CommandCat, List<AbstractCommand>> categories = new HashMap<>() {
        };
        var reflections = new Reflections("b1gb0t.Command");
        var classes = reflections.getSubTypesOf(AbstractCommand.class);
        for (Class<? extends AbstractCommand> s : classes) {
            try {
                if (Modifier.isAbstract(s.getModifiers())) {
                    continue;
                }
                var packageName = s.getPackage().getName();
                var c = s.getConstructor().newInstance();
                if (!categories.containsKey(c.getCommandCategory())) {
                    categories.put(c.getCommandCategory(), new ArrayList<>());
                }
                categories.get(c.getCommandCategory()).add(c);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if(args.length < 1) {

            var embedBuilder = new EmbedBuilder();
            embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
            embedBuilder.setTitle("b1g help");
            embedBuilder.setThumbnail(BotVars.image());
            embedBuilder.setColor(BotVars.color());
            categories.forEach((cat, list) -> {
                        StringBuilder fieldString = new StringBuilder();
                        list.forEach(command -> {
                            if(command == list.get(0))
                                fieldString.append("``" + command.commandName() + "``");
                            else
                                fieldString.append(", ``" + command.commandName() + "``");
                        });
                        embedBuilder.addField(cat.getEmoticon() + cat.getDisplayName(), fieldString.toString(), false);
                    }
            );
            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }
        else if (args.length == 1){
            var embedBuilder = new EmbedBuilder();
            embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
            AbstractCommand cmd = null;
            if(Connection.getCommandHandler().getCommand(args[0]) != null)
                cmd = Connection.getCommandHandler().getCommand(args[0]);
            if(cmd != null){
                embedBuilder.setColor(BotVars.color());
                embedBuilder.setTitle("Command: " + cmd.commandName());
                embedBuilder.setAuthor("b1g.help");

                embedBuilder.addField("Name", cmd.commandName(), true);
                embedBuilder.addField("Description", cmd.commandDescription(), true);
                embedBuilder.addField("Usage", "``" + BotVars.prefix() + cmd.commandUsage() + "``", true);
                embedBuilder.addField("Category",   cmd.getCommandCategory().getEmoticon() + cmd.getCommandCategory().getDisplayName(), true);

                StringBuilder aliases = new StringBuilder();
                for (String s: cmd.commandAliases()) {
                    if(s == cmd.commandAliases()[0])
                        aliases.append(s);
                    else
                        aliases.append(", " + s);
                }
                embedBuilder.addField("Aliases",   aliases.toString(), true);
                embedBuilder.addBlankField(true);
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}
