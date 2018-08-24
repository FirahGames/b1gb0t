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

public class RandomNumber extends AbstractCommand {
    public RandomNumber () { super(); }

    @Override
    public String commandName() {
        return "random";
    }

    @Override
    public String commandUsage() {
        return "random (min) (max)";
    }

    @Override
    public String commandDescription() {
        return "Generates a random number.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"randomnumber", "random#", "#"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        long min = 0;
        long max = 100;
        long val = 0;
        if(args.length >= 2) {
            if (args[0].length() < 20)
                min = Long.parseLong(args[0]);
            else {
                if(args[0].startsWith("-"))
                    min = Long.MIN_VALUE + 5;
                else
                    min = Long.MAX_VALUE - 1;
            }

            if (args[1].length() < 20)
                max = Long.parseLong(args[1]);
            else {
                if(args[1].startsWith("-"))
                     max = Long.MIN_VALUE + 5;
                else
                    max = Long.MAX_VALUE - 1;
            }

        }


        if(max - min < min){
            long prevMax = max;
            max = min;
            min = prevMax;
        }

        val = min + (long)(Math.random() * ((max - min) + 1));


        var embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Random Number between " + min + " and " + max , null, event.getJDA().getSelfUser().getAvatarUrl());
        embedBuilder.setColor(BotVars.color());
        embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setDescription(Long.toString(val));
        event.getChannel().sendMessage(embedBuilder.build()).queue();

    }

}
