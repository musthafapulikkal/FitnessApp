package com.example.musthafa.fitness;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminHome extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        findViewById(R.id.btn_add_products_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),AddProducts.class));
            }
        });

        findViewById(R.id.btn_add_training_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),AddTrainingCenters.class));
            }
        });
        findViewById(R.id.btn_view_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),ViewUsers.class));
            }
        });
        findViewById(R.id.btn_view_trainers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),ViewTrainers.class));
            }
        });
        findViewById(R.id.btn_view_orders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewOrders.class));
            }
        });

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
               finish();
            }
        });
        findViewById(R.id.btn_view_product).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminViewProducts.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
