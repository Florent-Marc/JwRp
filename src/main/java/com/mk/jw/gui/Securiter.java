package com.mk.jw.gui;

import com.mk.jw.Utils.Profils;
import com.mk.jw.Utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

public class Securiter extends GuiScreen {
    @Override
    public void initGui() {
        this.buttonList.add(new Mainbutton(1,(width/2)-((width/7)/2),(height/2)+(height/6),this.width/7,this.height/20,"Rejoindre"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        /*
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, 1.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/noir.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        GlStateManager.popMatrix();

         */

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, -14.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/leftgame.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        Gui.drawScaledCustomSizeModalRect(((this.width/2)-((this.width/5)/2)), this.height/4, 0, 0, 1, 1, this.width/5, this.height/2, 1, 1);
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer,TextFormatting.GREEN + "Team Securiter", this.width / 2 , (this.height/2)-this.height/5, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer,TextFormatting.RED + "Ce choix est definitif donc", this.width / 2 , (this.height/2)+40, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer,TextFormatting.RED + "faite bien votre choix ...", this.width / 2 , (this.height/2)+50, 0);

    }
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
}
