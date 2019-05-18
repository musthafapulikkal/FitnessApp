package com.example.musthafa.fitness;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class EditProduct extends Activity {
    EditText edt_name,edt_price;
    ImageView img;
    ImageButton choose;
    Button btn_upload;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        edt_name=(EditText)findViewById(R.id.edt_name_id);
        edt_price=(EditText)findViewById(R.id.edt_price_id);
        img=(ImageView)findViewById(R.id.id_product_image);
        choose=(ImageButton)findViewById(R.id.choose_image);
        btn_upload=(Button)findViewById(R.id.btn_update_id);
        Intent intent=getIntent();

        final String id=intent.getStringExtra("id");
        Log.v("id",id);
        final String product_name=intent.getStringExtra("product_name");
        edt_name.setText(product_name,TextView.BufferType.EDITABLE);

        final String product_price=intent.getStringExtra("price");
        edt_price.setText(product_price,TextView.BufferType.EDITABLE);

        final String product_image=intent.getStringExtra("image");
        String Url="http://192.168.43.193/fitness/"+product_image ;
        Glide.with(getApplicationContext()).load(Url).into(img);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
       btn_upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String pname=edt_name.getText().toString();
               Log.v("pname",pname);
               final String price=edt_price.getText().toString();
               Log.v("price",price);
               Bitmap bitmap=((BitmapDrawable)img.getDrawable()).getBitmap();
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
               final byte[] imageInByte = baos.toByteArray();
               final String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);

               RequestQueue requestQueue=Volley.newRequestQueue(EditProduct.this);
               String Url="http://www.slight.fabstudioz.com/fitness/updateproduct.php";
               StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       if (response.equals("success")){
                           Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
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
                       params.put("id",id);
                       params.put("pname",pname);
                       params.put("price",price);
                       params.put("image",image);
                       return params;
                   }
               };
               requestQueue.add(stringRequest);
           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();


            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.id_product_image

            );
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),AdminHome.class));
        finish();
    }
}
