package b1gb0t.Connection;

import b1gb0t.Handlers.CommandHandler;
import b1gb0t.Handlers.EventHandler;
import b1gb0t.Util.ImageUtil;
import b1gb0t.Variables.BotVars;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.WriterConfig;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Icon;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Connection {

    private static JDA m_Bot;
    private static CommandHandler m_Commands = new CommandHandler();

    public static CommandHandler getCommandHandler() {
        return m_Commands;
    }

    private static JsonArray jsonArray;

    public static long getPing() {
        return m_Bot.getPing();
    }

    public static void main(String[] args) {

        JDA discord = null;
        try {

            Path path = Paths.get("./config.json");
            Scanner in = new Scanner(System.in);
            if (!Files.exists(path)) {
                System.out.println("What do you want your prefix to be?");
                String prefix = in.nextLine();
                BotVars.setPrefix(prefix);
                System.out.println("What is your user ID?");
                String id = in.nextLine();
                BotVars.setOwner(id);
                System.out.println("What is your bot token?");
                String token = in.nextLine();
                BotVars.setToken(id);
                System.out.println("What is your bots image? (URL)");
                String img = in.nextLine();
                BotVars.setImage(id);
                var user = Json.object().add("prefix", prefix).add("id", id).add("token", token).add("image", img).add("game", "watching ali-a");
                String json = user.toString(WriterConfig.PRETTY_PRINT);
                BufferedWriter writer = new BufferedWriter(new FileWriter("config.json"));
                writer.write(json);
                writer.close();
            } else {
                Reader reader = new FileReader("./config.json");
                var lol = Json.parse(reader);
                var jObj = lol.asObject();
                BotVars.setToken(jObj.getString("token", "Default Token"));
                BotVars.setImage(jObj.getString("image", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&h=350"));
                BotVars.setPrefix(jObj.getString("prefix", "b1g."));
                BotVars.setOwner(jObj.getString("id", "274686621243408387"));
                BotVars.setGame(jObj.getString("game", "watching faggotry"));
            }

            m_Commands.loadCommands();
            discord = new JDABuilder(AccountType.BOT).setToken(BotVars.token()).addEventListener(new EventHandler()).buildBlocking();
            System.out.println("Setting game to: " + BotVars.game());
            String[] gamer = BotVars.game().split(" ", 2);
            String[] game = Arrays.copyOfRange(gamer, 1, gamer.length);
            if (gamer[0].equalsIgnoreCase("listening")) {
                System.out.println("Set game!");
                discord.getPresence().setGame(Game.listening(String.join(" ", gamer[1])));
            } else if (gamer[0].equalsIgnoreCase("watching")) {
                System.out.println("Set game!");
                discord.getPresence().setGame(Game.watching(String.join(" ", gamer[1])));
            } else if (gamer[0].equalsIgnoreCase("playing")) {
                System.out.println("Set game!");
                discord.getPresence().setGame(Game.playing(String.join(" ", gamer[1])));
            } else {
                System.out.println("Set game!");
                discord.getPresence().setGame(Game.playing(String.join(" ", String.join(" ", gamer))));
            }

        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
