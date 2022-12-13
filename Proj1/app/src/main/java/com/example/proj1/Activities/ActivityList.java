package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        ArrayList<String> productbarcode = new ArrayList<>();
        ArrayList<String> productnames = new ArrayList<>();
        ArrayList<String> productcaducity = new ArrayList<>();
        ArrayList<Integer> productquantity = new ArrayList<>();
        ArrayList<Integer> productbasearea = new ArrayList<>();
        ArrayList<Integer> productbaseremain = new ArrayList<>();
        ArrayList<Integer> productbaseweight = new ArrayList<>();

        Product new_product = (Product) getIntent().getSerializableExtra("product");
        if(new_product!=null){
            productlist.add(new_product);
//            productbarcode.add(new_product.barcode);
//            productnames.add(new_product.name);
//            productcaducity.add(new_product.caducity);
//            productquantity.add(new_product.quantity);
//            productbasearea.add(new_product.base_area);
//            productbaseremain.add(new_product.remain_product);
//            productbaseweight.add(new_product.total_weight);
            Log.i("Debugggggggg:","Adding "+new_product.ingredients);
        }
        else
            Log.i("xxxxxxxxxxinlei:","the product added is null.Error from ActivityList");

//        int i;
//        for(i=0; i<productbarcode.size(); i++){
//            //create object and add all the attributes into it
//            productlist.add(new Product(productbarcode.get(i),productnames.get(i),
//                    productcaducity.get(i),productquantity.get(i),productbasearea.get(i),
//                    productbaseremain.get(i),productbaseweight.get(i)));
//        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ActivityList.this, ActivityProdInfo.class);

        Product prod= productlist.get(position);

        intent.putExtra("NAME",prod.getName());
        intent.putExtra("CADUCITY",prod.getCaducity());
        intent.putExtra("PERCENT", prod.getRemain_product());
        intent.putExtra("QUANTITY_STATE",prod.RemainProductToText());
        intent.putExtra("INGREDIENTS",prod.getIngredients());

//        aqui recibe el array de float de nutritional info
        intent.putExtra("NUTRI_GRASAS",prod.getNutricionalInfo()[0]);
        intent.putExtra("NUTRI_GRASAS_SAT",prod.grasas_sat);
        intent.putExtra("NUTRI_HIDCARB",prod.hid_carb);
        intent.putExtra("NUTRI_AZUCARES",prod.azucares);
        intent.putExtra("NUTRI_FIBRA",prod.fibra);
        intent.putExtra("NUTRI_PROT",prod.proteinas);
        intent.putExtra("NUTRI_SAL",prod.sal);

        Log.i("Debugggggggg:","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+prod.getNutricionalInfo()[0]);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        productlist.remove(position);
        adapter.notifyItemRemoved(position);
    }
}


