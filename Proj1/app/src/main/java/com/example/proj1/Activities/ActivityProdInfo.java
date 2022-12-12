package com.example.proj1.Activities;

import android.content.Intent;
import android.os.Bundle;
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
        {

            String name,cad,perc,state,ingred;
            String grasa,grasa_sat,hidcarbono,azucar,fibra,proteina,sal;

            //Get string from Intent
            name = getIntent().getStringExtra("NAME");
            cad = getIntent().getStringExtra("CADUCITY");
            perc = getIntent().getStringExtra("PERCENT");
            state = getIntent().getStringExtra("QUANTITY_STATE");
            ingred = getIntent().getStringExtra("INGREDIENTS");

            grasa = getIntent().getStringExtra("NUTRI_GRASAS");
            grasa_sat = getIntent().getStringExtra("NUTRI_GRASAS_SAT");
            hidcarbono = getIntent().getStringExtra("NUTRI_HIDCARB");
            azucar = getIntent().getStringExtra("NUTRI_AZUCARES");
            fibra = getIntent().getStringExtra("NUTRI_FIBRA");
            proteina = getIntent().getStringExtra("NUTRI_PROT");
            sal = getIntent().getStringExtra("NUTRI_SAL");


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

            // Set texts
            tvname.setText(name);
            tvcad.setText(cad);
            tvpercent.setText(perc);
            tvstate.setText(state);
            tvingredient.setText(ingred);

            tvgrasa.setText(grasa);
            tvgrasa_sat.setText(grasa_sat);
            tvhidcarbono.setText(hidcarbono);
            tvazucar.setText(azucar);
            tvfibra.setText(fibra);
            tvproteina.setText(proteina);
            tvsal.setText(sal);

            back_prod_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    //startActivity(new Intent(ActivityProdInfo.this,MainActivity.class));
                }
            });

        }
    }
}
