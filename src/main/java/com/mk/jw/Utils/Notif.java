package com.mk.jw.Utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

public class Notif {

    public static int timer ;
    public static String notif;
    public static int counter = 0;

    public void sendNotif(int timer, String notif){
        Notif.timer = timer*40;
        Notif.notif = notif;
    }

    @SubscribeEvent
    public static void minuteur(TickEvent.ClientTickEvent event) {
        if (counter==0){

            counter = timer;
        }else{
            counter--;
        }
    }
    @SubscribeEvent
    public void rendermanager(RenderGameOverlayEvent.Post e) {
        int w = e.getResolution().getScaledWidth();
        int h = e.getResolution().getScaledHeight();
        if(!Minecraft.getMinecraft().player.isCreative()) {
            GlStateManager.pushMatrix();
            GL11.glScaled(0.7F, 0.7F, 0.7F);
            GlStateManager.translate(0, 0, -5.0F);
            int heal = (int) (Minecraft.getMinecraft().player.getHealth() * 5);
            int food = Minecraft.getMinecraft().player.getFoodStats().getFoodLevel() * 5;
            FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow(ChatFormatting.RED + String.valueOf(heal) + "%", (float) w/6, (float) (h + (h * 0.4)) - 20, 0xfc0000);
            GlStateManager.popMatrix();
        }
    }


}
