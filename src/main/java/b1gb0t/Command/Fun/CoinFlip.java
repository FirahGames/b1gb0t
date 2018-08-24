package b1gb0t.Command.Fun;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.util.Random;

public class CoinFlip extends AbstractCommand {
    public CoinFlip() { super();}

    @Override
    public String commandName() {
        return "coinflip";
    }

    @Override
    public String commandUsage() {
        return "coinflip";
    }

    @Override
    public String commandDescription() {
        return "Flips a coin.";
    }

    @Override
    public String[] commandAliases() {
        return new String[] { "coin", "flip", "tails", "heads" };
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        var rand = new Random();
        var res = rand.nextInt(2);

        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Flip!", null, "https://media1.tenor.com/images/938e1fc4fcf2e136855fd0e83b1e8a5f/tenor.gif?itemid=5017733");
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        if (res >= 1) {
            embedBuilder.setImage("https://upload.wikimedia.org/wikipedia/commons/2/28/98_quarter_obverse.png");
            embedBuilder.setDescription("Heads!");
        }
        else
        {
            embedBuilder.setImage("https://upload.wikimedia.org/wikipedia/commons/5/5a/98_quarter_reverse.png");
            embedBuilder.setDescription("Tails!");
        }
        event.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
