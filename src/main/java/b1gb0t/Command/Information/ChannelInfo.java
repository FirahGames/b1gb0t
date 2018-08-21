package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class ChannelInfo extends AbstractCommand {
    public ChannelInfo(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "channelinfo";
    }

    @Override
    public String commandUsage() {
        return "channelinfo (channel)";
    }

    @Override
    public String commandDescription() {
        return "Grabs info from a specific channel.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"cinfo", "chanfo", "chaninfo"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        TextChannel chan;
        if(!event.getMessage().getMentionedChannels().isEmpty())
            chan = event.getMessage().getMentionedChannels().get(0);
        else
            chan = message.getTextChannel();
        EmbedBuilder eBuilder = new EmbedBuilder();
        eBuilder.setAuthor("Channel: #" +  chan.getName(), "https://discordapp.com", guild.getIconUrl());
        eBuilder.setColor(Constants.color());
        eBuilder.addField(":clock1: Creation Date/Time", chan.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);
        eBuilder.addField(":race_car: Position", chan.getPositionRaw() + "/" + guild.getTextChannels().size(), false);
        if(chan.getTopic() != null && !chan.getTopic().isEmpty())
            eBuilder.addField(":speech_left: Topic", chan.getTopic(), false);
        eBuilder.addField(":basketball_player: Members", chan.getMembers().size() + "", false);
        if(chan.getParent() != null)
            eBuilder.addField(":card_box: Category", chan.getParent().getName(), false);
        eBuilder.addField(":mailbox: Invite Count", chan.getInvites().complete().size() + "", false);
        eBuilder.addField(":e_mail: Last Activity", chan.getMessageById(chan.getLatestMessageIdLong()).complete().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), false);
        eBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        eBuilder.setTimestamp(Instant.now());
        message.getTextChannel().sendMessage(eBuilder.build()).queue();
    }
}
