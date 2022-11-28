package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.example.proj1.Classes.RecyclerViewAdapter;
import com.example.proj1.RecyclerViewInterface;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<Product> productlist = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);

        TextView listquantity = findViewById(R.id.product_quantity);
        ImageView btnback = findViewById(R.id.left_icon);
        ImageView btnplus = findViewById(R.id.right_icon);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityList.this,MainActivity.class));
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityList.this,ActivityEscaneo.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        SetUpProducts();

         adapter = new RecyclerViewAdapter(this,productlist, this);
        recyclerView.setAdapter(adapter);
        listquantity.setText(String.valueOf(adapter.getItemCount())); //set the value of "x products remaining"
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void SetUpProducts() {
        String[] productbarcode = {"bar1","bar2","bar3","bar4","bar5","bar6","bar7"} ;//getResources().getStringArray()
        String[] productnames = {"name1","name2","name3","name4","name5","name6","name7"};//getResources().getStringArray()
        String[] productcaducity= {"cad1","cad2","cad3","cad4","cad5","cad6","cad7"};
//        int[] productquantity;

//        int[] productbasearea;
//        int[] productbaseremain;
//        int[] productbaseweight;

        int i;
        for(i=0; i<productbarcode.length; i++){
            //create object and add all the string/attributes into it
            productlist.add(new Product(productbarcode[i],productnames[i],
                    productcaducity[i],i,0,100,500) );
            //falta por completar los atributos de la clase Product
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ActivityList.this, ActivityProdInfo.class);

        intent.putExtra("NAME",productlist.get(position).getName());
        intent.putExtra("CADUCITY",productlist.get(position).getCaducity());
        intent.putExtra("PERCENT",productlist.get(position).getRemain_product());
        intent.putExtra("QUANTITY_STATE",productlist.get(position).RemainProductToText());
        intent.putExtra("NUTRITIONAL_INFO",productlist.get(position).getNutricionalInfo());//va a ser un array de float
        intent.putExtra("INGREDIENTS",productlist.get(position).getIngredients());

        startActivity(intent);

    }

    @Override
    public void onItemLongClick(int position) {
        productlist.remove(position);
        adapter.notifyItemRemoved(position);
    }
}


