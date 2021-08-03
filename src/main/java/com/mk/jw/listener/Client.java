package com.mk.jw.listener;

import com.mk.jw.Utils.Utils;
import com.mk.jw.gui.GuiStats;
import com.mk.jw.gui.GuiTryDisconnect;
import com.mk.jw.gui.Scientifique;
import com.mk.jw.gui.Securiter;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jline.utils.Log;
import org.lwjgl.opengl.GL11;


@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.CLIENT})
public class Client {

    @SubscribeEvent
    public void GuieventHandler(GuiOpenEvent e) {
        if(e.getGui() instanceof GuiMainMenu){
            Minecraft.getMinecraft().gameSettings.loadOptions();
            Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.MUSIC,0.0F);
            Minecraft.getMinecraft().gameSettings.saveOptions();
            //Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(SoundManager.menu, 1.0F, 0.3F));
           //e.setGui(new com.mk.jw.gui.GuiMainMenu());
        }
        if(e.getGui() instanceof GuiDisconnected){
            //e.setGui(new com.mk.jw.gui.GuiMainMenuFailed());

        }
        if(e.getGui() instanceof GuiDownloadTerrain){
            //e.setGui(new GuiDownload());
            Utils.gui=true;
        }
        if(e.getGui() == null ){
            if(Utils.gui) {
                e.setGui(new GuiStats());
            }
        }
        if(e.getGui() instanceof GuiIngameMenu){
            e.setGui(new GuiTryDisconnect());
        }
        //Log.error(e.getGui());
    }
    @SubscribeEvent
    public void block(PlayerInteractEvent.RightClickBlock e){
        if(e.getItemStack().getItem()== Item.getItemById(50)){
        }

    }
    @SubscribeEvent
    public void InteractWithEntity(PlayerInteractEvent.EntityInteract e){
        EntityPlayer p = e.getEntityPlayer();

        if(e.getTarget().getName().equals("Securiter")){
            Minecraft.getMinecraft().displayGuiScreen(new Securiter());
        }
        if(e.getTarget().getName().equals("Scientifique")){
            Minecraft.getMinecraft().displayGuiScreen(new Scientifique());
        }
    }

    @SubscribeEvent
    public void renderGameOverlayPre(RenderGameOverlayEvent.Pre e) {

        if(!(Minecraft.getMinecraft().player.getPermissionLevel()==4)){
            if (e.getType() == RenderGameOverlayEvent.ElementType.DEBUG) {

                e.setCanceled(true);
            }
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
            e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            e.setCanceled(true);
        }
        if (e.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
            e.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void renderPseudo(RenderLivingEvent.Specials.Pre e) {
        if (!(Minecraft.getMinecraft().player.getPermissionLevel()==4)) {
            e.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Post e) {
        int w = e.getResolution().getScaledWidth();
        int h = e.getResolution().getScaledHeight();
        if(!Minecraft.getMinecraft().player.isCreative()) {
            GlStateManager.pushMatrix();
            GL11.glScaled(0.7F, 0.7F, 0.7F);
            GlStateManager.translate(0, 0, -5.0F);
            int heal = (int) (Minecraft.getMinecraft().player.getHealth() * 5);
            int food = (int) (Minecraft.getMinecraft().player.getFoodStats().getFoodLevel() * 5);
            FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow(ChatFormatting.RED + String.valueOf(heal) + "%", (float) w/6, (float) (h + (h * 0.4)) - 20, 0xfc0000);
            FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow(ChatFormatting.DARK_GRAY + String.valueOf(food + "%"), (float) w/6, (float) ((h + (h * 0.4)) - 10), 0xfc0000);
            GlStateManager.popMatrix();
        }
    }

}
