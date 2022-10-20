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
        setContentView(R.layout.productlist);

        ImageView leftIcon = findViewById(R.id.left_icon);
        ImageView rightIcon = findViewById(R.id.right_icon);
    }
        public void Pasardeactivity(View v) {
            Intent i = new Intent(this, cards.class);
            Toast.makeText(this, "left icon clicked", Toast.LENGTH_SHORT).show();
            startActivity(i);
            }

}

