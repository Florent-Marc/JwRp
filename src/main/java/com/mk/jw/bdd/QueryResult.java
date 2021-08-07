package com.mk.jw.bdd;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryResult {
    private Statement s;
    private ArrayList<Object> r;
    public QueryResult(Statement s, ArrayList<Object> r) {
        this.s = s;
        this.r = r;
    }

    public Statement getStatement() {
        return s;
    }

    public ArrayList<Object> getResult() {
        return r;
    }

    public void close(){
        try {
            s.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
