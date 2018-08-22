package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class EmojiInfo extends AbstractCommand {
    public EmojiInfo(){
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "emojiinfo";
    }

    @Override
    public String commandUsage() {
        return "emoji <custom emoji>";
    }

    @Override
    public String commandDescription() {
        return "Returns info about a custom emoji.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"einfo", "emoji", "emoteinfo", "emote"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {

            if(!message.getEmotes().isEmpty()){
                message.getEmotes().forEach(emote->{var embedBuilder = new EmbedBuilder();
                    embedBuilder.setAuthor("Emoji: " + emote.getName(), emote.getImageUrl(), emote.getImageUrl());
                    embedBuilder.setThumbnail(emote.getImageUrl());
                    embedBuilder.addField("URL", emote.getImageUrl(), true);
                    if(emote.getGuild() != null)
                        embedBuilder.addField("Server", emote.getGuild().getId() + "(" + emote.getGuild().getName() + ")", true);
                    else
                        embedBuilder.addField("Server", "*Unknown*", true);
                    embedBuilder.addField("Animated", Boolean.toString(emote.isAnimated()), true);
                    embedBuilder.addField("ID", emote.getId(), true);
                    embedBuilder.addField("API Call", "``<:" + emote.getName() + ":" + emote.getId() + ":>``", true);
                    embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
                    embedBuilder.setTimestamp(Instant.now());
                    embedBuilder.setColor(BotVars.color());
                    event.getChannel().sendMessage(embedBuilder.build()).queue();});

            }
            else{

            }
    }

}
