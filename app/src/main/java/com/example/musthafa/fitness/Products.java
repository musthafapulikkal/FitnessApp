package com.example.musthafa.fitness;

import android.content.Context;

public class Products {
    private String id;
    private String name;
    private String price;
    private String quantity;
    private String image;
    private Context context;
    private String phone;

    public Products() {
    }

    public Products(String id,String name, String price,String quantity, String image, Context context) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.quantity=quantity;
        this.image=image;
        this.context=context;
        // this.year = year;
    }
    public String getId() {return id;}
    public void setId(String id) {this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity(){return quantity;}

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image=image;
    }
    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
