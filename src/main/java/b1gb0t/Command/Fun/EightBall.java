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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EightBall extends AbstractCommand {
    public EightBall() { super(); }
    @Override
    public String commandName() {
        return "8ball";
    }

    @Override
    public String commandUsage() {
        return "8ball <question>";
    }

    @Override
    public String commandDescription() {
        return "Because random answers solve all of lifes questions.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"8", "eight", "ball", "eightball"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        List<String> responses = new ArrayList<>();
        responses.add("It is certain.");
        responses.add("It is decidedly so.");
        responses.add("Without a doubt.");
        responses.add("Yes - definitely.");
        responses.add("You may rely on it.");
        responses.add("As I see it, yes.");
        responses.add("Most likely.");
        responses.add("Outlook good.");
        responses.add("Yes.");
        responses.add("Signs point to yes.");
        responses.add("Reply hazy, try again.");
        responses.add("Ask again later.");
        responses.add("Better not tell you now.");
        responses.add("Concentrate and ask again.");
        responses.add("Don't count on it.");
        responses.add("Concentrate and ask again.");
        responses.add("My reply is no.");
        responses.add("My sources say no.");
        responses.add("Very doubtful.");
        Random rand = new Random();
        var response = responses.get(rand.nextInt(responses.size()));

        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Question: " + String.join(" ", args), null, "https://www.horoscope.com/images-US/games/game-magic-8-ball-no-text.png");
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setThumbnail("https://www.horoscope.com/images-US/games/game-magic-8-ball-no-text.png");
        embedBuilder.setDescription(response);
        event.getChannel().sendMessage(embedBuilder.build()).queue();

    }
}
