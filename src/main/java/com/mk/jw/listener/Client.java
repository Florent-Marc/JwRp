package com.mk.jw.listener;

import com.mk.jw.Utils.Utils;
import com.mk.jw.gui.GuiDownload;
import com.mk.jw.gui.GuiPause;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
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
           //e.setGui(new GuiPause());
        }
        if(e.getGui() instanceof GuiDisconnected){
            //e.setGui(new com.mk.jw.gui.GuiMainMenuFailed());

        }
        if(e.getGui() instanceof GuiDownloadTerrain){
            //e.setGui(new GuiDownload());
        }
        Log.error(e.getGui());
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
        drawEntityOnScreen( w/18,h+((h/4)/2),h/6,Minecraft.getMinecraft().mouseHelper.deltaX,Minecraft.getMinecraft().mouseHelper.deltaY,Minecraft.getMinecraft().player);
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
    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY,
                                          EntityLivingBase ent) {

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, -50F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate((float) (180.0F), (float) 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate((float) (25.0F), (float) 0.0F, 1.0F, 0.0F);

        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        RenderHelper.enableStandardItemLighting();
        ent.renderYawOffset = 0;
        ent.rotationYawHead = 0;
        ent.rotationPitch = 0;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);

        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);

        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
    }
}
