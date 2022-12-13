package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.example.proj1.RecyclerViewInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityProdInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_info);

        String name,cad,state,ingred;
        int perc;
        float grasa,grasa_sat,hidcarbono,azucar,fibra,proteina,sal;

        //Get string from Intent
        name = getIntent().getStringExtra("NAME");
        cad = getIntent().getStringExtra("CADUCITY");
        perc = getIntent().getIntExtra("PERCENT",-1);
        state = getIntent().getStringExtra("QUANTITY_STATE");
        ingred = getIntent().getStringExtra("INGREDIENTS");

        grasa = getIntent().getFloatExtra("NUTRI_GRASAS",-1);
        grasa_sat = getIntent().getFloatExtra("NUTRI_GRASAS_SAT",-1);
        hidcarbono = getIntent().getFloatExtra("NUTRI_HIDCARB",-1);
        azucar = getIntent().getFloatExtra("NUTRI_AZUCARES",-1);
        fibra = getIntent().getFloatExtra("NUTRI_FIBRA",-1);
        proteina = getIntent().getFloatExtra("NUTRI_PROT",-1);
        sal = getIntent().getFloatExtra("NUTRI_SAL",-1);

        Log.i("xxxxxxxxxxinlei:","prodinfo_name "+name);

        TextView tvname,tvcad,tvpercent,tvstate,tvingredient;
        TextView tvgrasa,tvgrasa_sat,tvhidcarbono,tvazucar,tvfibra,tvproteina,tvsal;

        //Find views
        ImageView back_prod_info= findViewById(R.id.back_prod_info);

        tvname = findViewById(R.id.prod_name);
        tvcad = findViewById(R.id.cad_date);
        tvpercent = findViewById(R.id.prod_rem_percent);
        tvstate = findViewById(R.id.prod_rem_text);
        tvingredient = findViewById(R.id.prod_ingredientes);

        tvgrasa = findViewById(R.id.prod_nutri_grasas);
        tvgrasa_sat = findViewById(R.id.prod_nutri_grasas_sat);
        tvhidcarbono = findViewById(R.id.prod_nutri_hid_carb);
        tvazucar = findViewById(R.id.prod_nutri_azucares);
        tvfibra = findViewById(R.id.prod_nutri_fibra);
        tvproteina = findViewById(R.id.prod_nutri_prot);
        tvsal = findViewById(R.id.prod_nutri_sal);

        // Set the String got from the Intent to the TextViews found
        tvname.setText(name);
        tvcad.setText(cad);
        tvpercent.setText(String.valueOf(perc));
        tvstate.setText(state);
        tvingredient.setText(ingred);

        tvgrasa.setText(String.valueOf(grasa));
        tvgrasa_sat.setText(String.valueOf(grasa_sat));
        tvhidcarbono.setText( String.valueOf(hidcarbono));
        tvazucar.setText(String.valueOf(azucar));
        tvfibra.setText(String.valueOf(fibra));
        tvproteina.setText(String.valueOf(proteina));
        tvsal.setText(String.valueOf(sal));

        back_prod_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
