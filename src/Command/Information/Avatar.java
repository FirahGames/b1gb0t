package Command.Information;
import Command.Command;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Avatar extends Command {
    @Override
    public String cmdName() {
        return "avatar";
    }

    @Override
    public String cmdDesc() {
        return "Embeds a users avatar.";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "avatar", Constants.prefix() + "avi");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String raw, User author, Guild server, String... args) {
         EmbedBuilder eBuilder = new EmbedBuilder();
            User aviGet;
         if(!event.getMessage().getMentionedUsers().isEmpty())
             aviGet = event.getMessage().getMentionedUsers().get(0);
         else
             aviGet = author;
         eBuilder.setImage(aviGet.getAvatarUrl());
         eBuilder.setColor(Constants.color());
         eBuilder.setTitle(":mountain: Avatar for **" + aviGet.getName() + "#" + aviGet.getDiscriminator() + "**");
         event.getChannel().sendMessage(eBuilder.build()).queue();
    }
}
