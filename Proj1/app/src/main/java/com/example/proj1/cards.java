package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.server_test);

        Button boton1 = (Button) findViewById(R.id.button1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView word1 = (TextView) findViewById(R.id.text);
                TextView word2 = (TextView) findViewById(R.id.text2);

                SQLConnection c = new SQLConnection();
                connection = c.connectionclass(); //Empieza la ejecucion de la connexion
                if (c != null) {

                    try {
                        // Creacion del statement que se ejecutara
                        Statement smt = connection.createStatement();
                        // Ejecucion del statement
                        ResultSet set = smt.executeQuery("Select * from customers where customer_id = '1'");
                        while (set.next()) { //Copiar los datos de sql a los Textview del layout
                            word1.setText(set.getString(2));
                            word2.setText(set.getString(3));
                        }
                        connection.close();
                    } catch (Exception e) {
                        Log.e("Error:", e.getMessage());
                    }
                }
                else{
                    ConnectionResult="";
                }
            }
        });

    }

}
