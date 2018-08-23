package b1gb0t.Command.Information;

import b1gb0t.Command.AbstractCommand;
import b1gb0t.Enums.CommandCat;
import b1gb0t.Variables.BotVars;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class RoleInfo extends AbstractCommand {
    public RoleInfo() {
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "roleinfo";
    }

    @Override
    public String commandUsage() {
        return "roleinfo <role>";
    }

    @Override
    public String commandDescription() {
        return "Gets info about a certain role.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"role", "getrole", "rollinfo"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {
        if (!message.getMentionedRoles().isEmpty()) {
            message.getMentionedRoles().forEach(role -> {
                var embedBuilder = new EmbedBuilder();
                embedBuilder.addField("Name", role.getName(), true);
                embedBuilder.addField("ID", role.getId(), true);
                embedBuilder.addField("Position", role.getPosition() + "/"  +guild.getRoles().size(), true);
                embedBuilder.addField("Color", "rgb(" + role.getColor().getRed() + ", " + role.getColor().getGreen() + ", " + role.getColor().getBlue() + ")", true);
                StringBuilder perms = new StringBuilder();
                embedBuilder.addField("Mention", role.getAsMention(), true);
                embedBuilder.addField("Creation Time", role.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
                role.getPermissions().forEach(perm->{
                    if(perm == role.getPermissions().get(0))
                        perms.append("``" + perm.getName() + "``");
                    else
                        perms.append(", ``" + perm.getName() + "``");
                });
                embedBuilder.addField("Permissions", perms.toString(), true);
                embedBuilder.setColor(BotVars.color());
                embedBuilder.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
                embedBuilder.setTimestamp(Instant.now());
                embedBuilder.setAuthor("Role: " + role.getName(), "https://discordapp.com", guild.getIconUrl());
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            });
        }
    }
}

