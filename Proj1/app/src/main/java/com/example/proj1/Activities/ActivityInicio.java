package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.R;


public class ActivityInicio extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicial);

        // 2 segundos
        int DURACION_SPLASH = 2000;
        new Handler().postDelayed(() -> {
            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
            Intent intent = new Intent(ActivityInicio.this, ActivityLogin.class);
            startActivity(intent);
            finish();
            Log.d("Handler","Running Handler at Inicio");
        }, DURACION_SPLASH);
    }
}
