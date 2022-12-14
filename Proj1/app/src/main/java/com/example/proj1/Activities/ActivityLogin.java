package com.example.proj1.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class ActivityLogin extends AppCompatActivity {
    Button btnAceptar, btnRegistrarse;
    EditText email, password;
    ArrayList<Customer> users;
    Customer user_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        getUserData();

        btnAceptar = findViewById(R.id.btnaceptar_login);
        btnRegistrarse = findViewById(R.id.btnregister);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this,ActivityRegister.class));
                finish();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this,MainActivity.class);

                int pos = CheckIdentity(email.getText().toString(),password.getText().toString());
                if (pos>=0){
                    user_current=users.get(pos);
                }
                i.putExtra("NAME",user_current.last_name);
                startActivity(i);
                finish();
            }
        });
    }
    private void getUserData(){//0 si correcto, 1 si contraseña incorrecta, 2 si no existe usuario
        SharedPreferences preferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;
        int position;
        //recuperar la lista de usuarios
        json = preferences.getString("userslist","");
//        Log.i("strinnnnnnnnnnnnnnnn:",json);//resultado default es []

        if(json.length()>2) {
            Type listType = new TypeToken<ArrayList<Customer>>(){}.getType();
            users = gson.fromJson(json, listType);
            Log.i("DebugggggggGGGGg:", "From get json " + users.get(0).first_name+" "+users.get(0).last_name);
        }

    }

    public int CheckIdentity(String email,String pass) {
        int i;
        for (i=0; i<users.size(); i++){
            if (Objects.equals(users.get(i).email, email)){ // found email
                //check pass
                if (Objects.equals(users.get(i).password, pass)){
                    //correct pass
                    return i;
                }
                else{
                    //incorrect pass
                    Toast.makeText(ActivityLogin.this,"contraseña incorrecta",Toast.LENGTH_SHORT).show();
                    return -1;
                }
            }
        }
        //fuera del for: not email found
        Toast.makeText(ActivityLogin.this,"Usuario no encontrado",Toast.LENGTH_SHORT).show();
        return -2;

    }
}
