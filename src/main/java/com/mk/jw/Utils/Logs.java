package com.mk.jw.Utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Logs extends ListenerAdapter {

    public static void sendlog(String message,int na){
        EmbedBuilder builder = new EmbedBuilder();
        if(na==3){
            builder.setColor(Color.RED);
        }
        if(na==2){
            builder.setColor(Color.ORANGE);
        }
        if(na==1){
            builder.setColor(Color.GREEN);
        }
        builder.setTitle("Logs");
        builder.setDescription("Ce message est generer automatiquement par le serveur\n\n");
        builder.addField("Description", message, true);
        builder.addField("Niveau d'Alerte", String.valueOf(na), false);
        Bot.getJda().getTextChannelById(Utils.ChannelLog).sendMessage(builder.build()).queue();
    }
}
