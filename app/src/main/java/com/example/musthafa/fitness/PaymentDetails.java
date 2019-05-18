package com.example.musthafa.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentDetails extends MainActivity {
EditText edtphone,edtaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        //Log.v("total",total);
        edtphone=(EditText)findViewById(R.id.edt_payment_phone_id);
        edtaddress=(EditText)findViewById(R.id.edt_apayment_ddress_id);

        findViewById(R.id.btn_payment_send_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone=edtphone.getText().toString();
                final String address=edtaddress.getText().toString();
                final Date currentTime=Calendar.getInstance().getTime();
                SimpleDateFormat df=new SimpleDateFormat("dd-mmm-yyyy");
                final String date=df.format(currentTime);
               // Log.v("total",total);
                //TextView p_name=(TextView)findViewById(R.id.text_product_name_id);
                //p_name.setText(product_name);

                SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                final String email=prefs.getString(pref_email,null);
                RequestQueue requestQueue=Volley.newRequestQueue(PaymentDetails.this);
                String Url="http://www.slight.fabstudioz.com/fitness/order.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response);
                        if (response.equals("nostock")){
                            Toast.makeText(getApplicationContext(),"Sorry No stock",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ViewProduct.class));
                            finish();
                        }
                        else
                            {
                         Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                         Bundle b=new Bundle();
                         b.putString("date", String.valueOf(currentTime));
                         Intent i=new Intent(getApplicationContext(),PaymentSuccessActivity.class);
                         i.putExtras(b);
                         //i.putExtra("bucketid",response);
                         startActivity(i);
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
                        params.put("email",email);
                        params.put("phone",phone);
                        params.put("address",address);
                        params.put("date", String.valueOf(currentTime));
                       // params.put("total",total);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}
