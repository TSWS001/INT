package com.example.proj1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    boolean PrimeraEjecuciondelaApp=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);

        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;

//        //antes de obtener los datos del productlist desde preference verificamos si es la primera ejecucion
//        // de la app
//        if(productlist.size()==0) {
//            Toast.makeText(ActivityList.this,"PrimeraEjecuciondelaApp=true",Toast.LENGTH_SHORT).show();
//            PrimeraEjecuciondelaApp = true;
//        }
        //obtener los datos de la lista de productos desde preferences
        json = preferences.getString("listadeproductos","");
        Log.i("ActList contenido JSON:",json);//resultado default es []

        if(json.length()>2) {   //si el contenido de preference no esta vacio
            Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
            productlist = gson.fromJson(json, listType);
            CheckAndNotifyCaducity();   //notifica si un producto esta a punto de caducar
            Log.i("DebugggggggGGGGg:", "From get json " + productlist.get(0).barcode);
        }
//        //reseteamos el atributo notify en todos los productos de la productlist
//        if(PrimeraEjecuciondelaApp)
//            SetAllNotifyToFalse();

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
    }

    private void SetUpProducts() {

        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json;

        //------------------------------------------------------------------------------------------
        Product new_product = (Product) getIntent().getSerializableExtra("product");
        if(new_product!=null){
            new_product.notificado=false;
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

    public void SetAllNotifyToFalse(){
        int i;
        for (i=0; i<productlist.size(); i++){
            productlist.get(i).notificado=false;
        }
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

    public int StringDateToDays(int d, int m, int y) {  //pasa de una un date string a dias del año
        int res=0,i;
        boolean leapyear;
        leapyear=LeapYear(y);
        //sum of months
        for(i=1; i<m; i++) {    //suma del mes 1 hasta el mes m-1
            if (i<8){           //primera mitad del año
                if (i==2) {     //caso especial
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

    public int DaysDifference(int[] DateCad, int d, int m, int y){
        int d_cad,m_cad,y_cad,totaldays_cad,totaldays_now;
        final int LEAPYEAR=366,NO_LEAPYEAR=365;

        d_cad=DateCad[0];
        m_cad=DateCad[1];
        y_cad=DateCad[2];
        totaldays_now=StringDateToDays(d,m,y);
        totaldays_cad=StringDateToDays(d_cad,m_cad,y_cad);
        Log.i("dia caducidad de prod:",d_cad+"/"+m_cad+"/"+y_cad);

        if (y_cad == y + 1) {//caso especial, los años se diferencia en 1 año
            if(LeapYear(y))
                return LEAPYEAR + totaldays_cad - totaldays_now;
            else
                return NO_LEAPYEAR + totaldays_cad - totaldays_now;
        }
        else if (y_cad == y) {//año igual
            return totaldays_cad - totaldays_now;
        }
        else//año muy diferente
            return -1;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal1";
            String description = "canal para notificar";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("123", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void setNotification(int position, int X){   //X is the difference between two dates
        createNotificationChannel();//creacion del canal
        String msg;

        if (X==0)
            msg="El producto "+productlist.get(position).name+" se caducará HOY";
        else   //si el product se caduca dentro de mas de 1 dia
            msg="El producto "+productlist.get(position).name+" se caducará dentro de "+X+" dias";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "123")
                .setSmallIcon(R.drawable.ic_baseline_fastfood_24)
                .setContentTitle("Se te caduca la comida !!!!!")//añadir el nombre del prod
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

    // notificationId(first input) is a unique int for each notification that you must define
        notificationManager.notify(position, builder.build());
    }

    public boolean NumeroExiste(ArrayList<Integer> arrayList, int numero){
        int i;
        if (arrayList.size()==0)
            return false;

        for (i=0; i<arrayList.size(); i++){
            if (arrayList.get(i)==numero)
                return true;
        }
        return false;
    }

    public void CheckAndNotifyCaducity(){
        Calendar actual_date = Calendar.getInstance();
        int i,d,m,y,X;
        int[] DateCad;

        d=actual_date.get(Calendar.DAY_OF_MONTH);
        m=actual_date.get(Calendar.MONTH);
        y=actual_date.get(Calendar.YEAR);

        for (i=0; i<productlist.size(); i++){
            DateCad = DateToInteger(productlist.get(i).caducity);
            Log.i("ActList prod.notficado:",i+" "+productlist.get(i).notificado+"");
            if(!productlist.get(i).notificado){ //si el prod actual no ha sido notificado
                X = DaysDifference(DateCad,d,m,y);
                Log.i("Act List____X:",String.valueOf(X));
                if(3>=X){
                    //si el producto esta a punto de caducar y no es hoy
                    setNotification(i,X);
                    productlist.get(i).notificado=true;
                    Toast.makeText(ActivityList.this,"Caduca en menos de "+X,Toast.LENGTH_SHORT).show();
                }
                else
                    return;

            }
        }
    }
}

/*
* if(X==0){  //si caduca hoy
                    setNotification(i,X);
                    productlist.get(i).notificado=true;
                    Toast.makeText(ActivityList.this,"Caduca HOY "+X,Toast.LENGTH_SHORT).show();
                }
                else if(3>=X){
                    //si el producto esta a punto de caducar y no es hoy
                    setNotification(i,X);
                    productlist.get(i).notificado=true;
                    Toast.makeText(ActivityList.this,"Caduca en menos de "+X,Toast.LENGTH_SHORT).show();
                }
* */
