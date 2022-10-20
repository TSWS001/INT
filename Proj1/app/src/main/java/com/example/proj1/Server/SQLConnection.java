package com.example.proj1.Server;

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
        ip="localhost"; //ip="192.168.1.131";

        database="sqldom";
        uname="root";
        pass="123456";
        port="3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        con=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String con1 ="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+uname+";password"+pass+";";
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,uname,pass);
        }
        catch (Exception ex){
            Log.e("Error1:", ex.getMessage());
        }
        return con;
    }
}
