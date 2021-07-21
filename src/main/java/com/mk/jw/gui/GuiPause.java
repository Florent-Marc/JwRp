package com.mk.jw.gui;

import com.mk.jw.Utils.Profils;
import com.mk.jw.Utils.Utils;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiPause extends GuiScreen {

    private static final ResourceLocation background = new ResourceLocation(Utils.MODID,"textures/gui/font1.png");
    public int xSize=350;
    public int ySize=600;

    @Override
    public void initGui() {
        super.initGui();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i = (this.width - this.xSize) / 4;
        int j = (this.height - this.ySize) / 2;
        mc.getTextureManager().bindTexture(background);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, 250, 300, 1, 1);

        //int textureWidth = (int) (23f / 200f * Profils.getxp());
        this.drawTexturedModalRect(i + 81, j + 390, 150, 18, 100, 7);

        //int textureHeight = (int) (12f / Profils.getniveausup() * Profils.getxp());
        //this.drawTexturedModalRect(i + 36, j + 35 + 12 - textureHeight,
          //      350, 12 - textureHeight, 27, textureHeight);


        this.fontRenderer.drawString(ChatFormatting.GOLD +"xp", i + 60, j + 0, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
