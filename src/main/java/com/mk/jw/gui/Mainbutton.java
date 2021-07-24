package com.mk.jw.gui;

import com.mk.jw.Utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Mainbutton extends GuiButton {
    private static int tY = 0;
    private static int tX = 0;
    private static String t ="";
    private static final ResourceLocation button0 = new ResourceLocation(Utils.MODID,"textures/gui/button0.png");
    private static final ResourceLocation button1 = new ResourceLocation(Utils.MODID,"textures/gui/button1.png");

    public Mainbutton(int buttonId, int PosX, int PosY, int tailleX, int tailleY, String text) {
        super(buttonId, PosX, PosY,tailleX,tailleY,text);
        this.tY = tailleY;
        this.tX = tailleX;
        t = text;

    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible){
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if(this.hovered){
                GlStateManager.pushMatrix();
                GlStateManager.translate(0,0, 15.0F);
                mc.getTextureManager().bindTexture(button1);
                GL11.glColor4f(1, 1, 1, 0.5F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);
                Gui.drawScaledCustomSizeModalRect(this.x,this.y,0,0,1,1,this.tX,this.tY,1,1);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();

            }else {
                GlStateManager.pushMatrix();
                GlStateManager.translate(0,0, 15.0F);
                mc.getTextureManager().bindTexture(button1);
                GL11.glColor4f(1, 1, 1, 0.2F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);
                Gui.drawScaledCustomSizeModalRect(this.x,this.y,0,0,1,1,this.tX,this.tY,1,1);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();


            }
            int j = 14737632;
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate(0,0, 16.0F);
            this.drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
            GlStateManager.popMatrix();
        }

    }
}
