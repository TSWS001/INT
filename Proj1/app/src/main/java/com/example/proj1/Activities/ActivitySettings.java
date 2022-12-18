package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.R;

public class ActivitySettings extends AppCompatActivity {
    Button btnlogout, btnpersonaldata;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnlogout = findViewById(R.id.btnlogout);
        btnpersonaldata = findViewById(R.id.btnpersonaldata);
        back = findViewById(R.id.back_settings);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySettings.this,ActivityLogin.class));
                finish();   //la idea es volver a login, y no poder acceder a main con un retroceder (back)
            }
        });

        btnpersonaldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySettings.this,ActivityCondPol.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySettings.this,MainActivity.class));
            }
        });

    }
}
