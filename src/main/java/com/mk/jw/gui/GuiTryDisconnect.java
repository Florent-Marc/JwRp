package com.mk.jw.gui;


import com.mk.jw.Utils.Profils;
import com.mk.jw.Utils.Utils;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GuiTryDisconnect extends GuiScreen {
    
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new Mainbutton(1,(width/20)-(width/24),(height/2)+(height/2)-25,this.width/7,20, ChatFormatting.RED+"Quitter"));
        this.buttonList.add(new Mainbutton(2,(width/20)-(width/24),(height/2)+(height/2)-50,this.width/7,20,"Options"));
        this.buttonList.add(new Mainbutton(3,(width/20)-(width/24),(height/2)+(height/2)-75,this.width/7,20,"Mumble"));
        this.buttonList.add(new Mainbutton(4,(width/20)-(width/24),(height/2)+(height/2)-100,this.width/7,20,"Discord"));

    }




    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        /*
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, -10.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/noir.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        GlStateManager.popMatrix();

         */

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, 2.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/font.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width/6, this.height, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(0,0, 2.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/leftgame.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        Gui.drawScaledCustomSizeModalRect((this.width/6)*5, this.height/4, 0, 0, 1, 1, this.width/5, this.height/2, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.pushMatrix();
        GlStateManager.translate(0,0, 80.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GuiInventory.drawEntityOnScreen((int) ((this.width/6)*5.5), (int) ((this.height/4)*2.8),this.height/5,(this.width-this.width/10)-mouseX, (this.height/2)-mouseY, mc.player);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();


        //this.fontRenderer.drawString(TextFormatting.GOLD + "Jobs : "+References.Job.getJobName()  , this.width / 2-45 , this.height / 2 - 90, 0);
        //this.fontRenderer.drawString(TextFormatting.GOLD + "Argent : "+Utils.Argent , this.width / 2-45 , this.height / 2 - 80, 0);
        GlStateManager.translate(0,0, 3.0F);
        this.fontRenderer.drawString(TextFormatting.GOLD + "Nom : "+ Profils.getNom() , +10 , 20, 0);
        this.fontRenderer.drawString(TextFormatting.GOLD + "Prenom : "+Profils.getPrenom()  , +10 , 30, 0);
        this.fontRenderer.drawString(TextFormatting.GOLD + "Team : "+Profils.getTeam() , 10 , 40, 0);
        this.fontRenderer.drawString(TextFormatting.GOLD + "lvl : "+Profils.getlvl() , +10 , 50, 0);

    }
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if(button.id==1){
            this.mc.world.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            this.mc.displayGuiScreen(new GuiMainMenu());
            /*
            DiscordRichPresence richPresence = new DiscordRichPresence();
            richPresence.details = "Dans le menu principal";
            richPresence.startTimestamp = ReachPresence.getNowTimestamp();
            ReachPresence.update(richPresence);

             */



        }
        if(button.id==2){
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if(button.id==3){
            String ipts3 = "ts.ip.fr";
            int portTs3 = 9987;
            URI uri = URI.create("ts3server://"+ ipts3 +"?port="+ portTs3);
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(button.id==4){
            try
            {
                Desktop.getDesktop().browse(new URI("https://discord.gg/2ht65NGtZh"));
            }
            catch (URISyntaxException e){
                e.printStackTrace();
            }
        }
    }
}
