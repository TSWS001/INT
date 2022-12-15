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
import com.example.proj1.R;
import com.google.gson.Gson;

import java.util.*;

public class ActivityRegister extends AppCompatActivity {
    ArrayList<Customer> users = new ArrayList<>(); //array of users
    Customer user; //new user
    boolean validcad= true, emptycad, mandatoryfilled=true;
    Button btnAceptar,btnlogin;
    EditText first_name,last_name, email, password,password2, birth_day, birth_month, birth_year, phone;
    //habria que hacer un atributo de la clase customer de si el usuario esta o no registrado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        btnlogin = findViewById(R.id.btnlogin);
        btnAceptar = findViewById(R.id.btnaceptar_register);
        first_name = findViewById(R.id.textfirstname);
        last_name = findViewById(R.id.textlastname);
        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        password2 = findViewById(R.id.text_password2);
        birth_day = findViewById(R.id.birth_day);
        birth_month = findViewById(R.id.birth_month);
        birth_year = findViewById(R.id.birth_year);
        phone = findViewById(R.id.text_phone);

        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SetUserData();
                if (!validcad)
                    Toast.makeText(ActivityRegister.this, "fecha de nacimiento invalida", Toast.LENGTH_SHORT).show();
                else if (!password.getText().toString().equals(password2.getText().toString()))
                    Toast.makeText(ActivityRegister.this, "Las contraseñas debe ser identicas", Toast.LENGTH_SHORT).show();
                else if (!mandatoryfilled){
                    Toast.makeText(ActivityRegister.this, "Los campos con * son obligatorios", Toast.LENGTH_SHORT).show();
                }
                else {
                    GuardarUsuario();
                    GuardarDatos();
                    Intent i = new Intent(ActivityRegister.this,MainActivity.class);
                    i.putExtra("NAME",user.first_name);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRegister.this,ActivityLogin.class));
                finish();

            }
        });
    }

    private void SetUserData(){
        //campos obligatorios
        String first_name_data = first_name.getText().toString();
        String last_name_data = last_name.getText().toString();
        String email_data = email.getText().toString();
        String password_data = password.getText().toString();
        //campos opcionales
        String birth_data = birth_day.getText().toString()+"/"+birth_month.getText().toString()+"/"+birth_year.getText().toString();
        String phone_data = phone.getText().toString();
        //Log.i("aaaaaaaaaaaaaaaaaa:",Integer.parseUnsignedInt(birth_day.getText().toString())+" "+Integer.parseUnsignedInt(birth_month.getText().toString())+" "+Integer.parseUnsignedInt(birth_year.getText().toString()));


        mandatoryfilled= (first_name_data.length()>0 && last_name_data.length()>0 && email_data.length()>0 && password_data.length()>0 );
        emptycad = birth_data.equals("//");

        user = new Customer(email_data,first_name_data, last_name_data, password_data);

        if (emptycad)
            user.setBirth_date("");
        else{
            validcad = user.isValidBirthDate(Integer.parseUnsignedInt(birth_day.getText().toString()),Integer.parseUnsignedInt(birth_month.getText().toString()),Integer.parseUnsignedInt(birth_year.getText().toString()));
            user.setBirth_date(birth_data);
        }
        user.setPhone(phone_data);

        Log.i("validcad:", String.valueOf(validcad));
        Log.i("manatoryfilled:", String.valueOf(mandatoryfilled));
        Log.i("first_name:",user.first_name);
    }

    public void GuardarDatos(){
        SharedPreferences preferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json;

        json = gson.toJson(users);
        UserArrayPrint();//debug
        editor.putString("userslist",json);

        editor.apply();
    }

    public void GuardarUsuario(){
        int i;
        for (i=0; i<users.size(); i++) {
            if (Objects.equals(users.get(i).email, user.email)){
                Toast.makeText(ActivityRegister.this,"Usuario ya registrado",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        users.add(user);
    }

    public void UserArrayPrint(){
        int i;
        for (i=0; i<users.size(); i++){
            Log.i("bbbbbbbbbbbbbbbbbb","usuario numero "+i+" "+users.get(i).first_name+" "+users.get(i).last_name);
        }
    }
}
