package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.Classes.Product;
import com.example.proj1.R;

import java.util.Calendar;

public class ActivityCaducidad extends AppCompatActivity {

    Button btnAceptar,btnNoCad;
    EditText cad_day, cad_month, cad_year;
    //habria que hacer un atributo de la clase customer de si el usuario esta o no registrado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caducidad);

        btnNoCad = findViewById(R.id.btnnocaducity);
        btnAceptar = findViewById(R.id.btnaceptar_cad);
        cad_day = findViewById(R.id.cad_day);
        cad_month = findViewById(R.id.cad_month);
        cad_year = findViewById(R.id.cad_year);
        String cad_data = cad_day.getText().toString()+"/"+cad_month.getText().toString()+"/"+cad_year.getText().toString();


        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Set up of the product class object
                Calendar act_date= Calendar.getInstance();
                String[] array_str = getIntent().getStringArrayExtra("PRODUCT_STR");
                int[] array_int = getIntent().getIntArrayExtra("PRODUCT_INT");
                Product product = new Product(array_str[0],array_str[1],array_str[2],array_int[0],array_int[1],array_int[2],array_int[3]);
                //check
                if (product.isValidCadDate(cad_day.getInputType(),cad_month.getInputType(),cad_year.getInputType(),act_date)){
                    //si la fecha de caducidad es correcta
                    product.setCaducity(cad_data);
                    startActivity(new Intent(ActivityCaducidad.this,ActivityList.class));
                }
                else{
                    //si la fecha de caducidad es incorrecta

                }
            }
        });

        btnNoCad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityCaducidad.this,ActivityList.class));
            }
        });
    }
}
