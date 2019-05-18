package com.example.musthafa.fitness;

import android.content.Context;

public class Invoice {
    private String pname;
    private String price;
    private String quantity;
    public Invoice(){

    }
    public Invoice(String pname,String price,String quantity){
        this.pname=pname;
        this.price=price;
        this.quantity=quantity;
    }
    public String getPname(){
        return pname;
    }
    public void setPname(String pname){
        this.pname=pname;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price=price;
    }
    public String getQuantity(){
        return quantity;
    }
    public void setQuantity(String quantity){
        this.quantity=quantity;
    }
}
