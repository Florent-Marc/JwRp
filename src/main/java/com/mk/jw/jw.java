package com.mk.jw;

import com.mk.jw.Utils.Bot;
import com.mk.jw.Utils.Command;
import com.mk.jw.Utils.Logs;
import com.mk.jw.Utils.Utils;
import com.mk.jw.listener.Client;
import com.mk.jw.listener.Network;
import com.mk.jw.listener.Serveur;
import com.mk.jw.network.PacketSetSpeed;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.Display;

@Mod(
        modid = Utils.MODID,
        name = Utils.MODNAME,
        version = Utils.VERSION
)
public class jw {

    @Mod.Instance(Utils.MODID)
    public static jw INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new Network());

        if(e.getSide().isClient()) {
            Utils.seticon();
            Display.setTitle("JwRp - "+ Minecraft.getMinecraft().getSession().getUsername());
            MinecraftForge.EVENT_BUS.register(new Client());
        }
        if(e.getSide().isServer()){
            MinecraftForge.EVENT_BUS.register(new Serveur());
        }
    }
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent e)  {
        if(e.getSide().isServer()){
            //Bot.LoadConfig();
            //Bot.start();
        }

    }
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new Command());
    }
    @Mod.EventHandler
    public void FMLServerStoppedEvent(FMLServerStoppedEvent e)  {
        if(e.getSide().isServer()) {
            //Logs.sendlog("Serveur stopper", 3);
            //Bot.stop();
        }
    }

}
