package Command.Utility;
import Command.Command;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class Info extends Command {
    @Override
    public String cmdName() {
        return "info";
    }

    @Override
    public String cmdDesc() {
        return "Sends info about the bot.";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "info", Constants.prefix() + "i");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String raw, User author, Guild server, String... args) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("b1gb0t Info", "https://github.com/FirahGames/b1gb0t", event.getJDA().getSelfUser().getAvatarUrl());
        embed.addField(":white_check_mark: Version", "1.0", true);
        embed.addField(":books: Library", "JDA", true);
        embed.addField(":crown: Creator", "firah#0017", true);
        embed.addField(":flag_us: Servers", "" + event.getJDA().getGuilds().size(), true);
        embed.addField(":selfie: Users", "" + event.getJDA().getUsers().size(), true);
        embed.addField(":file_folder: Github", "http://bit.do/bigbotgit", true);

        embed.setColor(Color.green);
        embed.setFooter("b1g b0t", null);
        embed.setTimestamp(Instant.now());
        event.getChannel().sendMessage(embed.build()).queue();
    }
}
