package com.example.proj1.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.R;

public class ActivityLogin extends AppCompatActivity {
    Button btnAceptar, btnRegistrarse;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        btnAceptar = findViewById(R.id.btnaceptar_login);
        btnRegistrarse = findViewById(R.id.btnregister);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
    }
    private int getUserDatabase(){//0 si correcto, 1 si contraseña incorrecta, 2 si no existe usuario
        //conexión al server

        if (true){//comprobar si existe un usuario con ese email en la base de datos
            if (true){//si contraseña correcta
                //obtener valores del usuario de la database?
                String first_name_data ="";
                String last_name_data ="";
                Customer user = new Customer(first_name_data, last_name_data, email.getText().toString(), password.getText().toString());
                //obtener la product list de la database?
                return 0;
            }else{//si contraseña incorrecta
                return 1;
            }
        }else{
            return 2;
        }
    }
}
