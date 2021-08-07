package com.mk.jw.bdd;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;

public class MethodeBDD {

    // a update
    /*
    public static void CreateProfil(EntityPlayer p,String Prenom,String Nom,String Team,int Teamlvl,Double Argent,int Job,int Race,int Cabale,String Maison){
        SQLUtils.execute("INSERT INTO `players` (`uuid`,`Prenom`, `Nom`, `Team`, `Teamlvl`,`Argent`,`Job`,`Race`,`Cabale`,`Maison`) VALUES ('"+p.getUniqueID().toString()+"','"+Prenom+"','"+Nom+"','"+Team+"','"+Teamlvl+"','"+Argent+"','"+Job+"','"+Race+"','"+Cabale+"','"+Maison+"')");

    }
    public static boolean getUuidExist(EntityPlayer p){
        boolean exist;
        String uuid = p.getUniqueID().toString();
         QueryResult qr = SQLUtils.getData("SELECT * FROM players where uuid = ?", uuid);
        exist = qr.getResult().size() >0;
        return exist;

    }
    public static boolean getVilleExist(String p){
        boolean exist = false;
        String uuid = p;
        QueryResult qr = SQLUtils.getData("SELECT Nom FROM ville WHERE Nom= ?", uuid);
        exist = p.equals(qr.getResult().get(0));
        return exist;

    }

     */
    public static String getNom(EntityPlayer p){
        String Argent;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Nom FROM players WHERE uuid= ?", uuid);
        Argent = (String) qr.getResult().get(0);
        return Argent;
    }

    public static String getPrenom(EntityPlayer p) {
        String Prenom;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Prenom FROM players WHERE uuid= ?", uuid);
        Prenom = (String) qr.getResult().get(0);
        return Prenom;
    }

    public static String getSex(EntityPlayer p) {
        String Sex;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Sex FROM players WHERE uuid= ?", uuid);
        Sex = (String) qr.getResult().get(0);
        return Sex;
    }

    public static String getAge(EntityPlayer p) {
        String Age;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Age FROM players WHERE uuid= ?", uuid);
        Age = (String) qr.getResult().get(0);
        return Age;
    }

    public static String getTeam(EntityPlayer p) {
        String Team;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Team FROM players WHERE uuid= ?", uuid);
        Team = (String) qr.getResult().get(0);
        return Team;
    }

    public static int getXp(EntityPlayer p) {
        int Xp;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Xp FROM players WHERE uuid= ?", uuid);
        Xp = (Integer) qr.getResult().get(0);
        return Xp;
    }

    public static Double getArgent(EntityPlayer p) {
        Double Argent;
        String uuid = p.getUniqueID().toString();
        QueryResult qr = SQLUtils.getData("SELECT Argent FROM players WHERE uuid= ?", uuid);
        Argent = (Double) qr.getResult().get(0);
        return Argent;
    }


    // à terminer
    /*
    public static void setArgent(EntityPlayer p, double Argent){
        String uuid = p.getUniqueID().toString();
        SQLUtils.execute("UPDATE players SET Argent = ? WHERE uuid = ?", Argent, uuid);
    }


    public static void addChunks(String team, Chunk... chunks){
        for (Chunk c : chunks){
            SQLUtils.execute("INSERT INTO chunks (x, z, team) VALUES (?, ?, ?)", c.x, c.z, team);

        }
    }
    public static void removeChunks(Chunk... chunks){
        for(Chunk c: chunks){
            SQLUtils.execute("DELETE FROM chunks WHERE x=? AND z=?", c.x, c.z);
        }
    }

    public static void editChunksTeam(String newTeam, Chunk... chunks){
        for(Chunk c: chunks){
            SQLUtils.execute("UPDATE chunks SET team = ? WHERE x=? AND z=?", newTeam, c.x, c.z);
        }
    }

     */
}
