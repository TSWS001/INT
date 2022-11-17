package com.example.proj1.Classes;
import static java.lang.String.valueOf;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

public class Product { //connected with database Products
    public String barcode; //used as product_id (it has to be in database)
    public String name; //(it has to be in database)
    public String caducity; // dd/mm/yyyy
    public int quantity; //number of products of the same type
    public int total_weight; //weight of the product at 100% (it has to be in database)
    public int remain_product; //remaining percent of product based on weight
    public int base_area; //area to organize products in the scales matrix (it has to be in database)

    public Product(String barcode, String name, String caducity, int quantity, int total_weight, int remain_product, int base_area) {
        this.barcode = barcode;
        this.name = name;
        this.caducity = caducity;
        this.quantity = quantity;
        this.total_weight = total_weight;
        this.remain_product = remain_product;
        this.base_area = base_area;
    }


    public boolean setCaducity(int day,int month,int year){//detect the date we write on the app and save it in Product.caducity
        Scanner scanner = new Scanner(System.in);
        String cad=scanner.nextLine();
        List<Integer> cad_ints = new ArrayList<Integer>();
        if (cad.contains("/")) {
            String[] cad_strs = cad.split("/");
            if (cad_strs.length != 3) {
                for (int i = 0; i < cad_strs.length; i++) {
                    if (isNumeric(cad_strs[i])) {
                        cad_ints.add(Integer.parseInt(cad_strs[i]));
                    } else {
                        return (false);
                    }
                }
            } else {
                return false;
            }
        }else {
            return false;
        }
        Calendar act_date = null;
        if (isValidCadDate(day, month, year, act_date)) {
            this.caducity=day+"/"+month+"/"+year;
        }
        return isValidCadDate(day, month, year, act_date);
    }



    //    private static boolean isNumeric(String str){ //esto en el main o en el fichero de funciones
//        return str != null && str.matches("[0-9.]+");
//    }

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
    private boolean isNumeric(String text){
        try{
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    private boolean isValidCadDate(int d,int m,int y,Calendar act_date) {
        if (m<1 || m>12 || d<1 || d>31){
            return false;
        }else if ((m==4 || m==6 || m==9 || m==11) && d>30) {
            return false;
        }else if ((m==2) && ((((y%4==0 && (y%100!=0) || y%400==0)) && d>29) || (((y%4!=0 || y%100==0) && y%400!=0) && d>28))){
            return false;
        }else if (y<act_date.get(Calendar.YEAR)){
            return false;
        }else if (y==act_date.get(Calendar.YEAR)){
            if (m<act_date.get(Calendar.MONTH)){
                return false;
            }else if (m==act_date.get(Calendar.MONTH) && d<act_date.get(Calendar.DAY_OF_MONTH)){
                return false;
            }
        }
        return true;
    }
}
