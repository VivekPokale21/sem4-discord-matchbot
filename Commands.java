package ipl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
class match{
    String team1,team2,location;
    ZonedDateTime startTime;
    match(String t1,String t2,String loc,ZonedDateTime z){
        team1=t1;
        team2=t2;
        location = loc;
        startTime=z;
    }
}
public class Commands extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            ArrayList<match> ipl = new ArrayList<>(28);
            Scanner s = new Scanner(new File("F:\\Vivvysaur\\Documents\\iplbot\\src\\ipl\\source.txt"));
            while(s.hasNextLine()){
                ipl.add(new match(s.next(),s.next(),s.next(),ZonedDateTime.of(2022,Integer.parseInt(s.next()),Integer.parseInt(s.next()),Integer.parseInt(s.next()),0,0,0, ZoneId.of("GMT+4"))));
            }
            ZonedDateTime current = ZonedDateTime.now();
            if(args[0].equalsIgnoreCase(iplbot.prefix+"info")){
                    Duration d = null; int i =0;
                    while(i<ipl.size()){
                        if(current.isBefore(ipl.get(i).startTime)){
                            d=Duration.between(current,ipl.get(i).startTime);
                            break;
                        }
                        i++;
                    }
                    long sec = d.toSecondsPart();
                    long min = d.toMinutesPart();
                    long hr = d.toHoursPart();
                    String time = hr+" Hours "+min+" Minutes "+sec+" Seconds";
                    String t1 = hash.getHashed(ipl.get(i).team1);
                    String t2 = hash.getHashed(ipl.get(i).team2);
                    String loc = hash.getHashed(ipl.get(i).location);
                    if(loc!=null) {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle("Upcoming IPL Match");
                        embed.addField("Team 1", t1, true);
                        embed.addField("Team 2", t2, true);
                        embed.addField("Location", loc, false);
                        embed.addField("Time Left", time, false);
                        embed.setColor(0xa64ca6);
                        embed.setFooter("IPL 2022");
                        event.getChannel().sendMessageEmbeds(embed.build()).queue();
                    }
            }
            else if(args[0].equalsIgnoreCase(iplbot.prefix+"fixtures")&&args[1]!=null){
                    int i = 0; String t1="",t2="",time="";
                    ArrayList<match> temp = new ArrayList<>();
                    while (i < ipl.size()) {
                        if (current.isBefore(ipl.get(i).startTime)) {
                            if(ipl.get(i).team1.equalsIgnoreCase(args[1])) {
                                /*t1 += ipl.get(i).team1 + "\n";
                                time += ipl.get(i).startTime.getDayOfMonth()+" "+ipl.get(i).startTime.getMonth()+" at "+ipl.get(i).startTime.getHour()+ ":00\n";*/
                                temp.add(ipl.get(i));
                            }
                        }
                        i++;
                    }
                    i=0;
                    while (i < ipl.size()) {
                    if (current.isBefore(ipl.get(i).startTime)) {
                        if(ipl.get(i).team2.equalsIgnoreCase(args[1])) {
                                /*t1 += ipl.get(i).team1 + "\n";
                                time += ipl.get(i).startTime.getDayOfMonth()+" "+ipl.get(i).startTime.getMonth()+" "+ipl.get(i).startTime.getHour()+ ":00\n";*/
                                temp.add(ipl.get(i));
                            }
                        }
                        i++;
                    }
                    int j=0;
                    sort obj = new sort();
                    obj.mergeSort(temp, temp.size());
                    while(j<temp.size()){
                        t1 += temp.get(j).team1 + "\n";
                        t2 += temp.get(j).team2 + "\n";
                        time += temp.get(j).startTime.getDayOfMonth()+" "+temp.get(j).startTime.getMonth()+" at "+temp.get(j).startTime.getHour()+ ":00\n";
                        j++;
                    }
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setThumbnail("https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixlok.com%2Fimages%2Fipl-png-logo-free-download%2F&psig=AOvVaw1tbWifbXYCst63U4cNASKD&ust=1651247353357000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIDF3IiOt_cCFQAAAAAdAAAAABAD");
                    embed.setTitle("Remaining Fixtures for "+args[1].toUpperCase());
                    embed.addField("Team 1",t1,true);
                    embed.addField("Team 2",t2,true);
                    embed.addField("Time",time,true);
                    embed.setColor(0xa64ca6);
                    embed.setFooter("IPL 2022");
                    event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
            else if(args[0].equalsIgnoreCase(iplbot.prefix+"schedule") ){
                    int i = 0; String t1="",t2="",time="";
                    while (i < ipl.size()) {
                        if (current.isBefore(ipl.get(i).startTime)) {
                            t1+=ipl.get(i).team1+"\n";
                            t2+=ipl.get(i).team2+"\n";
                            time += ipl.get(i).startTime.getDayOfMonth()+" "+ipl.get(i).startTime.getMonth()+" at "+ipl.get(i).startTime.getHour()+ ":00\n";
                        }
                        i++;
                    }
                    EmbedBuilder embed = new EmbedBuilder();
                    //embed.setThumbnail("G:\\Downloads\\IPL-Logo-PNG.jpg");
                    embed.setTitle("Upcoming Fixtures");
                    embed.addField("Team 1",t1,true);
                    embed.addField("Team 2",t2,true);
                    embed.addField("Time",time,true);
                    embed.setColor(0xa64ca6);
                    embed.setFooter("IPL 2022");
                    event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
        }catch (FileNotFoundException e){e.printStackTrace();}
    }
}