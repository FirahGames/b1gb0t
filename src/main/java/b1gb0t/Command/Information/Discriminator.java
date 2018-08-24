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
import java.util.ArrayList;
import java.util.List;

public class Discriminator extends AbstractCommand {
    public Discriminator() { super(); }

    @Override
    public String commandName() {
        return "discriminator";
    }

    @Override
    public String commandUsage() {
        return "discriminator (4 digits || user)";
    }

    @Override
    public String commandDescription() {
        return "Returns users with the same discriminator.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"discrim", "dscrim", "discnator", "getdiscrim"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        String discrim;
        if (args.length == 1 && args[0].length() == 4)
            discrim = args[0];
        else if (!message.getMentionedUsers().isEmpty())
            discrim = message.getMentionedUsers().get(0).getDiscriminator();
        else
            discrim = author.getDiscriminator();

        List<User> discrimMatches = new ArrayList<>();
        EmbedBuilder eBuilder = new EmbedBuilder();
        eBuilder.setAuthor("Discriminator: #" + discrim);
        eBuilder.setColor(BotVars.color());
        eBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        eBuilder.setTimestamp(Instant.now());
        event.getJDA().getUsers().forEach(m -> {
            if (m.getDiscriminator().equals(discrim) && discrimMatches.size() <= 10)
                discrimMatches.add(m);
        });
        discrimMatches.forEach(m -> {
            eBuilder.addField(m.getName() + "#" + m.getDiscriminator(), m.getId(), false);
        });
        message.getTextChannel().sendMessage(eBuilder.build()).queue();
    }
}
