package com.mk.jw.juralink;

import com.mk.jw.Utils.Utils;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jline.utils.Log;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Utils.MODID, value = {Side.SERVER})
public class ElectricityLink {



    public static int prodkwh=0;
    public static int consokwh=0;

    //public static int capciterstockage=0;
    public static int utilsation=0; //en pourcentage
    public static boolean status=false;
    public static String stability= "Normal";

    public static int counter = 0;
    public static int timer = 40*10;//60s

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
                Log.info("Production : "+getProdkwh());
            }catch (Exception e){
                Log.info("System ElectricityLink failure");
            }
        }else{
            counter--;
        }
    }
    public static boolean checksutilisation(){
        utilsation=consokwh*100/prodkwh;
        return true;
    }
    public static boolean checksurcharge(){
        if(consokwh>prodkwh){
            status=false;
        }
        return true;
    }
    public static boolean checkalerte(){
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
        return true;
    }
    public static boolean trytostart(){
        if(consokwh<prodkwh){
            status=true;
        }
        return true;
    }
    public static boolean checkprod(){
        //methode pour recuper un par generateur
        //add si geneteur is good

        return true;
    }

    public static void setProdkwh(int prodkwh) {
        ElectricityLink.prodkwh = prodkwh;
    }

    public static int getProdkwh() {
        return prodkwh;
    }

    public static int getConsokwh() {
        return consokwh;
    }


    public static void checklist(BlockPos pos) {
        if(getstatus(pos)){
            ElectricityLink.setProdkwh(getProdkwh()-120);
            String v = pos.getX()+":"+pos.getY()+":"+pos.getZ();
            getGenerateur().remove(v);
        }
    }
    public static void addlist(BlockPos pos) {
        String v = pos.getX()+":"+pos.getY()+":"+pos.getZ();
        getGenerateur().put(v,false);
    }
    public static void updatelist(BlockPos pos,Boolean status) {
        String v = pos.getX()+":"+pos.getY()+":"+pos.getZ();
        getGenerateur().replace(v,status);
    }

    public static HashMap<String, Boolean> generateur = new HashMap<>();
    public static HashMap<String,Boolean> getGenerateur() {
        return generateur;
    }
    //pos
    // true ou false



    public static boolean getstatus(BlockPos pos){
        boolean reg = false;
        int posx = pos.getX();
        int posy = pos.getY();
        int posz = pos.getZ();
        HashMap<String, Boolean> generateur = getGenerateur();
        for (HashMap.Entry<String, Boolean> entry : generateur.entrySet()) {
            String rg = entry.getKey();
            Boolean load = entry.getValue();
            //System.out.println(rg);
            int cposx = 0;//load
            int cposy = 0;//load
            int cposz = 0;//load

            //load les variables des limites des zones
            String[] arrOfStr = rg.split(":");
            int count = 1;
            for (String a: arrOfStr){
                if(count==3){
                    cposz = Integer.parseInt(a);
                    count=4;
                }
                if(count==2){
                    cposy = Integer.parseInt(a);
                    count=3;
                }
                if(count==1){
                    cposx = Integer.parseInt(a);
                    count=2;
                }
            }
            if ((posx==cposx)&&(posy==cposy)&&(posz==cposz)) {
                return load;
            }else{
                return reg;
            }
        }
        //System.out.println(reg);
        return reg;
    }
}
