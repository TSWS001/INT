package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.example.proj1.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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


        ImageView leftIcon = findViewById(R.id.left_icon);
        ImageView rightIcon = findViewById(R.id.right_icon);
    }
        public void NextActivity(View v) {
            Intent i = new Intent(this, cards.class);
            Toast.makeText(this, "left icon clicked", Toast.LENGTH_SHORT).show();
            startActivity(i);
            }

}

