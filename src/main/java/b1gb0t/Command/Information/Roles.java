package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class Roles extends AbstractCommand {
    public Roles(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "roles";
    }

    @Override
    public String commandUsage() {
        return "roles";
    }

    @Override
    public String commandDescription() {
        return "Lists the server's roles with basic info";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"serverroles", "getroles"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(guild.getRoles().size() + " Roles in " + guild.getName(), null, guild.getIconUrl());
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        StringBuilder roles = new StringBuilder();
        guild.getRoles().forEach(role->{
            roles.append(role.getAsMention() + " -- **ID**: " + role.getId() + "\n");
        });
        embedBuilder.setDescription(roles.toString());
        event.getChannel().sendMessage(embedBuilder.build()).queue();

    }
}
