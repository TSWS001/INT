package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proj1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_Proj1);// set the theme proj1 after splash screen
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
        if(v==findViewById(R.id.img11)){
            Toast.makeText(this, "ic_camera clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ActivityEscaneo.class));
        }
        else if(v==findViewById(R.id.img12)){
            Toast.makeText(this, "ic_list clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ActivityList.class));
        }
        else if(v==findViewById(R.id.img21)){
            Toast.makeText(this, "Shopping function is coming soon...", Toast.LENGTH_SHORT).show();
            //activityshopping
        }
        else if(v==findViewById(R.id.img22)){
            Toast.makeText(this, "Diet function is coming soon...", Toast.LENGTH_SHORT).show();
            //activitydiet
        }
    }



}

