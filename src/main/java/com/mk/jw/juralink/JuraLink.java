package com.mk.jw.juralink;

import com.mk.jw.Utils.Profils;
import com.mk.jw.Utils.Utils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.SERVER})
public class JuraLink {
    private static Profils INSTANCE;

    private static int panne=0;
    private static int probability=0;
    private static int na=1;
    private static int nvsp=1;
    private static int MS=0;
    private static int MR=0;
    private static Double revenue=0.0;
    private static Double depences=0.0;
    private static Double Argent=0.0;

}
