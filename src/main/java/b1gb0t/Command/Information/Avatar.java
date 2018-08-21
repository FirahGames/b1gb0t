package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class Avatar extends AbstractCommand {
    public Avatar(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "avatar";
    }

    @Override
    public String commandUsage() {
        return "avatar (User)";
    }

    @Override
    public String commandDescription() {
        return "Embeds a users avatar";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"avi", "avatar", "eavi"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        EmbedBuilder eBuilder = new EmbedBuilder();
        User aviGet;
        if(!event.getMessage().getMentionedUsers().isEmpty())
            aviGet = event.getMessage().getMentionedUsers().get(0);
        else
            aviGet = author;
        eBuilder.setImage(aviGet.getAvatarUrl());
        eBuilder.setColor(Constants.color());
        eBuilder.setTitle(":mountain_snow:Avatar for **" + aviGet.getName() + "#" + aviGet.getDiscriminator() + "**");
        eBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        eBuilder.setTimestamp(Instant.now());
        event.getChannel().sendMessage(eBuilder.build()).queue();
    }
}
