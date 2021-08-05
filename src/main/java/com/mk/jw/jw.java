package com.mk.jw;

import com.mk.jw.Utils.*;
import com.mk.jw.blocks.block.BlockGenerateur;
import com.mk.jw.blocks.GuiHandler;
import com.mk.jw.items.ItemBidon;
import com.mk.jw.items.ItemCarte;
import com.mk.jw.blocks.tile.TEGenerateur;
import com.mk.jw.listener.Client;
import com.mk.jw.listener.Network;
import com.mk.jw.listener.Serveur;
import fr.dynamx.api.contentpack.DynamXAddon;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.lwjgl.opengl.Display;

import static com.mk.jw.Utils.Utils.VERSION;

@Mod(
        modid = Utils.MODID,
        name = Utils.MODNAME,
        version = VERSION
)
public class jw {

    @Mod.Instance(Utils.MODID)
    public static jw INSTANCE;
    public static CreativeTabs juraTabs = new JuraTabs("machine");
    public static BlockGenerateur test = new BlockGenerateur(Material.ANVIL,Utils.MODID,"generateur","generateur/generateur.obj");
    public static ItemBidon bidon= new ItemBidon(Utils.MODID,"bidon","bidon/bidon.obj");
    public static ItemCarte carte= new ItemCarte(Utils.MODID,"carte","carte/carte.obj");


    @DynamXAddon.AddonEventSubscriber
    public static void init()
    {

    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new Network());
        GameRegistry.registerTileEntity(TEGenerateur.class, new ResourceLocation(Utils.MODID, "tilegenerateur"));
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
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
    public void serverStarting(FMLServerStartingEvent e)
    {
        e.registerServerCommand(new Command());
        if(e.getSide().isServer()) {
            FMLServerHandler.instance().getServer().getWorld(1).setWorldTime(Serveur.worldtime);
        }
    }
    @Mod.EventHandler
    public void FMLServerStoppedEvent(FMLServerStoppedEvent e)  {
        if(e.getSide().isServer()) {
            //Logs.sendlog("Serveur stopper", 3);
            //Bot.stop();
        }
    }

}
