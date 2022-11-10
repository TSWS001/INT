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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ic_camera = findViewById(R.id.img11);
        ImageView ic_list = findViewById(R.id.img12);
        ImageView ic_shopping = findViewById(R.id.img21);
        ImageView ic_diet = findViewById(R.id.img22);

        ic_camera.setOnClickListener(this::Clicklistener);
        ic_list.setOnClickListener(this::Clicklistener);
        ic_shopping.setOnClickListener(this::Clicklistener);
        ic_diet.setOnClickListener(this::Clicklistener);

    }
    public void Clicklistener (View v){
        Class class_aux= this.getClass();
        Intent i = new Intent(this,class_aux);
        if(v==findViewById(R.id.img11)){
            Toast.makeText(this, "ic_camera clicked", Toast.LENGTH_SHORT).show();
            class_aux= ActivityEscanear.class;
        }
        else if(v==findViewById(R.id.img12)){
            Toast.makeText(this, "ic_list clicked", Toast.LENGTH_SHORT).show();
            class_aux= ActivityList.class;
        }
        else if(v==findViewById(R.id.img21)){
            Toast.makeText(this, "ic_shopping clicked", Toast.LENGTH_SHORT).show();
            //activityshopping
        }
        else if(v==findViewById(R.id.img22)){
            Toast.makeText(this, "ic_diet clicked", Toast.LENGTH_SHORT).show();
            //activitydiet
        }
        startActivity(i);
    }


        public void NextActivity(View v) {
            Intent i = new Intent(this, MainActivity.class);

            Toast.makeText(this, "left icon clicked", Toast.LENGTH_SHORT).show();
            startActivity(i);
            }

}

