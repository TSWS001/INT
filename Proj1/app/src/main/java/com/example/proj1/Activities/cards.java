package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.example.proj1.RecyclerViewAdapter;
import com.example.proj1.Server.SQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class cards extends AppCompatActivity {

    ArrayList<Product> productsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        SetUpProducts();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,productsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void SetUpProducts() {
        String[] productbarcode ={"bar1","bar2","bar3"} ;//getResources().getStringArray()
        String[] productnames = {"name1","name2","name3"};//getResources().getStringArray()
        String[] productcaducity= {"cad1","cad2","cad3"};
//        int[] productquantity;
//        int[] productbasearea;
//        int[] productbaseremain;
//        int[] productbaseweight;

        int i;
        for(i=0; i<productbarcode.length; i++){
            //create object and add all the string/attributes into it
//            prod_aux.barcode=productbarcode[i];
//            prod_aux.name=productnames[i];
//            //prod_aux.caducity=productcaducity[i];
//            prod_aux.quantity=i;
//            prod_aux.base_area=0;
//            prod_aux.remain_product=99;
//            prod_aux.total_weight=100;
            productsModels.add(new Product(productbarcode[i],productnames[i],
                    productcaducity[i],i,0,0,0) );
            //falta por completar los atributos de la clase Product
        }

    }

}


