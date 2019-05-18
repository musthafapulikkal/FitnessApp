package com.example.musthafa.fitness;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AddTrainingCenters extends AppCompatActivity {
EditText edt_center_name,edt_center_place;
ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training_centers);
        edt_center_name=(EditText)findViewById(R.id.centername_id);
        edt_center_place=(EditText)findViewById(R.id.centerplacd_id);
        imageView=(ImageView)findViewById(R.id.centerimage_id);

        findViewById(R.id.btn_center_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            }
        });

        findViewById(R.id.btn_center_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String center_name=edt_center_name.getText().toString();
                final String place=edt_center_place.getText().toString();

                if (TextUtils.isEmpty(center_name)){
                    edt_center_name.setError("please enter center name");
                    edt_center_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(place)){
                    edt_center_place.setError("please enter place");
                    edt_center_place.requestFocus();
                    return;
                }


                ImageView imageView = (ImageView) findViewById(R.id.centerimage_id);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] imageInByte = baos.toByteArray();
                final String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);

                RequestQueue requestQueue=Volley.newRequestQueue(AddTrainingCenters.this);
                String Url="http://www.slight.fabstudioz.com/fitness/addcenters.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      if (response.equals("success")){
                          Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),AdminHome.class));
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
                        params.put("c_name",center_name);
                        params.put("place",place);
                        params.put("images",image);
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



            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.centerimage_id);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));



        }
    }
}
