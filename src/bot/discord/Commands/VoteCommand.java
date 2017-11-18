package bot.discord.Commands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.awt.*;
import java.util.List;

public class VoteCommand implements Commands{
    @Override
    public boolean validate(MessageReceivedEvent event) {
        return false;
    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws InterruptedException {
        if(args.length == 0){
            event.getTextChannel().sendMessage(help().build()).queue();
        }

        if(args[0].equalsIgnoreCase("--help")){
            event.getTextChannel().sendMessage(help().build()).queue();
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (String a : args){
            builder.append(a);
            builder.append(" ");
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder
                .setColor(Color.ORANGE)
                .setTitle("VOTE")
                .addField("Author", event.getMessage().getAuthor().getName(), false)
                .addField("Content", builder.toString().trim(), false);

        Message message = new MessageBuilder().setEmbed(embedBuilder.build()).build();
        event.getTextChannel().sendMessage(message).queue();

        List<Message> m = event.getTextChannel().getHistory().retrievePast(5).complete();



    }

    @Override
    public EmbedBuilder help() {
        return new EmbedBuilder().setTitle("**VOTE HELP**").addField("USAGE", "`!vote [content]`" +
                "\n For example, `!vote make a new voice chat?`", false);
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}
