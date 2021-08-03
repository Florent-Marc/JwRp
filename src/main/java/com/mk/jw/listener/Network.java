package com.mk.jw.listener;

import com.mk.jw.network.PacketGui;
import com.mk.jw.network.PacketSetSpeed;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Network {
    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("jw");
        network.registerMessage(PacketSetSpeed.handler.class,PacketSetSpeed.class,0,Side.SERVER);
        network.registerMessage(PacketGui.handler.class,PacketGui.class,1,Side.CLIENT);
    }
}
