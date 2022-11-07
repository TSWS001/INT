package com.example.proj1.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.R;

public class CustomerActivity extends AppCompatActivity {

    Button btnAcceptar;
    EditText first_name,last_name, email, birth, phone, adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        btnAcceptar = findViewById(R.id.btnacceptar);
        first_name = findViewById(R.id.textfirstname);
        last_name = findViewById(R.id.textlastname);
        email = findViewById(R.id.text_email);
        birth = findViewById(R.id.textbirth);
        phone = findViewById(R.id.text_phone);
        adress = findViewById(R.id.text_direccion);

        btnAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(this, ));  //al clicar el boton aceptar pasar a la pagina inicial
            }
        });
    }

//    private void SetUserData(){
//
//        String first_name_data = first_name.getText().toString();
//        String last_name_data = last_name.getText().toString();
//        String email_data = email.getText().toString();
//        String birth_data = birth.getText().toString();
//        String phone_data = phone.getText().toString();
//        String adress_data = adress.getText().toString();
//
//        Customer user = new Customer();
//
//
//    }
}
