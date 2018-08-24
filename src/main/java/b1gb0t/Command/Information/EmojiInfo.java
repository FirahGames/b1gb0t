package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.TwemojiWrapper;
import b1gb0t.Variables.BotVars;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import com.vdurmont.emoji.Fitzpatrick;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;

public class EmojiInfo extends AbstractCommand {
    public EmojiInfo() { super(); }

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

        if (!message.getEmotes().isEmpty()) {
            message.getEmotes().forEach(emote -> {
                var embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor("Emoji: " + emote.getName(), emote.getImageUrl(), emote.getImageUrl());
                embedBuilder.setThumbnail(emote.getImageUrl());
                embedBuilder.addField("URL", emote.getImageUrl(), true);
                if (emote.getGuild() != null)
                    embedBuilder.addField("Server", emote.getGuild().getId() + "(" + emote.getGuild().getName() + ")", true);
                else
                    embedBuilder.addField("Server", "*Unknown*", true);
                embedBuilder.addField("Animated", Boolean.toString(emote.isAnimated()), true);
                embedBuilder.addField("ID", emote.getId(), true);
                embedBuilder.addField("API Call", "``<:" + emote.getName() + ":" + emote.getId() + ":>``", true);
                embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
                embedBuilder.setTimestamp(Instant.now());
                embedBuilder.setColor(BotVars.color());
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            });

        } else {
            var emojis = EmojiParser.extractEmojis(String.join(" ", args));
            emojis.forEach(emote -> {
                var embedBuilder = new EmbedBuilder();
                var emoji = EmojiManager.getByUnicode(emote);
                System.out.println();
                embedBuilder.setAuthor("Emoji: " + emoji.getDescription(), null, TwemojiWrapper.parse(emoji.getUnicode()));
                embedBuilder.setTimestamp(Instant.now());
                embedBuilder.setColor(BotVars.color());
                embedBuilder.addField("Supports Fitspatrick", Boolean.toString(emoji.supportsFitzpatrick()), true);
                embedBuilder.addField("Description", emoji.getDescription(), true);
                embedBuilder.addField("HTML Decimal", emoji.getHtmlDecimal(), true);
                embedBuilder.addField("HTML Hexadecimal", emoji.getHtmlHexadecimal(), true);
                embedBuilder.addField("Unicode", "`" +emoji.getUnicode() + "`", true);
                if(emoji.supportsFitzpatrick()){
                    var strBuilder = new StringBuilder();
                    strBuilder.append(emoji.getUnicode(Fitzpatrick.TYPE_1_2));
                    strBuilder.append(emoji.getUnicode(Fitzpatrick.TYPE_3));
                    strBuilder.append(emoji.getUnicode(Fitzpatrick.TYPE_4));
                    strBuilder.append(emoji.getUnicode(Fitzpatrick.TYPE_5));
                    strBuilder.append(emoji.getUnicode(Fitzpatrick.TYPE_6));
                    embedBuilder.addField("Variants", strBuilder.toString(), true);
                }
                else {
                    embedBuilder.addField("Variants", emoji.getUnicode(), true);
                }

                embedBuilder.addField("URL", TwemojiWrapper.parse(emoji.getUnicode()), true);
                embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
                embedBuilder.setThumbnail(TwemojiWrapper.parse(emoji.getUnicode()));
                StringBuilder aliases = new StringBuilder();
                StringBuilder tags = new StringBuilder();
                emoji.getAliases().forEach(alias->{
                    if(alias == emoji.getAliases().get(0))
                        aliases.append("``" + alias + "``");
                    else
                        aliases.append(", ``" + alias + "``");
                });
                emoji.getTags().forEach(tag->{
                    if(tag == emoji.getTags().get(0))
                        tags.append("``" + tag + "``");
                    else
                        tags.append(", ``" + tag + "``");
                });
                embedBuilder.addField("Tags", tags.toString(), true);
                embedBuilder.addField("Aliases", aliases.toString(), true);
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            });
        }
    }
}
