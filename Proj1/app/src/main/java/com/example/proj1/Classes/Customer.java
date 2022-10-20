package com.example.proj1.Classes;

public class Customer { //connects with database Customers
    public String email;//mandatory
    public String first_name; // mandatory, used to refer to customer inside the app
    public String last_name;//mandatory
    public String phone=null; //send offers, optional
    public String address=null; //to search near markets, optional
    public String birth_date=null; //for some offers from markets, recipes or diet, optional

    public Customer(String first_name, String email, String second_name){//constructor for mandatory attributes
        this.first_name=first_name;
        this.last_name=second_name;
        this.email=email;
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
}

