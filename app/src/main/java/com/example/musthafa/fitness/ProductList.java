package com.example.musthafa.fitness;

import android.content.Context;

public class ProductList {
        private String id;
        private String name;
        private String price;
        private String quantity;
        private String image;
       private String email;
        private String phone;
        private String address;
        private Context context;
        public ProductList() {
        }

        public ProductList(String id,String name, String price,String image,String email,String phone,String address, Context context) {
            this.id=id;
            this.name = name;
            this.price = price;
            //this.quantity=quantity;
            this.image=image;
            this.email=email;
            this.phone=phone;
            this.address=address;
            this.context=context;
            // this.year = year;
        }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

        public String getEmail(){return email;}
        public void setEmail(String email){this.email=email;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

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


