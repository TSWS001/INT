package com.example.proj1.Classes;
import static java.lang.String.valueOf;

import android.preference.PreferenceDataStore;
import android.util.Log;
import android.widget.Toast;

import com.example.proj1.Activities.ActivityCaducidad;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Calendar;

public class Product implements Serializable { //connected with database Products
    public String barcode; //used as product_id (it has to be in database)
    public String name; //(it has to be in database)
    public String caducity; // dd/mm/yyyy
    public int quantity; //number of products of the same type
    public int total_weight; //weight of the product at 100%
    public int remain_product; //remaining percent of product based on weight
    public int base_area; //area to organize products in the scales matrix (it has to be in database)
    public float grasas;
    public float grasas_sat;
    public float hid_carb;
    public float azucares;
    public float fibra;
    public float proteinas;
    public float sal;
    public String ingredients;

    public Product(String barcode, String name, String caducity, int quantity, int total_weight, int remain_product, int base_area) {
        this.barcode = barcode;
        this.name = name;
        this.caducity = caducity;
        this.quantity = quantity;
        this.total_weight = total_weight;
        this.remain_product = remain_product;
        this.base_area = base_area;
    }


    public void setCaducity(String cad){//detect the date we write on the app and save it in Product.caducity
        this.caducity=cad;
    }

    //    private static boolean isNumeric(String str){ //esto en el main o en el fichero de funciones
//        return str != null && str.matches("[0-9.]+");
//    }

    public void setNutricionalInfo(float grasas,float grasas_sat,float hid_carb,float azucares,float fibra,float proteinas,float sal){
        this.grasas=grasas;
        this.grasas_sat=grasas_sat;
        this.hid_carb=hid_carb;
        this.azucares=azucares;
        this.fibra=fibra;
        this.proteinas=proteinas;
        this.sal=sal;
    }

    ; //previous than current date. We shall do a function so we can use the same to check Customer.birthdate
    public void setBarcode(String code){
        this.barcode=code;
    }
    public void setQuantity(){
        this.quantity=1;
    };
    public void QuantityAdd(){
        this.quantity++;
    };
    public void QuantitySubstract(){//if quantity is 0, change the - button like you can't click it
        if (this.quantity>1) {
            this.quantity--;
        }
    };
    public void setRemain_product(int curr_weight){//every time the user puts a product on the scales, the weight
        this.remain_product=(curr_weight/this.total_weight)*100;//is detected and this function is called
    };
    public void set_from_database(){//to copy the values (name, weight, area) of the scanned product
        // from the database using the barcode

    };

    public String getName() {//to be able to show the name of the product
        return name;
    }

    public String getCaducity() {//to be able to show the caducity date
        return caducity;
    }

    public int getQuantity() {//to be able to show the quantity of the product
        return quantity;
    }

    public int getRemain_product() {//to be able to show the percentage remaining
        return remain_product;
    }

    public float[] getNutricionalInfo(){
        return new float[]{this.grasas,this.grasas_sat,this.hid_carb,this.azucares,this.fibra,this.proteinas,this.sal};}

    public String getIngredients() { return this.ingredients; }


    private boolean isNumeric(String text){
        try{
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    //funcion de segun el % remain, devuelva completo, ...
    public String RemainProductToText(){
        if (this.remain_product<15){
            return ("Casi vacío");
        } else if (this.remain_product<30){
            return ("Menos de la mitad");
        } else if (this.remain_product<60){
            return ("Más de la mitad");
        }else if (this.remain_product<90){
            return ("Casi completo");
        }else{
            return ("Completo");
        }
    }

    public boolean isValidCadDate(int d,int m,int y) {
        Calendar act_date= Calendar.getInstance();
        boolean bool=true;
        if (m<1 || m>12 || d<1 || d>31){
            bool= false;
        }else if ((m==4 || m==6 || m==9 || m==11) && d>30) {
            bool= false;
        }else if ((m==2) && ((((y%4==0 && (y%100!=0) || y%400==0)) && d>29) || (((y%4!=0 || y%100==0) && y%400!=0) && d>28))){
            bool= false;
        }else if (y<act_date.get(Calendar.YEAR)){
            bool= false;
        }else if (y==act_date.get(Calendar.YEAR)){
            if (m<act_date.get(Calendar.MONTH)){
                bool= false;
            }else if (m==act_date.get(Calendar.MONTH) && d<act_date.get(Calendar.DAY_OF_MONTH)){
                bool= false;
            }
        }
        return bool;
    }


// en caso de que no funcione correctamente el Database, utilizaremos esta funcion donde simularemos una database.
// Contendra toda la informacion de los productos que escaneemos

    public Product getProductData(String barcode){

        //declaration of the Products in the Product array
        ArrayList<Product> database= new ArrayList<>(List.of(
                new Product("GALLETAS-PRINCIPE-0001","Galletas Príncipe","",1,300,10,0),
                new Product("COLACAO-ORIGINAL-0002","Colacao el original","",1,760,100,0),
                new Product("TOMATE-SOLIS-0003","Tomate solís","",1,350,100,0),
                new Product("ESPAGUETIS-0004","Espaguetis","",1,1000,100,0),
                new Product("ARROZ-0005","Arroz redondo Sos","",1,1000,100,0),
                new Product("LECHE-ENTERA-0006","Leche entera carrefour brick","",1,1500,100,0),
                new Product("ATUN-CLARO-0007","Atún claro en aceite de oliva Carrefour pack de 8 latas de 52","",1,560,100,0),
                new Product("HUEVOS-0006","Huevos L morenos Carreofur 12 ud.","",1,876,100,0),
                new Product("SAL-IODADA-0009","Sal marina yodada fina Carrefour 1kg","",1,1000,100,0),
                new Product("ACEITE-OLIVA-VIRGENEXTRA-0010","Aceite de oliva virgen extra Carrefour 1 l.","",1,1000,100,0)
                ));

        //set nutritional information
        database.get(0).setNutricionalInfo(17,5.6f,71,32,3.1f,6.2f,0.49f);
        database.get(1).setNutricionalInfo(2.5f,1.6f,78,70,7.8f,6.6f,0.1f);
        database.get(2).setNutricionalInfo(3.1f,0.4f,9.1f,6.2f,1.3f,1.2f,0.91f);
        database.get(3).setNutricionalInfo(1.5f,0.3f,70,2.9f,3.6f,12,0.03f);
        database.get(4).setNutricionalInfo(0.5f,0.1f,79,0.1f,2.8f,6.5f,0);
        database.get(5).setNutricionalInfo(3.6f,2.5f,4.7f,4.7f,0,3,0.13f);
        database.get(6).setNutricionalInfo(7.5f,1.1f,0,0,0,26,1.5f);
        database.get(7).setNutricionalInfo(9.5f,2.8f,0.7f,0.7f,12.5f,0,0.36f);
        database.get(8).setNutricionalInfo(0,0,0,0,0,0,0);
        database.get(9).setNutricionalInfo(100,16,0,0,0,0,0);


        //set description
        database.get(0).ingredients="Harina de TRIGO 49 %, azúcar, grasa de palma, aceite de nabina, cacao magro en polvo 4,5 %, jarabe de glucosa, almidón de TRIGO, gasificantes (carbonatos de amonio, carbonatos de sodio), emulgente (lecitinas de SOJA), sal, LECHE desnatada en polvo, permeato de suero (de LECHE), aroma.PUEDE CONTENER HUEVO.";
        database.get(1).ingredients="Azúcar, cacao desgrasado natural¹ (22%), crema de cereal kola-malteado [harina de trigo, extracto de malta de cebada, aroma natural (extracto de nuez de cola)], sales minerales (calcio, fósforo), aromas, sal.";
        database.get(2).ingredients="Tomate y concentrado de tomate (174g de tomate para elaborar 100 g de tomate frito), aceite de girasol, azúcar, almidón modificado de maíz, sal, aroma, cebolla, ajo, pimienta blanca.";
        database.get(3).ingredients="Semola de trigo duro. Puede contener trazas de huevo.";
        database.get(4).ingredients="100% Arroz grano redondo.";
        database.get(5).ingredients="Leche entera de vaca.";
        database.get(6).ingredients="Atún claro, aceite de oliva 24,2%, sal.";
        database.get(7).ingredients="Huevo";
        database.get(8).ingredients="Sal marina";
        database.get(9).ingredients="100% Aceite de oliva virgen extra. Coupage variedades Arbequina, Hojiblanca y Picual";

        int i;
        Product product = null;
        for (i=0; i<10; i++){
            if(Objects.equals(database.get(i).barcode, barcode))
                product=database.get(i);
        }

        if (product!=null)
            return product;
        else{
            Log.e("DATABASE ERROR","can't find the barcode introduced");
            return null;
        }
    }
}
