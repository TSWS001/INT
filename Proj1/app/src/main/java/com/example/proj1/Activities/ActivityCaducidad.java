package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;

public class ActivityCaducidad extends AppCompatActivity {

    Button btnAceptar,btnlogin;
    EditText first_name,last_name, email, password, birth_day, birth_month, birth_year,prefix_phone, phone, address;
    //habria que hacer un atributo de la clase customer de si el usuario esta o no registrado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caducidad);

        btnlogin = findViewById(R.id.btnlogin);
        btnAceptar = findViewById(R.id.btnaceptar_register);
        first_name = findViewById(R.id.textfirstname);
        last_name = findViewById(R.id.textlastname);
        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        birth_day = findViewById(R.id.birth_day);
        birth_month = findViewById(R.id.birth_month);
        birth_year = findViewById(R.id.birth_year);
        prefix_phone = findViewById(R.id.text_prefix_phone);
        phone = findViewById(R.id.text_phone);
        address = findViewById(R.id.text_direccion);

        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SetUserData();
                startActivity(new Intent(ActivityRegister.this,MainActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRegister.this,ActivityLogin.class));
            }
        });

    }
}
