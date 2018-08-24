package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UserInfo extends AbstractCommand {
    public UserInfo(){ super(); }
    @Override
    public String commandName() {
        return "userinfo";
    }

    @Override
    public String commandUsage() {
        return "userinfo (user)";
    }

    @Override
    public String commandDescription() {
        return "Gets info about a user.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"getuser", "uinfo"};

    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        List<User> user = new ArrayList<>();

        if(!message.getMentionedUsers().isEmpty())
            user = message.getMentionedUsers();
        else
            user.add(author);

        user.forEach(info->{
            var embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(guild.getMember(info).getColor());
            embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
            embedBuilder.setTimestamp(Instant.now());
            embedBuilder.setAuthor("User: " + info.getName(), null, info.getAvatarUrl());
            embedBuilder.setThumbnail(info.getEffectiveAvatarUrl());
            embedBuilder.addField("Name" , info.getName() + "#" +  info.getDiscriminator(), true);
            embedBuilder.addField("Mention" , info.getAsMention(), true);
            embedBuilder.addField("ID" , info.getId(), true);
            embedBuilder.addField("Creation date" , info.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            embedBuilder.addField("Join date", guild.getMember(info).getJoinDate().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            switch(guild.getMember(info).getOnlineStatus().getKey()){
                case "online":
                    embedBuilder.addField("Status", ":melon: Online", true);
                    break;
                case "idle":
                    embedBuilder.addField("Status", ":shallow_pan_of_food: Idle", true);
                    break;
                case "dnd":
                    embedBuilder.addField("Status", ":tomato: Do not Disturb", true);
                    break;
                case "invisible":
                    embedBuilder.addField("Status", ":ghost: Invisible", true);
                    break;
                case "offline":
                    embedBuilder.addField("Status", ":new_moon: Offline", true);
                    break;
            }

            if(guild.getMember(info).getGame() != null && guild.getMember(info).getGame().getName() != null)
                embedBuilder.addField("Game", guild.getMember(info).getGame().getName(), true);
            else
                embedBuilder.addField("Game", "None", true);
            if(info.isBot())
                embedBuilder.addField("Bot Account" , "Yes", true);
            else
                embedBuilder.addField("Bot Account" , "No", true);
            var strBuild = new StringBuilder();
            info.getMutualGuilds().forEach(guild1->{
                if(guild1 == info.getMutualGuilds().get(0))
                    strBuild.append("``" + guild1.getName() + "``");
                else
                    strBuild.append(", ``" + guild1.getName() + "``");
            });
            embedBuilder.addField("Mutual Guilds" , strBuild.toString(), true);
            event.getChannel().sendMessage(embedBuilder.build()).queue();
        });

    }
}
