package ipl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class iplbot {
    static String prefix ="+";
    static String token = "OTY2NDE4MzUwOTQ5NDI1MjA2.YmBdOg.HdCLnTwGuzSs5hd1mI50daAftjg";
    static JDA bot;
    public static void main(String[] args) throws LoginException {
        bot = JDABuilder.createDefault(token).build();
        bot.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        bot.getPresence().setActivity(Activity.watching("Mumbai lose 8 in a row"));
        bot.addEventListener(new Commands());
    }
}
