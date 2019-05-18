 package com.example.musthafa.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class ViewCartItems extends MainActivity {
     private List<Products> prodlist = new ArrayList<>();
     private RecyclerView recyclerView;
     private CartAdapter mAdapter;
     int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart_items);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cart);
        mAdapter = new CartAdapter(prodlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        final TextView total=(TextView)findViewById(R.id.text_total_id);

        RequestQueue requestQueue=Volley.newRequestQueue(ViewCartItems.this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String email=prefs.getString(pref_email,null);

        Log.v("email",email);
        String Url="http://www.slight.fabstudioz.com/fitness/viewcart.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    Context context=getApplicationContext();
                    for(int i = 0 ; i < length ; i++){
                        JSONObject obj=(JSONObject)jsonArray.get(i);
                        String prod_id =obj.optString("id");
                       String prod_name=obj.optString("product name");
                        String prod_price=obj.optString("product price");
                       String prod_quantity=obj.optString("quantity");
                        String image=obj.optString("image");
                        Products products=new Products(prod_id,prod_name,prod_price,prod_quantity,image,context);
                       prodlist .add(products);
                        mAdapter.notifyDataSetChanged();


                        sum=sum+Integer.parseInt(String.valueOf(products.getPrice()))*Integer.parseInt(String.valueOf(products.getQuantity()));

                   Log.v("total",String.valueOf(sum));


                    }

               String hint=getString(R.string.Total);
            total.setText(hint+String.valueOf(sum));

                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("email",email);

                return params;
            }
        };
        requestQueue.add(stringRequest);

        findViewById(R.id.btn_buy_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(getApplicationContext(),PaymentDetails.class));


            }
        });
    }




 }
