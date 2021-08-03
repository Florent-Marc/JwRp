package com.mk.jw.listener;

import com.mk.jw.Utils.Utils;
import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;

@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.SERVER})
public class Serveur {


    public static int worldtime = 23000;
    public static int minutes = 0;
    public static double timeingame = 0;

    public static int counter = 0;
    public static int timer = 40*60;//


    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (counter==0){
            counter = timer;
            if (minutes == 50) {
                minutes = 0;
            }else{
                minutes=minutes+1;
            }
            timeingame=timeingame+14.4;
            //Log.info("Ancienner de la periode : "+minutes+"");
            worldtime=worldtime+240;
            if(worldtime==24000){
                worldtime=0;
            }
            //Log.info(worldtime);
            FMLServerHandler.instance().getServer().getWorld(0).setWorldTime(worldtime);
            //FMLServerHandler.instance().getServer().sendMessage(new TextComponentString("Mine reset"));

        }else{
            counter--;
        }
    }
    @SubscribeEvent
    public void onConnectToServer(PlayerEvent.PlayerLoggedInEvent e) {
        //Logs.sendlog(e.player.getName()+" vient se connecter",1);
        //e.player.setInvisible(true);
    }
    @SubscribeEvent
    public void onConnectToServer(PlayerEvent.PlayerLoggedOutEvent e) {

        //Logs.sendlog(e.player.getName()+" vient se deconnecter",1);
    }

    @SubscribeEvent
    public static void tt(EntityJoinWorldEvent e) {

        if (e.getEntity() instanceof EntityZombie) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntityWitch) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntitySpider) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntitySkeleton) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntityEnderman) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntityCreeper) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntitySlime) {
            e.setCanceled(true);
        }
        if (e.getEntity() instanceof EntityCreeper) {
            e.setCanceled(true);
        }

    }
}
