package com.mk.jw.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class Rg {
    public static String firstpoint;
    public static String secondpoint;
    public static void setfirstpoint(EntityPlayer p){
        BlockPos loc = p.getPosition();
        firstpoint = loc.getX()+":"+loc.getY()+":"+loc.getZ();
    }
    public static void setSecondpoint(EntityPlayer p){
        BlockPos loc = p.getPosition();
        secondpoint = loc.getX()+":"+loc.getY()+":"+loc.getZ();
    }

    public static void addregion(String name){
        String reg = name;
        String zone = firstpoint+":"+secondpoint;
        //send to serveur
    }
    public static void removeregion(String name){
        String reg = name;
        //send to serveur

    }
    public static HashMap<String, String> Rglist = new HashMap<String, String>();
    public static HashMap<String,String> getRglist() {
        Rglist.put("Diamant", "-1:0:0:-10:0:0");
        Rglist.put("Or", "2:6:20:10:60:80");
        return Rglist;
    }
    public static String getregion(EntityPlayer p){
        String reg = null;
        int Vx = p.getPosition().getX();
        int Vy = p.getPosition().getY();
        int Vz = p.getPosition().getZ();
        //System.out.println(x+" "+y+" "+z);
        HashMap<String, String> rglist = getRglist();
        //System.out.println("load list");
        //load les zones
        for (HashMap.Entry<String, String> entry : rglist.entrySet()) {
            String rg = entry.getKey();
            String load = entry.getValue();
            //System.out.println(rg);
            int R1x = 0;//load
            int R1y = 0;//load
            int R1z = 0;//load

            int R2x = 0;//load
            int R2y = 0;//load
            int R2z = 0;//load
            //load les variables des limites des zones
            String[] arrOfStr = load.split(":");
            int count = 1;
            for (String a: arrOfStr){
                if(count==6){
                    R2z = Integer.parseInt(a);
                }
                if(count==5){
                    R2y = Integer.parseInt(a);
                    count=6;
                }
                if(count==4){
                    R2x = Integer.parseInt(a);
                    count=5;
                }
                if(count==3){
                    R1z = Integer.parseInt(a);
                    count=4;
                }
                if(count==2){
                    R1y = Integer.parseInt(a);
                    count=3;
                }
                if(count==1){
                    R1x = Integer.parseInt(a);
                    count=2;
                }
            }
            //System.out.println(R1x+" "+R1y+" "+R1z);
            //verif si le joueur est ds la rg
            boolean v1 = false, v2 = false, v3 = false;

            if ((Vx >= R1x && Vx <= R2x)||(Vx <= R1x && Vx >= R2x)) {
                v1 = true;
            }
            if ((Vy >= R1y && Vy <= R2y)||(Vy <= R1y && Vy >= R2y)) {
                v2 = true;
            }
            if ((Vz >= R1z && Vz <= R2z)||(Vz <= R1z && Vz >= R2z)) {
                v3 = true;
            }
            if (v1 && v2 && v3) {
                reg = rg;
                System.out.println("Vous etes ds la region : "+reg);
                return reg;
            }

            //resultz = resultz * (-1);
        }
        System.out.println(reg);
        return reg;
    }
}
