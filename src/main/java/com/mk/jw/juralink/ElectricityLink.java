package com.mk.jw.juralink;

import com.mk.jw.Utils.Utils;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.jline.utils.Log;

@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.SERVER})
public class ElectricityLink {

    public static int prodkwh=0;
    public static int consokwh=0;

    //public static int capciterstockage=0;
    public static int utilsation=0; //en pourcentage
    public static boolean status=false;
    public static String stability= "Normal";

    public static int counter = 0;
    public static int timer = 1200*5;//5s

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (counter==0){
            counter = timer;
            try {
                checkprod();
                checksutilisation();
                checkalerte();
                checksurcharge();
                Log.info("System ElectricityLink update");
            }catch (Exception e){
                Log.info("System ElectricityLink failure");
            }
        }else{
            counter--;
        }
    }
    public static void checksutilisation(){
        utilsation=consokwh*100/prodkwh;
    }
    public static void checksurcharge(){
        if(consokwh>prodkwh){
            status=false;
        }
    }
    public static void checkalerte(){
        if(utilsation<70){
            stability="Normal";
        }
        if(utilsation>=70&&utilsation<80){
            stability="Warning";
        }
        if(utilsation>=80&&utilsation<90){
            stability="Limite";
        }
        if(utilsation>90){
            stability="Danger";
        }
    }
    public static void trytostart(){
        if(consokwh<prodkwh){
            status=true;
        }
    }
    public static void checkprod(){
        //methode pour recuper un par generateur
        //add si geneteur is good
    }
}
