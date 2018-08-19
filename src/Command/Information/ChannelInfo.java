package Command.Information;

import Command.Command;
import Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ChannelInfo extends Command {
    @Override
    public String cmdName() {
        return "channelinfo";
    }

    @Override
    public String cmdDesc() {
        return "Shows info about a channel.";
    }

    @Override
    public List<String> cmdNames() {
        return Arrays.asList(Constants.prefix() + "channelinfo", Constants.prefix() + "channeli");
    }

    @Override
    public void cmdFunc(GuildMessageReceivedEvent event, Message msg, String raw, User author, Guild server, String... args) {
        TextChannel chan;
        if(!event.getMessage().getMentionedChannels().isEmpty())
            chan = event.getMessage().getMentionedChannels().get(0);
        else
            chan = msg.getTextChannel();
        EmbedBuilder eBuilder = new EmbedBuilder();
        eBuilder.setAuthor("Channel: #" +  chan.getName(), "https://discordapp.com", server.getIconUrl());
        eBuilder.setColor(Constants.color());
        eBuilder.addField(":clock1: Creation Date/Time", chan.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);
        eBuilder.addField(":race_car: Position", chan.getPositionRaw() + 1 + "/" + server.getTextChannels().size(), false);
        if(chan.getTopic() != null && !chan.getTopic().isEmpty())
            eBuilder.addField(":speech_left: Topic", chan.getTopic(), false);
        eBuilder.addField(":basketball_player: Members", chan.getMembers().size() + "", false);
        if(chan.getParent() != null)
            eBuilder.addField(":card_box: Category", chan.getParent().getName(), false);
        eBuilder.addField(":mailbox: Invite Count", chan.getInvites().complete().size() + "", false);
        eBuilder.addField(":e_mail: Last Activity", chan.getMessageById(chan.getLatestMessageIdLong()).complete().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME).toString() + "", false);
        msg.getTextChannel().sendMessage(eBuilder.build()).queue();
    }
}