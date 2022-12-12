package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.R;

import java.util.*;

public class ActivityRegister extends AppCompatActivity {
    Customer user;
    boolean validcad= true, emptycad, mandatoryfilled=true;
    Button btnAceptar,btnlogin;
    EditText first_name,last_name, email, password, birth_day, birth_month, birth_year,prefix_phone, phone, address;
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
        birth_day = findViewById(R.id.birth_day);
        birth_month = findViewById(R.id.birth_month);
        birth_year = findViewById(R.id.birth_year);
        phone = findViewById(R.id.text_phone);

        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SetUserData();
                if (!validcad){
                    Toast.makeText(ActivityRegister.this, "fecha de nacimiento invalida", Toast.LENGTH_SHORT).show();
                }
                else if (!mandatoryfilled){
                    Toast.makeText(ActivityRegister.this, "Los campos con * son obligatorios", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(ActivityRegister.this,MainActivity.class);
                    i.putExtra("NAME",user.first_name);
                    startActivity(i);
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRegister.this,ActivityLogin.class));
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

        user = new Customer(first_name_data, last_name_data, email_data, password_data);
        Calendar act_date = Calendar.getInstance();
        validcad = user.isValidBirthDate(birth_day.getInputType(),birth_month.getInputType(),birth_year.getInputType(),act_date);
        emptycad = birth_data.equals("//");

        user.setPhone(phone_data);

        mandatoryfilled= (first_name_data.equals("") && last_name_data.equals("") && email_data.equals("") && password_data.equals(""));
            //probamos poner "" y si no añadimos un espacio al final del first_name_data al asignarle valor

        if (validcad) {
            if (emptycad)
                user.setBirth_date("");
            else
                user.setBirth_date(birth_data);
        }
        Log.i("validcad:", String.valueOf(validcad));
        Log.i("manatoryfilled:", String.valueOf(mandatoryfilled));
        Log.i("first_name:",user.first_name);
//            Log.i("last_name_data:",user.last_name);
//            Log.i("email_data:",user.email);
//            Log.i("password_data:",user.password);
//            Log.i("birth_data:",user.birth_date);
//            Log.i("phone_data:",user.phone);
//            Log.i("address_data:",user.address);
    }
}
