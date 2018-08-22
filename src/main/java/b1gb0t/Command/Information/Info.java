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

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;


public class Info extends AbstractCommand {
    public Info() {
        category = CommandCat.INFORMATIVE;
    }

    @Override
    public String commandName() {
        return "info";
    }

    @Override
    public String commandUsage() {
        return "info";
    }

    @Override
    public String commandDescription() {
        return "Sends info about this bot.";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"botinfo", "binfo", "bot"};
    }

    @Override
    public void commandFunction(GuildMessageReceivedEvent event, Guild guild, Channel channel, User author, Message message, String rawMessage, String[] args) {

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("b1gb0t Info", "https://github.com/FirahGames/b1gb0t", event.getJDA().getSelfUser().getAvatarUrl());
        embed.addField(":white_check_mark: Version", "0.0.1 Development", true);
        embed.addField(":books: Library", "JDA", true);
        embed.addField(":crown: Creator", "firah#0017", true);
        embed.addField(":flag_us: Servers", "" + event.getJDA().getGuilds().size(), true);
        embed.addField(":selfie: Users", "" + event.getJDA().getUsers().size(), true);
        embed.addField(":file_folder: Github", "http://bit.do/bigbotgit", true);
        embed.addField(":tram: RAM Usage", Long.toString((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 / 1000) + "MB", true);
        embed.addField(":chipmunk: CPU Usage", String.format("%.2f", osBean.getProcessCpuLoad()) + "%", true);
        embed.addField(":floppy_disk: Operating System", System.getProperty("os.name"), true);
        embed.addField(":timer: Uptime", Long.toString(rb.getUptime() / 1000 / 60) + " Minutes", true);
        embed.addField(":tools: Owner", event.getJDA().getUserById(BotVars.ownerId()).getName() + "#" + event.getJDA().getUserById(BotVars.ownerId()).getDiscriminator(), true);
        embed.setColor(BotVars.color());
        embed.setFooter("Requested by " + author.getName(), author.getAvatarUrl());
        embed.setTimestamp(Instant.now());
        event.getChannel().sendMessage(embed.build()).queue();
    }
}
