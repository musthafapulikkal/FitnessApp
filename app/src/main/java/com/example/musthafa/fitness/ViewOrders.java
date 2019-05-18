package com.example.musthafa.fitness;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class ViewOrders extends AppCompatActivity {
    private List<ProductList> prodlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_order);
        mAdapter = new OrderAdapter(prodlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);



        RequestQueue requestQueue=Volley.newRequestQueue(ViewOrders.this);
        String Url="http://www.slight.fabstudioz.com/fitness/vieworders.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);


                    int length=jsonArray.length();
                    Context context=getApplicationContext();
                    for(int i = 0 ; i < length ; i++){
                        JSONObject obj=(JSONObject)jsonArray.get(i);
                        String id=obj.optString("id");
                        String prod_name=obj.optString("product name");
                        String prod_price=obj.optString("product price");
                        //String prod_quantity=obj.optString("quantity");
                        String image=obj.optString("image");
                        String email=obj.optString("email");
                        String phone=obj.optString("phone");
                        String address=obj.optString("address");
                        ProductList products=new ProductList(id,prod_name,prod_price,image,email,phone,address,context);
                        prodlist .add(products);
                        mAdapter.notifyDataSetChanged();





                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }




}
