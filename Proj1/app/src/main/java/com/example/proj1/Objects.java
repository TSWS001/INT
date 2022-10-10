package com.example.proj1;

public class Product { //connects with database Products
    String barcode; //used as product_id
    String name;
    String caducity; // dd/mm/yyyy ? la clase de date se llama SimpleDateFormat. hay que implementar la funcion de leer el dia mes y anyo
    int total_weight; //weight of the product at 100%
    int remain_product; //remaining percent of product based on weight
    int base_area; //area to organize products in the scales matrix
}
    public void read_string_date(){//we can make a date lass

    }
    public void connection(){


}

public class Customer { //connects with database Customers
    String first_name; // used to refer to customer inside the app
    String second_name;
    String phone; //send offers, optional
    String address; //to search near markets, optional
    String birth_date; //for some offers from markets, recipes or diet, optional
}