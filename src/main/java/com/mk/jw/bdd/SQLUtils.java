package com.mk.jw.bdd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SQLUtils {
    private static String url, log, pwd;
    private static Properties props = new Properties();
    private static Connection c;


    public static void init(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(new FileReader(new File("mods/setup/bdd.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        url = props.getProperty("url");
        log = props.getProperty("login");
        pwd = props.getProperty("pwd");

        System.out.println("\n------------------------------------------\n"+
                "Config chargee"+
                "\n------------------------------------------\n");
    }

    private static Connection getConnexion(){
        try {
            if(c == null || c.isClosed()){
                c = DriverManager.getConnection(url, log, pwd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }


    public static void execute(String query){
        Statement s = null;
        try {
            s = getConnexion().createStatement();
            if(s == null){
                throw new IllegalArgumentException("s is null silly");
            }
            s.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                getConnexion().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void execute(String query, Object... args){
        PreparedStatement s = null;
        try{
            s= getConnexion().prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try{
            for (int i = 1; i <= args.length; i++) {
                s.setString(i, args[i-1].toString());
            }
            s.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                getConnexion().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static QueryResult getData(String query){
        Statement s = null;
        ResultSet r = null;
        ArrayList<Object> result = new ArrayList<>();
        try {
            s = getConnexion().createStatement();
            if(s == null){
                throw new IllegalArgumentException("s is null silly");
            }
            r = s.executeQuery(query);
            int i = 1;
            if (r.next()) {
                do{
                    result.add(i-1, r.getObject(i));
                    i++;
                } while(r.next());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                getConnexion().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return new QueryResult(s,result);
    }

    public static QueryResult getData(String query, Object... args){
        PreparedStatement s =null;
        ResultSet r;
        ArrayList<Object> result = new ArrayList<>();
        try{
            s = getConnexion().prepareStatement(query);
            for (int i = 0; i < args.length; i++) {
                s.setString((i+1), args[i].toString());

            }
            r = s.executeQuery();
            int i = 1;
            if (r.next()) {
                do{
                    result.add(i-1, r.getObject(i));
                    i++;
                } while(r.next());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                getConnexion().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new QueryResult(s, result);

    }



}
