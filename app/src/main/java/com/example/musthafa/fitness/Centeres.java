package com.example.musthafa.fitness;

import android.content.Context;

public class Centeres {
    private String name;
    private String place;
    private String image;
    private Context context;
    public Centeres()
    {

    }
    public Centeres(String name, String place,String image, Context context){

        this.name=name;
        this.place=place;
        this.image=image;
        this.context=context;


    }
    public String getCenterName() {
        return name;
    }

    public void setCenterName(String name) {
        this.name = name;
    }

    public String getCenterPlce() {
        return place;
    }

    public void setCenterPlace(String place) {
        this.place = place;
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

