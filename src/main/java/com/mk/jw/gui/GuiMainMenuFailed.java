package com.mk.jw.gui;

import com.mk.jw.Utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

public class GuiMainMenuFailed extends GuiScreen {
    @Override
    public void initGui() {
        super.initGui();

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(new ResourceLocation(Utils.MODID, "textures/gui/back.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer,TextFormatting.RED + "La connection au serveur a echoue !", this.width / 2 , this.height-20, 0);
        this.drawCenteredString(Minecraft.getMinecraft().fontRenderer,TextFormatting.RED + "prochain essai dans "+timer/20, this.width / 2 , this.height-10, 0);

    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
    int timer =20*30;
    int objectif = 0;
    public void updateScreen()
    {
        if(!(objectif == timer)){
            timer--;
        }else {
            mc.displayGuiScreen(new GuiMainMenu());
            timer=20*30;
        }
        if (this.networkManager != null)
        {
            if (this.networkManager.isChannelOpen())
            {
                this.networkManager.processReceivedPackets();
            }
            else
            {
                this.networkManager.handleDisconnection();
            }
        }
    }
    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
    private NetworkManager networkManager;
    private void connect(final String ip, final int port)
    {
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet())
        {
            public void run()
            {
                InetAddress inetaddress = null;

                try
                {

                    inetaddress = InetAddress.getByName(ip);
                    networkManager = NetworkManager.createNetworkManagerAndConnect(inetaddress, port, mc.gameSettings.isUsingNativeTransport());
                    networkManager.setNetHandler(new NetHandlerLoginClient(networkManager, mc, new GuiMainMenu()));
                    networkManager.sendPacket(new C00Handshake(ip, port, EnumConnectionState.LOGIN, true));
                    networkManager.sendPacket(new CPacketLoginStart(mc.getSession().getProfile()));
                }
                catch (UnknownHostException unknownhostexception)
                {
                    mc.displayGuiScreen(new GuiMainMenu());
                }
                catch (Exception exception)
                {

                    String s = exception.toString();

                    if (inetaddress != null)
                    {
                        String s1 = inetaddress + ":" + port;
                        s = s.replaceAll(s1, "");
                    }
                    mc.displayGuiScreen(new GuiMainMenu());

                }
            }
        }).start();
    }
}
