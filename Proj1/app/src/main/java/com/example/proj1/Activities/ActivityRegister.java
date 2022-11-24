package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.R;

import java.util.*;

public class ActivityRegister extends AppCompatActivity {

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

    private void SetUserData(){

        String first_name_data = first_name.getText().toString();
        String last_name_data = last_name.getText().toString();
        String email_data = email.getText().toString();
        String password_data = password.getText().toString();
        String birth_data = birth_day.getText().toString()+"/"+birth_month.getText().toString()+"/"+birth_day.getText().toString();
        String phone_data = "+"+prefix_phone.getText().toString()+phone.getText().toString();
        String address_data = address.getText().toString();

        Customer user = new Customer(first_name_data, last_name_data, email_data, password_data);
        Calendar act_date = null;
//        act_date.complete();
        if (!birth_data.equals("//") && user.isValidBirthDate(birth_day.getInputType(),birth_month.getInputType(),birth_year.getInputType(),act_date)) {
            user.setBirth_date(birth_data);//comprobar la fecha
            user.setPhone(phone_data);
            user.setAddress(address_data);
        }
        Log.i("first_name:",user.first_name);
        Log.i("last_name_data:",user.last_name);
        Log.i("email_data:",user.email);
        Log.i("password_data:",user.password);
        Log.i("birth_data:",user.birth_date);
        Log.i("phone_data:",user.phone);
        Log.i("address_data:",user.address);

    }
}
