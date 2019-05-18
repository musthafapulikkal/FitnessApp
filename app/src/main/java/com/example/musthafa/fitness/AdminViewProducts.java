package com.example.musthafa.fitness;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
import java.util.List;

public class AdminViewProducts extends AppCompatActivity {
    private List<Products> prodlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdminProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_products);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AdminProductAdapter(prodlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Products products=prodlist.get(position);
                Intent intent=new Intent(getApplicationContext(),UpdateProduct.class);
                intent.putExtra("id",products.getId());
                intent.putExtra("product_name",products.getName());
                intent.putExtra("price",products.getPrice());
                intent.putExtra("image",products.getImage());
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        RequestQueue requestQueue = Volley.newRequestQueue(AdminViewProducts.this);
        String Url = "http://www.slight.fabstudioz.com/fitness/viewproducts.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length = jsonArray.length();
                    Context context=getApplicationContext();
                    for(int i = 0 ; i < length ; i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        String prod_id = obj.optString("id");
                        String prod_name = obj.optString("product name");
                        Log.v("prodname", prod_name);
                        String prod_price = obj.optString("product price");
                        String prod_quantity = obj.optString("quantity");
                        String image = obj.optString("image");
                        Products products=new Products(prod_id,prod_name,prod_price,prod_quantity,image,context);
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
