package com.example.proj1.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Customer;
import com.example.proj1.Classes.Product;
import com.example.proj1.R;

import java.util.Calendar;

public class ActivityCaducidad extends AppCompatActivity {

    Button btnAceptar,btnNoCad;
    EditText cad_day, cad_month, cad_year, quantity;
    ImageView backarrow;
    Product product;
    String day,month,year, quant;
    int quant_int;

    //habria que hacer un atributo de la clase customer de si el usuario esta o no registrado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caducidad);

        btnNoCad = findViewById(R.id.btnnocaducity);
        btnAceptar = findViewById(R.id.btnaceptar_cad);
        quantity= findViewById(R.id.cad_quantity);
        cad_day = findViewById(R.id.cad_day);
        cad_month = findViewById(R.id.cad_month);
        cad_year = findViewById(R.id.cad_year);
        backarrow = findViewById(R.id.back_arrow_caducity);


        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                product = (Product) getIntent().getSerializableExtra("product");
                quant = quantity.getText().toString();
                //checks the correctness of the date
                day = cad_day.getText().toString();
                month = cad_month.getText().toString();
                year = cad_year.getText().toString();
                String cad_data = day+"/"+month+"/"+year;
                setQuantityInt();
                Log.i("CANTIDAD en int", "int quantity"+"="+quant_int);
                if (day.length()+month.length()+year.length()<8){
                    Toast.makeText(ActivityCaducidad.this, "introduce una fecha de caducidad válida", Toast.LENGTH_SHORT).show();
                }
                else if (!product.isValidCadDate(Integer.parseUnsignedInt(day),Integer.parseUnsignedInt(month),Integer.parseUnsignedInt(year))){
                    //si la fecha de caducidad es incorrecta
                    Toast.makeText(ActivityCaducidad.this, "fecha de caducidad erronea", Toast.LENGTH_SHORT).show();
                }
                else{
                    //si la fecha de caducidad es correcta
                    Intent intent = new Intent(ActivityCaducidad.this,ActivityList.class);
                    product.setCaducity(cad_data);
                    product.setQuantity(quant_int);
                    Log.i("CANTIDAD stored", "stored quantity"+"="+product.quantity);
                    Log.i("aaaaaaaaaaaaaaaaaaaaaaa", cad_data+"="+product.caducity);
                    intent.putExtra("product",product);
                    Log.i("Debugggggggg:","With cad "+product.barcode);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnNoCad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityCaducidad.this,ActivityList.class);
                product = (Product) getIntent().getSerializableExtra("product");
                product.setCaducity("No tiene");
                setQuantityInt();
                Log.i("CANTIDAD stored", "stored quantity"+"="+product.quantity);
                intent.putExtra("product",product);
                Log.i("Debugggggggg:","No cad "+product.barcode);

                startActivity(intent);
                finish();
            }
        });
    }
    public void setQuantityInt(){
        if (quant.length()<1){
            //si no hay cantidad asignada
            quant_int=1;
        }
        else{
            quant_int=Integer.parseUnsignedInt(quant);
        }
        product.setQuantity(quant_int);
    }
}
