package com.example.musthafa.fitness;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Buyproduct extends MainActivity {

ImageView imageView;
EditText edt_quantity;
public static final String MyPREFERENCES = "MyPrefs" ;
SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyproduct);
        Intent intent=getIntent();

        final String product_name=intent.getStringExtra("product_name");
        TextView p_name=(TextView)findViewById(R.id.text_product_name_id);
        p_name.setText(product_name);

        final String price=intent.getStringExtra("price");
        TextView txt_price=(TextView)findViewById(R.id.text_product_price_id);
        String rs=getString(R.string.rs);
        txt_price.setText(rs+price);


//        final String product_quantity = intent.getStringExtra("quantity");
//        TextView quantity=(TextView) findViewById(R.id.text_product_quantity_id);
//        quantity.setText(product_quantity);


        String image=intent.getStringExtra("image");
        imageView=(ImageView)findViewById(R.id.id_product_image);
        String Url="http://www.slight.fabstudioz.com/fitness/"+image ;
        Glide.with(getApplicationContext()).load(Url).into(imageView);

        edt_quantity=( EditText)findViewById(R.id.edt_id_quantity) ;


        findViewById(R.id.btn_addto_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String pro_name = product_name;
                final String pro_price = price;
                final String p_quantity = edt_quantity.getText().toString();
                SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                final String email=prefs.getString(pref_email,null);
                Log.v("prefuser",email);
                Log.v("prodname",pro_name);
                Log.v("prodprice",pro_price);


                //pref=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
                //final String username=pref.getString()


                 if (TextUtils.isEmpty(p_quantity)){
                     edt_quantity.setError("please mention your quantity");
                     edt_quantity.requestFocus();
                     return;
                 }


                    ImageView imageView = (ImageView) findViewById(R.id.id_product_image);
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    final byte[] imageInByte = baos.toByteArray();
                    final String p_image = Base64.encodeToString(imageInByte, Base64.DEFAULT);

                    RequestQueue requestQueue = Volley.newRequestQueue(Buyproduct.this);
                    String Url = "http://www.slight.fabstudioz.com/fitness/addtocart.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("response",response);
                            response=response.replace(" ","");
                            Log.v("Length",String.valueOf(response.length()));
                            Log.v("Condition",String.valueOf(response.equals(pro_name)));
                            if (response.equals(pro_name)) {

                               Toast.makeText(getApplicationContext(), "added into cart", Toast.LENGTH_SHORT).show();
                                findViewById(R.id.btn_addto_cart).setVisibility(View.GONE);
                            }
                            else if (response.equals("nostock")) {

                                Toast.makeText(Buyproduct.this, "no stock", Toast.LENGTH_SHORT).show();


//                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Buyproduct.this);
//                                alertDialogBuilder.setMessage("Sorry no stock available");
//
//                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface arg0, int arg1) {
//                                       // Toast.makeText(Buyproduct.this,"",Toast.LENGTH_LONG).show();
//                                    }
//                                });
//
//
//                                alertDialogBuilder.setCancelable(true);
//                                alertDialogBuilder.show();
//                            }


                            }
                            }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("pname", pro_name);
                            params.put("price", pro_price);
                            params.put("quantity", p_quantity);
                            params.put("image", p_image);
                            params.put("email",email);


                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }



        });

  findViewById(R.id.btn_purchase).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),ViewProduct.class));

    }
});
  findViewById(R.id.btn_back_home).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          startActivity(new Intent(getApplicationContext(),UserHome.class));
          finish();
      }
  });
    }

}
