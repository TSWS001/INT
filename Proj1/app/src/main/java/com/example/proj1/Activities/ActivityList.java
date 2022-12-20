package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proj1.Classes.Product;
import com.example.proj1.R;
import com.example.proj1.Classes.RecyclerViewAdapter;
import com.example.proj1.RecyclerViewInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class ActivityList extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<Product> productlist = new ArrayList<>();
    RecyclerViewAdapter adapter;
    TextView listquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);

        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;

        //obtener los datos de la lista de productos desde preferences
        json = preferences.getString("listadeproductos","");
        Log.i("strinnnnnnnnnnnnnnnn:",json);//resultado default es []

        if(json.length()>2) {
            Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
            productlist = gson.fromJson(json, listType);
            Log.i("DebugggggggGGGGg:", "From get json " + productlist.get(0).barcode);
        }

        listquantity = findViewById(R.id.product_quantity);
        ImageView btnback = findViewById(R.id.back_arrow_list);
        ImageView btnplus = findViewById(R.id.right_icon);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityList.this,MainActivity.class));
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityList.this,ActivityEscaneo.class));
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        SetUpProducts();

        adapter = new RecyclerViewAdapter(this,productlist, this);
        recyclerView.setAdapter(adapter);
        listquantity.setText(String.valueOf(productlist.size())); //set the value of "x products remaining"
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CheckandNotifyCaducity();   //notifica si un producto esta a 3 dias de la fecha de caducidad
    }

    private void SetUpProducts() {

        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json;

        //------------------------------------------------------------------------------------------
        Product new_product = (Product) getIntent().getSerializableExtra("product");
        if(new_product!=null){
            productlist.add(new_product);
            Log.i("Debugggggggg:","Adding "+new_product.barcode);
        }
        else
            Log.i("xxxxxxxxxxinlei:","the product added is null. Error from ActivityList");
        //------------------------------------------------------------------------------------------
        //guardar los datos de la lista de productos a preferences
        json = gson.toJson(productlist);
        editor.putString("listadeproductos",json);
        editor.apply();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ActivityList.this, ActivityProdInfo.class);

        Product prod= productlist.get(position);

        intent.putExtra("NAME",prod.getName());
        intent.putExtra("CADUCITY",prod.getCaducity());
        intent.putExtra("PERCENT", prod.getRemain_product());
        intent.putExtra("QUANTITY_STATE",prod.RemainProductToText());
        intent.putExtra("INGREDIENTS",prod.getIngredients());

//        aqui recibe el array de float de nutritional info
        intent.putExtra("NUTRI_GRASAS",prod.getNutricionalInfo()[0]);
        intent.putExtra("NUTRI_GRASAS_SAT",prod.grasas_sat);
        intent.putExtra("NUTRI_HIDCARB",prod.hid_carb);
        intent.putExtra("NUTRI_AZUCARES",prod.azucares);
        intent.putExtra("NUTRI_FIBRA",prod.fibra);
        intent.putExtra("NUTRI_PROT",prod.proteinas);
        intent.putExtra("NUTRI_SAL",prod.sal);

        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json;

        productlist.remove(position);
        adapter.notifyItemRemoved(position);
        listquantity.setText(String.valueOf(productlist.size()));

        json = gson.toJson(productlist);
        editor.putString("listadeproductos",json);
        editor.apply();
    }
    @Override
    public void onBackPressed() {//para cualquier tipo de retroceso a la anterior actividad
        startActivity(new Intent(ActivityList.this,MainActivity.class));
    }

    public int[] DateToInteger(String date){    //output structure: [day,month,year]
        String[] DateString = date.split("/");
        int i;
        int[] DateInt = new int[DateString.length];

        for(i=0; i<DateString.length; i++)
            DateInt[i] = Integer.parseInt(DateString[i]);

        return DateInt;
    }

    public boolean LeapYear(int y){
        return y%4==0 && (y%100!=0) || y%400==0;
    }

    public int StringDateToDays(String Date) {  //pasa de una un date string a dias del año
        int[] dateInt = DateToInteger(Date);
        int res=0,d,m,y,i;
        boolean leapyear;
        final int LEAPYEAR=366,NO_LEAPYEAR=365;
        //set up
        d=dateInt[0];
        m=dateInt[1];
        y=dateInt[2];
        leapyear=LeapYear(y);
        //sum of months
        for(i=1; i<m; i++) {//suma del mes 1 hasta el mes m-1
            if (i<8){   //primera mitad del año
                if (i==2) {//caso especial
                    if(leapyear)
                        res=res+29;
                    else
                        res=res+28;
                }
                else if (i%2==0) //caso general
                    res=res+30;
                else
                    res=res+31;
            }
            else {  //segunda mitad del año
                if (i%2==0)
                    res=res+31;
                else
                    res=res+30;
            }
        }
        //sum of days
        res=res+d;
        Log.i("result StringDateToDays",String.valueOf(res));
       return res;
    }

    public boolean Check3daysbefore(int[] DateCad, int d, int m, int y){
        int d_cad,m_cad,y_cad;
        d_cad=DateCad[0];
        m_cad=DateCad[1];
        y_cad=DateCad[2];

        if (y_cad==y) {//si año diferente
            if() {  // si el mes es igual que el mes_cad-1
                //if el dia es

            }
            return -1;
        }
        else if (DateCad[1]!=m) {

        }
    }

    public int CheckandNotifyCaducity(){
        Calendar actual_date = Calendar.getInstance();
        int i,j,d,m,y;
        int[] DateCad;
        d=actual_date.get(Calendar.DAY_OF_MONTH);
        m=actual_date.get(Calendar.MONTH);
        y=actual_date.get(Calendar.YEAR);

        for (i=0; i<productlist.size(); i++){
            DateCad = DateToInteger(productlist.get(i).caducity);
                Check3daysbefore(DateCad,d,m,y);

            }
        }

    }

}

