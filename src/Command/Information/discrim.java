package Command.Information;

import Command.Command;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class discrim extends Command {
    @Override
    public String cmdName() {
        return "discrim";
    }

    @Override
    public String cmdDesc() {
        return "Shows users with the same discrim as you.";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "discrim", Constants.prefix() + "discriminator");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String raw, User author, Guild server, String... args) {
        String lmao;
        if (args.length > 1 && args[1].length() == 4)
            lmao = args[1];
        else if (!msg.getMentionedUsers().isEmpty())
            lmao = msg.getMentionedUsers().get(0).getDiscriminator();
        else
            lmao = author.getDiscriminator();

        List<User> discrimMatches = new ArrayList<>();
        EmbedBuilder eBuilder = new EmbedBuilder();
        eBuilder.setAuthor("Discriminator: #" + lmao);
        eBuilder.setColor(Constants.color());
        event.getJDA().getUsers().forEach(m -> {
            if (m.getDiscriminator().equals(lmao) && discrimMatches.size() <= 10)
                discrimMatches.add(m);
        });
        discrimMatches.forEach(m -> {
            eBuilder.addField(m.getName() + "#" + m.getDiscriminator(), m.getId(), false);
        });
        msg.getTextChannel().sendMessage(eBuilder.build()).queue();
    }
}
