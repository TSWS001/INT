package com.example.proj1.Classes;

import java.util.Calendar;

public class Customer { //connects with database Customers
    public String email;//mandatory
    public String first_name; // mandatory, used to refer to customer inside the app
    public String last_name;//mandatory
    public String password;//mandatory
    public String phone=null; //send offers, optional
    public String address=null; //to search near markets, optional
    public String birth_date=null; //for some offers from markets, recipes or diet, optional

    public Customer(String email, String first_name, String second_name, String password){//constructor for mandatory attributes
        this.first_name=first_name;
        this.last_name=second_name;
        this.email=email;
        this.password=password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void SendToDatabase(){

    }
    private boolean isValidBirthDate(int d, int m, int y, Calendar act_date) {
        if (y>act_date.get(Calendar.YEAR)){
            return false;
        }else if (y<=act_date.get(Calendar.YEAR)){
            if (m<act_date.get(Calendar.MONTH) || m<0 || m>12){
                return false;
            }else if (m==act_date.get(Calendar.MONTH) && d<act_date.get(Calendar.DAY_OF_MONTH)){
                return false;
            }else if ((m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12) && d>31){
                return false;
            }else if ((m==4 || m==6 || m==9 || m==11) && d>30) {
                return false;
            }else if ((m==2) && ((((y%4==0 && y%100!=0) || y%400==0) && d>29) || (((y%4!=0 || y%100==0) && y%400!=0) && d>28))){
                return false;
            }
        }
        return true;
    }
}

