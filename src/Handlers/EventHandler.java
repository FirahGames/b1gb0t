package Handlers;
import Variables.Constants;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Bot Ready!");
    }
    @Override
    public void onMessageReceived (MessageReceivedEvent event){
            String msg = event.getMessage().getContentRaw();

            if(msg.startsWith(Constants.prefix() + "p1ng") && !event.getAuthor().isBot()){
                String response = "p0ng!";
                event.getTextChannel().sendMessage(response).queue();
            }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
    }
}
