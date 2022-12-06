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
        Bundle extra = getIntent().getBundleExtra("product");

        ArrayList<String> productbarcode = new ArrayList<>();
        ArrayList<String> productnames = new ArrayList<>();
        ArrayList<String> productcaducity = new ArrayList<>();
        ArrayList<Integer> productquantity = new ArrayList<>();
        ArrayList<Integer> productbasearea = new ArrayList<>();
        ArrayList<Integer> productbaseremain = new ArrayList<>();
        ArrayList<Integer> productbaseweight = new ArrayList<>();

//"bar1","bar2","bar3","bar4","bar5","bar6","bar7"};
// {"name1","name2","name3","name4","name5","name6","name7"};
// {"cad1","cad2","cad3","cad4","cad5","cad6","cad7"};

        if (extra!=null){
            Product new_product = (Product) extra.getSerializable("product");
            productbarcode.add(new_product.barcode);
            productnames.add(new_product.name);
            productcaducity.add(new_product.caducity);
            productquantity.add(new_product.quantity);
            productbasearea.add(new_product.base_area);
            productbaseremain.add(new_product.remain_product);
            productbaseweight.add(new_product.total_weight);
        }

        int i;
        for(i=0; i<productbarcode.size(); i++){
            //create object and add all the string/attributes into it
            productlist.add(new Product(productbarcode.get(i),productnames.get(i),
                    productcaducity.get(i),productquantity.get(i),productbasearea.get(i),
                    productbaseremain.get(i),productbaseweight.get(i)) );
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ActivityList.this, ActivityProdInfo.class);

        intent.putExtra("NAME",productlist.get(position).getName());
        intent.putExtra("CADUCITY",productlist.get(position).getCaducity());
        intent.putExtra("PERCENT",productlist.get(position).getRemain_product());
        intent.putExtra("QUANTITY_STATE",productlist.get(position).RemainProductToText());
        intent.putExtra("INGREDIENTS",productlist.get(position).getIngredients());
        //aqui recibe el array de float de nutritional info
        float[] arraynutri= productlist.get(position).getNutricionalInfo();
        intent.putExtra("NUTRI_GRASAS",arraynutri[0]);
        intent.putExtra("NUTRI_GRASAS_SAT",arraynutri[1]);
        intent.putExtra("NUTRI_HIDCARB",arraynutri[2]);
        intent.putExtra("NUTRI_AZUCARES",arraynutri[3]);
        intent.putExtra("NUTRI_FIBRA",arraynutri[4]);
        intent.putExtra("NUTRI_PROT",arraynutri[5]);
        intent.putExtra("NUTRI_SAL",arraynutri[6]);

        startActivity(intent);

    }

    @Override
    public void onItemLongClick(int position) {
        productlist.remove(position);
        adapter.notifyItemRemoved(position);
    }
}


