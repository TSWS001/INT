package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class cards extends AppCompatActivity {

    //Connection connection;
    //String ConnectionResult="";
    
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view);

        TextView boton_aux = (TextView) findViewById(R.id.text_showquantity);

        boton_aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.text_productname);
                SQLConnection c = new SQLConnection();
                connection = c.connectionclass();
                if (c != null) {

                    try {
                        String sqlstatement = "Select * from customers";
                        Statement smt = connection.createStatement();
                        ResultSet set = smt.executeQuery(sqlstatement);
                        while (set.next()) {
                            name.setText(set.getString(2));
                        }
                        connection.close();
                    } catch (Exception e) {
                        Log.e("Error:", e.getMessage());
                    }
                }
            }
        });


    }

}
