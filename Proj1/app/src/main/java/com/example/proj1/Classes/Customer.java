package com.example.proj1.Classes;

import android.util.Log;

import java.util.Calendar;

public class Customer { //connects with database Customers
    public String email;//mandatory
    public String first_name; // mandatory, used to refer to customer inside the app
    public String last_name;//mandatory
    public String password;//mandatory
    public String phone=null; //send offers, optional
    public String birth_date=null; //for some offers from markets, recipes or diet, optional

    public Customer(String email, String first_name, String last_name, String password){//constructor for mandatory attributes
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.password=password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public boolean isValidBirthDate(int d, int m, int y) {
        Calendar act_date= Calendar.getInstance();
        Log.i("feeeeeeeeecha actual:",act_date.get(Calendar.DAY_OF_MONTH)+" "+act_date.get(Calendar.MONTH)+" "+act_date.get(Calendar.YEAR));
        boolean bool=true;
        if (m<1 || m>12 || d<1 || d>31){
            bool= false;
        }else if ((m==4 || m==6 || m==9 || m==11) && d>30) {
            bool= false;
        }else if ((m==2) && ((((y%4==0 && (y%100!=0) || y%400==0)) && d>29) || (((y%4!=0 || y%100==0) && y%400!=0) && d>28))){
            bool= false;
        }else if (y>act_date.get(Calendar.YEAR)){
            bool= false;
        }else if (y==act_date.get(Calendar.YEAR)){
            if (m>act_date.get(Calendar.MONTH)){
                bool= false;
            }else if (m==act_date.get(Calendar.MONTH) && d>act_date.get(Calendar.DAY_OF_MONTH)){
                bool= false;
            }
        }
        return bool;
    }
}

