package com.example.musthafa.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.musthafa.fitness.MainActivity.MyPREFERENCES;

public class UserHome extends AppCompatActivity {
    Button product_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        product_view=(Button)findViewById(R.id.btn_view_product);
        product_view.setTextColor(getApplicationContext().getResources().getColor(R.color.white));


        findViewById(R.id.btn_view_product).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),ViewProduct.class));

            }
        });
      findViewById(R.id.btn_user_add_product).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),UserAddProduct.class));

          }
      });

      findViewById(R.id.btn_view_fitnss).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),ViewFitnessCentres.class));
          }
      });
      findViewById(R.id.btn_view_cart).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),ViewCartItems.class));
          }
      });
      findViewById(R.id.btn_view_trainers).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),ViewTrainers.class));
          }
      });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                SharedPreferences preferences =getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(UserHome.this,MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
