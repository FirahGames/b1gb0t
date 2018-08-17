import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class RespondMsg extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Bot Ready!");
    }
    @Override
    public void onMessageReceived (MessageReceivedEvent event){
            String msg = event.getMessage().getContentRaw();

            if(msg.startsWith(Constants.m_Prefix + "p1ng")){
                String response = "p0ng!";
                event.getTextChannel().sendMessage(response).queue();
            }
    }
}
