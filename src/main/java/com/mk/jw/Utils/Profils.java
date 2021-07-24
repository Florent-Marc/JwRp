package com.mk.jw.Utils;

import com.mk.jw.listener.Network;
import com.mk.jw.network.PacketUpdateServer;
import net.minecraft.client.Minecraft;

public class Profils {

    private static Profils INSTANCE;

    private static String Nom ="";
    private static String Prenom="";
    private static String Sex="";
    private static String Age="";
    private static String Team="";
    private static int xp=0;
    private static int niveausup=0;
    private static int lvl=0;
    private static Double Argent=1500.0;

    public static String getNom(){return Nom;}
    public static String getPrenom(){return Prenom;}
    public static String getSex(){return Sex;}
    public static String getTeam(){return Team;}
    public static String getAge(){return Age;}
    public static Double getArgent(){return Argent;}
    public static int getxp(){return xp;}
    public static int getlvl(){return lvl;}
    public static int getniveausup(){return niveausup;}

    public static void setPrenom(String prenom){
        Prenom = prenom;
    }
    public static void setNom(String nom){
        Nom = nom;
    }
    public static void setTeam(String Team){
        Nom = Team;
    }
    public static void setSex(String sex){
        Sex = sex;
    }
    public static void setLvl(String lvl){
        Age = lvl;
    }
    public static void setAge(String age){
        Age = age;
    }
    public static void setArgent(Double argent){ Argent = argent;}

    public static void save(){
        Network.network.sendToServer(new PacketUpdateServer(Minecraft.getMinecraft().player, getNom(),getPrenom(),getSex(),getAge(),getArgent()));
    }
    public static void reset(){
        setPrenom("");
        setNom("");
        setSex("");
        setAge("");
        setArgent(0.0);
        Network.network.sendToServer(new PacketUpdateServer(Minecraft.getMinecraft().player, getNom(),getPrenom(),getSex(),getAge(),getArgent()));
    }
    public static void load(String prenom,String nom,String sex,String age,int rib,int cb,double argent){
        setPrenom(prenom);
        setNom(nom);
        setSex(sex);
        setAge(age);
        setArgent(argent);
    }
}
