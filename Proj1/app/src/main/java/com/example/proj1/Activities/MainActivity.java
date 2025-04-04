package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proj1.Classes.Customer;
import com.example.proj1.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_Proj1);// set the theme proj1 after splash screen
        setContentView(R.layout.activity_main);

        TextView nombre = findViewById(R.id.welcome_name);
        String name;

        name = getIntent().getStringExtra("NAME"); // leer nombre del intent
        if (name !=null){
            Log.i("Nombre del intent", name);
            GuardarNombre(name);
        }
        else {
            name=LeerNombre(); // leer nombre del preference
            Log.i("Nombre del preference",name);
        }
        Log.i("Nombre para set",name);
        nombre.setText(name);   //sets the name of the user to the main page

        ImageView ic_camera = findViewById(R.id.img11);
        ImageView ic_list = findViewById(R.id.img12);
        ImageView ic_shopping = findViewById(R.id.img21);
        ImageView ic_diet = findViewById(R.id.img22);
        ImageView ic_settings = findViewById(R.id.ic_settings);

        ic_camera.setOnClickListener(this::Clicklistener);
        ic_list.setOnClickListener(this::Clicklistener);
        ic_shopping.setOnClickListener(this::Clicklistener);
        ic_diet.setOnClickListener(this::Clicklistener);
        ic_settings.setOnClickListener(this::Clicklistener);
    }


    public void Clicklistener (View v) {
        if (v == findViewById(R.id.img11)) {
            Toast.makeText(this, "Abriendo cámara para escaneo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ActivityEscaneo.class));
        } else if (v == findViewById(R.id.img12)) {
            Toast.makeText(this, "Cargando la lista de productos", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ActivityList.class));
        } else if (v == findViewById(R.id.img21)) {
            Toast.makeText(this, "Las compras online estarán disponible próximamente", Toast.LENGTH_SHORT).show();
            //activityshopping
        } else if (v == findViewById(R.id.img22)) {
            Toast.makeText(this, "La recomendación de dietas estará disponible próximamente", Toast.LENGTH_SHORT).show();
            //activitydiet
        } else if (v == findViewById(R.id.ic_settings)) {
            //Toast.makeText(this, "ic_settings clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ActivitySettings.class));
        }
    }

    public void GuardarNombre(String name){
        SharedPreferences preferences = getSharedPreferences("nombre", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("NAME",name);
        editor.apply();
    }

    public String LeerNombre(){
        SharedPreferences preferences = getSharedPreferences("nombre", Context.MODE_PRIVATE);

        return preferences.getString("NAME","");
    }

}

