package com.example.musthafa.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class UpdateProduct extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        Intent intent=getIntent();
        final String product_id=intent.getStringExtra("id");
        final String product_name=intent.getStringExtra("product_name");
        TextView p_name=(TextView)findViewById(R.id.text_product_name_id);
        p_name.setText(product_name);

        final String price=intent.getStringExtra("price");
        TextView txt_price=(TextView)findViewById(R.id.text_product_price_id);
        String rs=getString(R.string.rs);
        txt_price.setText(rs+price);
        final String image=intent.getStringExtra("image");

        imageView=(ImageView)findViewById(R.id.id_product_image);
        String Url="http://www.slight.fabstudioz.com/fitness/fitness/"+image ;
        Glide.with(getApplicationContext()).load(Url).into(imageView);

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v("id",product_id);
                RequestQueue requestQueue=Volley.newRequestQueue(UpdateProduct.this);
                String Url="http://www.slight.fabstudioz.com/fitness/productdelete.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")){
                            startActivity(new Intent(getApplicationContext(),AdminViewProducts.class));
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("id",product_id);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i=new Intent(getApplicationContext(),EditProduct.class);
                i.putExtra("product_name",product_name);
                i.putExtra("price",price);
                i.putExtra("image",image);
                i.putExtra("id",product_id);
                startActivity(i);
                finish();
            }
        });
    }
}
