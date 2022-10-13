package com.example.proj1;
import java.util.Scanner;

public class Product { //connected with database Products
    public String barcode; //used as product_id (it has to be in database)
    public String name; //(it has to be in database)
    public String caducity; // dd/mm/yyyy ? la clase de date se llama SimpleDateFormat. hay que implementar la funcion de leer el dia mes y anyo
    public int quantity; //number of products of the same type
    public int total_weight; //weight of the product at 100% (it has to be in database)
    public int remain_product; //remaining percent of product based on weight
    public int base_area; //area to organize products in the scales matrix (it has to be in database)

    public void setCaducity(){//detect the date we write on the app and save it in Product.caducity
        Scanner scanner = new Scanner(System.in);
        this.caducity=scanner.nextLine();//in MainActivity, check if the format is correct and the date is
    }; //previous than current date. We shall do a function so we can use the same to check Customer.birthdate
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
}

