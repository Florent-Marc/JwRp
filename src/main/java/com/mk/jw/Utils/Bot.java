package com.mk.jw.Utils;

import akka.actor.ActorCell;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Bot extends ListenerAdapter {

    private static String token;
    public static JDABuilder jdb;
    public static JDA jda;
    public static String messageid ;
    EmbedBuilder builder = new EmbedBuilder();
    public static JDA getJda() {
        return jda;
    }

    public static void stop() {
        try {
            jdb.build().shutdown();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        int count = FMLServerHandler.instance().getServer().getCurrentPlayerCount();
        int maxcount = FMLServerHandler.instance().getServer().getMaxPlayers();
        super.onMessageReceived(event);
        if (!event.getAuthor().isBot()) {
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("/clear")) {
                /*
                builder.addField("Dernier refresh", String.valueOf(Calendar.getInstance().getTime()), true);
                EmbedBuilder m = null;
                m = (EmbedBuilder) event.getJDA().getTextChannelById(channelId).retrieveMessageById("861415233284341800");
                m.addField("Dernier refresh", String.valueOf(Calendar.getInstance().getTime()), true);

                 */
            }
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        super.onReady(event);
        jda = event.getJDA();
        Logs.sendlog("Serveur lancer",1);
    }

    public static void start(){
        jdb = JDABuilder.createDefault(token);
        jdb.setActivity(Activity.watching("JW"));
        jdb.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jdb.addEventListeners(new Bot());
        jdb.addEventListeners(new Logs());
        try {
            jdb.build();
            System.out.println("["+"Bot"+"]"+"Lancer avec succes !");
            //embed();
        } catch (LoginException loginException) {
            loginException.printStackTrace();
            System.out.println("["+"Bot"+"]"+loginException);
        }
    }

    public static void LoadConfig(){
        Properties config = new Properties();

        try {
            config.load(new FileReader(new File("mods/setup/Config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("["+"Bot"+"]"+"Erreur lors du chargement du Token");
            return;
        }
        token = config.getProperty("token");

        System.out.println("["+"Bot"+"]"+"Token chargee avec succes");
    }
}
