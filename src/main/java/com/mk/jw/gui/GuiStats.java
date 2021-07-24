package com.mk.jw.gui;

import com.mk.jw.Utils.Profils;
import com.mk.jw.Utils.Utils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiStats extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(2, (((width/2)-((width/5)/2))), height/2+(height/2)-20, width/5, 20, TextFormatting.AQUA+"spawn"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/map.png"));

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width-this.width/3, this.height, 1, 1);

        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((int) ((this.width/6)*4.5),(this.height/2), 80.0F);
        if(width>600){
            GL11.glScaled(1.0F,1.0F,1.0F);
        }else{
            GL11.glScaled(0.5F,0.5F,0.5F);
        }
        this.drawString(this.fontRenderer, "Pseudo : "+ Profils.getPrenom(), 0, -80,14423100);
        this.drawString(this.fontRenderer, "Team : "+Profils.getTeam(), 0, -70,14423100);
        this.drawString(this.fontRenderer, "Xp : "+Profils.getxp(), 0, -60,14423100);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0,0, 80.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiInventory.drawEntityOnScreen((int) ((this.width/6)*5.5), (int) ((this.height/4)*2.8),this.height/5, (float) ((this.width/2)-mouseX/1.5), (this.height/2)-mouseY, mc.player);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, 2.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/leftgame.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(this.width-this.width/3, 0, 0, 0, 1, 1, this.width/3, this.height, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }



    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if(button.id==1){
            this.mc.displayGuiScreen((GuiScreen) null);
        }
        else if(button.id == 2) {
            Utils.gui=false;
            this.mc.displayGuiScreen((GuiScreen) null);


        }


    }


}
