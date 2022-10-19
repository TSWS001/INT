package com.example.proj1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class cards extends AppCompatActivity {

    ArrayList<Product> productsModels = new ArrayList<>();
    Connection connection;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        //        SetUpProducts();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,productsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void GetTextFromSQL(View v) {
        TextView word1 = (TextView) findViewById(R.id.text);
        TextView word2 = (TextView) findViewById(R.id.text2);

        SQLConnection SQLobj = new SQLConnection();
        connection = SQLobj.connectionclass(); //Empieza la ejecucion de la connexion
        if (SQLobj != null) {

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
                Log.e("Error2:", e.getMessage());
            }
        }
        else{
            ConnectionResult="Check Connection c";
        }
    }



//    public void SetUpProducts {
//
//    String productnames[] = getResources().getStringArray();
//    String productcaducity[] = getResources().getStringArray();
//    // String quantity[]
//
//    int i;
//    for(i=0; i<productsModels.length; i++){
//        //create object and add all the string into it
//      productsModels.add(new Product(productnames[i],productcaducity[i],...));//falta por completar los atributos de la clase Product
//    }
//
//    }

}


