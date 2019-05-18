package com.example.musthafa.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Calendar;

import static com.example.musthafa.fitness.MainActivity.MyPREFERENCES;
import static com.example.musthafa.fitness.MainActivity.pref_email;

public class InvoiceActivity extends AppCompatActivity {
    private List<Invoice> invoiceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InvoiceAdapter mAdapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Bundle bundle=new Bundle();
        bundle=getIntent().getExtras();
        final String date= bundle.getString("date");
        SimpleDateFormat newdate=new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy",Locale.US);
        newdate.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        SimpleDateFormat outputFormat =
                new SimpleDateFormat("MMM dd, yyyy h:mm a");
        try {
            Date ldate = newdate.parse(date);
            Log.v("lastdate", String.valueOf(ldate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputText = outputFormat.format(date);
        //final String bucket= String.valueOf(getIntent().getExtras().getInt("bucketid"));
        //Log.v("bucket",bucket);
        Log.v("invoice date",date);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_invoice);
        mAdapter = new InvoiceAdapter(invoiceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue=Volley.newRequestQueue(InvoiceActivity.this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String email=prefs.getString(pref_email,null);
        String Url="http://192.168.43.193/fitness/invoice.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("invoice response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    for (int i=0;i<=length;i++){
                        JSONObject json=(JSONObject)jsonArray.get(i);
                        String p_name=json.optString("name");
                        String price=json.optString("price");
                        String quantity=json.optString("quantity");
                        Invoice invoice=new Invoice(p_name,price,quantity);
                        invoiceList.add(invoice);
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                params.put("date",date);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
