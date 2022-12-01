package com.example.proj1.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.R;

public class ActivityCondPol extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cond_politics);

        back = findViewById(R.id.left_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityCondPol.this,ActivitySettings.class));
            }
        });
    }
}
