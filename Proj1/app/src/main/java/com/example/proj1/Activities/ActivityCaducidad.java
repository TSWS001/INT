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
import com.example.proj1.Classes.Product;
import com.example.proj1.R;

import java.util.Calendar;

public class ActivityCaducidad extends AppCompatActivity {

    Button btnAceptar,btnNoCad;
    EditText cad_day, cad_month, cad_year;
    Product product;
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
                Log.i("aaaaaaaaaaaaaaaaaaaaaaa", act_date.get(Calendar.DAY_OF_MONTH)+"/"+act_date.get(Calendar.MONTH)+"/"+act_date.get(Calendar.YEAR));
                product = (Product) getIntent().getSerializableExtra("product");
                //checks the correctness of the date
                if (product.isValidCadDate(cad_day.getInputType(),cad_month.getInputType(),cad_year.getInputType(),act_date)){
                    //si la fecha de caducidad es correcta
                    Intent intent = new Intent(ActivityCaducidad.this,ActivityList.class);
                    product.setCaducity(cad_data);
                    intent.putExtra("product",product);
                    Log.i("Debugggggggg:","With cad "+product.barcode);

                    startActivity(intent);
                }
                else{
                    //si la fecha de caducidad es incorrecta
                    Toast.makeText(ActivityCaducidad.this, "fecha de caducidad erronea", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNoCad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityCaducidad.this,ActivityList.class);
                product = (Product) getIntent().getSerializableExtra("product");
                intent.putExtra("product",product);
                Log.i("Debugggggggg:","No cad "+product.barcode);

                startActivity(intent);
            }
        });
    }
}
