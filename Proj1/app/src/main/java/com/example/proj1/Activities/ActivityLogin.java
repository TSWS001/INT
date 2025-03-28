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
    ArrayList<Customer> users = new ArrayList<>();
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
                UserArrayPrint();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this,MainActivity.class);

                int pos = CheckIdentity(email.getText().toString(),password.getText().toString());
                Log.i("ActLogin :","position del usuario: "+pos);
                UserArrayPrint(); // debug
                if (pos>=0) {
                    user_current = users.get(pos);
                    i.putExtra("NAME",user_current.first_name);
                    startActivity(i);
                    finish();
                }
//                else{
//                    Toast.makeText(ActivityLogin.this," denegada",Toast.LENGTH_SHORT).show();
//                }

            }
        });
    }
    private void getUserData(){
        SharedPreferences preferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;

        //recuperar la lista de usuarios
        json = preferences.getString("userslist","");

        if(json.length()>2) {
            Type listType = new TypeToken<ArrayList<Customer>>(){}.getType();
            users = gson.fromJson(json, listType);
            // si existe el users leemos el primer usuario
            Log.i("DebugggggggGGGGg:", "From get json " + users.get(0).first_name+" "+users.get(0).last_name);
        }

    }

    public int CheckIdentity(String email,String pass) {
        int i;
        for (i=0; i<users.size(); i++){
            Log.i("strinnnnnnnnnnnnnnnn:", users.get(i).email);
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
        Toast.makeText(ActivityLogin.this,"Usuario "+email+" no encontrado",Toast.LENGTH_SHORT).show();
        return -2;

    }

    public void UserArrayPrint(){
        int i;
        for (i=0; i<users.size(); i++){
            Log.i("bbbbbbbbbbbbbbbbbb","usuario numero "+i+" "+ users.get(i).email+" "+users.get(i).first_name+" "+users.get(i).last_name+" "+users.get(i).password);

        }
    }
}
