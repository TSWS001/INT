package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.R;


public class ActivityInicio extends AppCompatActivity
{
    private final int DURACION_SPLASH = 2000; // 2 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicial);

        new Handler().postDelayed(() -> {
            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
            Intent intent = new Intent(ActivityInicio.this, ActivityEscaneo.class);
            startActivity(intent);
            finish();
        }, DURACION_SPLASH);
    }
}
