package com.mk.jw.listener;

import com.mk.jw.Utils.Logs;
import com.mk.jw.Utils.Utils;
import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.SERVER})
public class Serveur {

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
