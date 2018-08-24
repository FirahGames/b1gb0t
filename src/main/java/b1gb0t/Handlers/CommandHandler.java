package b1gb0t.Handlers;

import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;

public class CommandHandler {
    private HashMap<String, AbstractCommand> m_CommandMap = new HashMap<>();
    private HashMap<String, AbstractCommand> m_Aliases = new HashMap<>();
    HashMap<String, AbstractCommand> commands(){
        return m_CommandMap;
    }
    private void loadAliases() {
        for (AbstractCommand command : m_CommandMap.values()) {
            for (String alias : command.commandAliases()) {
                if (!m_Aliases.containsKey(alias)) {
                    m_Aliases.put(alias, command);
                } else {
                    System.out.println("[WARNING] Duplicate Alias found! The commands " + command.commandName() + " and " + m_Aliases.get(alias) + " have the same alias");
                }
            }
        }
    }
    public boolean isCommand(String msg) {
        return msg.startsWith(BotVars.prefix());
    }

    public void loadCommands() {

        Reflections reflections = new Reflections("b1gb0t.Command");
        var classes = reflections.getSubTypesOf(AbstractCommand.class);
        for (Class<? extends AbstractCommand> s : classes) {
            try {
                if (Modifier.isAbstract(s.getModifiers())) {
                    continue;
                }
                var packageName = s.getPackage().getName();
                var c = s.getConstructor().newInstance();
                c.setCommandCategory(CommandCat.fromPackage(packageName.substring(packageName.lastIndexOf(".") + 1)));
                if (!m_CommandMap.containsKey(c.commandName())) {
                    m_CommandMap.put(c.commandName(), c);
                }
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
            {
                e.printStackTrace();
            }
        }

        loadAliases();
    }
    public AbstractCommand getCommand(String commandHelp){
        AbstractCommand command = null;
        if (commandHelp.startsWith(BotVars.prefix()))
            commandHelp = commandHelp.replace(BotVars.prefix(), "").trim();
        if (m_CommandMap.containsKey(commandHelp) || m_Aliases.containsKey(commandHelp)) {
            command = m_CommandMap.containsKey(commandHelp) ? m_CommandMap.get(commandHelp) : m_Aliases.get(commandHelp);
        }

        return command;
    }
    public void processMessage(GuildMessageReceivedEvent event, Message message) {
        var rawMessage = message.getContentRaw();
        if (rawMessage.startsWith(BotVars.prefix()))
            rawMessage = rawMessage.substring(BotVars.prefix().length()).trim();
        else {
            return;
        }
        var input = rawMessage.split("\\s+", 2);
        String[] args;
        if (input.length == 2) {
            args = input[1].split(" +");
        } else {
            args = new String[0];
        }
            if (m_CommandMap.containsKey(input[0]) || m_Aliases.containsKey(input[0])) {
                AbstractCommand command = m_CommandMap.containsKey(input[0]) ? m_CommandMap.get(input[0]) : m_Aliases.get(input[0]);
                if(command.commandName() != "ping")
                    event.getChannel().sendTyping().complete();
                command.commandFunction(event, event.getGuild(), event.getChannel(), event.getAuthor(), message, message.getContentRaw(), args);
            }
    }
}
