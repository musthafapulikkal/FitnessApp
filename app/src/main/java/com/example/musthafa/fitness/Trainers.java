package com.example.musthafa.fitness;

import android.content.Context;

public class Trainers {

    private String name;
    private String age;
    private String phone;
    private String image;
    private Context context;

    public Trainers(){

    }
    public Trainers(String name, String age, String phone, String image, Context context){

        this.name=name;
        this.age=age;
        this.phone=phone;
        this.image=image;
        this.context=context;


    }
    public String getTrainerName() {
        return name;
    }

    public void setTrainerName(String name) {
        this.name = name;
    }

    public String getTrainerAge() {
        return age;
    }

    public void setTrainerAge(String age) {
        this.age = age;
    }

    public String getTrainerPhone() {
        return phone;
    }

    public void setTrainerPhone(String phone) {
        this.phone = phone;
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
