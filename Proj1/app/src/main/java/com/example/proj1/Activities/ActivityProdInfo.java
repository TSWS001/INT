package com.example.proj1.Activities;

import android.os.Bundle;
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
            String name,cad,perc,state,nutri,ingred;

            name = getIntent().getStringExtra("NAME");
            cad = getIntent().getStringExtra("CADUCITY");
            perc = getIntent().getStringExtra("PERCENT");
            state = getIntent().getStringExtra("QUANTITY_STATE");
            nutri = getIntent().getStringExtra("NUTRITIONAL_INFO");
            ingred = getIntent().getStringExtra("INGREDIENTS");

            TextView tvname,tvcad,tvpercent,tvstate,tvingredient;
            TextView tvgrasa,tvgrasa_sat,tvhidcarbono,tvazucar,tvfibra,tvproteina,tvsal;

            tvname.setText(findViewById(R.id.));
            tvcad.setText(findViewById(R.id.));
            tvpercent.setText(findViewById(R.id.));
            tvstate.setText(findViewById(R.id.));
            tvingredient.setText(findViewById(R.id.));

            tvgrasa.setText(findViewById(R.id.));
            tvgrasa_sat.setText(findViewById(R.id.));
            tvhidcarbono.setText(findViewById(R.id.));
            tvazucar.setText(findViewById(R.id.));
            tvfibra.setText(findViewById(R.id.));
            tvproteina.setText(findViewById(R.id.));
            tvsal.setText(findViewById(R.id.));



        }
    }
}
