package com.example.proj1;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    Connection con;
    @SuppressLint("NewApi")
    String uname, pass, ip, port, database;

    public Connection connectionclass(){
        ip="127.0.0.1"; //ip="10.192.241.71";

        database="sqldom";
        uname="root";
        pass="123456";
        port="3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        con=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+uname+";password"+pass+";");
        }
        catch (Exception ex){
            Log.e("Error1:", ex.getMessage());
        }
        return con;
    }
}
