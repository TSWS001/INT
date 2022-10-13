package com.example.proj1;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    Connection con;
    String uname, pass, ip, port, database;

    public Connection connectionclass(){
        ip="127.0.0.1";
        database="sqldom";
        uname="root";
        pass="123456";
        port="3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        con=null;
        String ConnectionURL= null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL ="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+uname+";password"+pass+";";
            con= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return con;
    }
}
