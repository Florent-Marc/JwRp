package com.mk.jw.blocks.gui;

import com.mk.jw.Utils.Utils;
import com.mk.jw.blocks.tile.TEGenerateur;
import com.mk.jw.gui.Mainbutton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

public class GuiGenerateur extends GuiScreen {
    private static final ResourceLocation background = new ResourceLocation(Utils.MODID,"textures/custom_furnace.png");
    public TEGenerateur tile;
    public int time = 0;
    public GuiGenerateur(TEGenerateur te) {
        this.tile = te;
    }


    @Override
    public void initGui() {
        this.buttonList.add(new Mainbutton(1,(width/2)-((width/7)/2),(height/2)+(height/6),this.width/7,this.height/20,"true"));
        this.buttonList.add(new Mainbutton(2,(width/2)-((width/7)/2),(height/2)+(height/6)-this.height/18,this.width/7,this.height/20,"false"));
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

        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN + "Fuel : "+this.tile.getFuel(), this.width / 2 , (this.height/2)-this.height/5, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN + "Capaciter : "+this.tile.getCapacitor(), this.width / 2 , ((this.height/2)-this.height/5)+10, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN + "Consome : "+this.tile.isburned(), this.width / 2 , ((this.height/2)-this.height/5)+20, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN + "Timeleft : "+this.time, this.width / 2 , ((this.height/2)-this.height/5)+30, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN + "Status : "+this.tile.getState(), this.width / 2 , ((this.height/2)-this.height/5)+40, 0);

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.time = this.tile.getTimeleft()/20;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if(button.id==1){
            this.tile.setState(true);
        }
        if(button.id == 2){
            this.tile.setState(false);
        }
    }
}
