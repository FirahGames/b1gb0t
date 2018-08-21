package b1gb0t.Handlers;
import b1gb0t.Connection.Connection;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Signed in!");
    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Connection.getCommandHandler().processMessage(event, event.getMessage());
    }
}
