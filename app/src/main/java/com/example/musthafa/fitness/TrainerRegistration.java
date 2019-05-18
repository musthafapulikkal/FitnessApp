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
import android.util.Base64;
import android.util.Log;
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

public class TrainerRegistration extends AppCompatActivity {

    EditText edtname,edtemail,edtage,edtpass,edtphone;
       ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_registration);
       edtname=(EditText)findViewById(R.id.edt_name);
       edtemail=(EditText)findViewById(R.id.edt_trainer_email);
       edtage=(EditText)findViewById(R.id.edt_age);
       edtpass=(EditText)findViewById(R.id.edt_password);
       edtphone=(EditText)findViewById(R.id.contact_id);
       imageView=(ImageView)findViewById(R.id.profile_id);

       findViewById(R.id.profile_choose).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(intent,RESULT_LOAD_IMAGE);
           }
       });


       findViewById(R.id.btn_trainer_reg).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String username=edtname.getText().toString();
               final String email=edtemail.getText().toString();
               final String age=edtage.getText().toString();
               final String password=edtpass.getText().toString();
               final String phone=edtphone.getText().toString();

               ImageView imageView = (ImageView) findViewById(R.id.profile_id);
               Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
               final byte[] imageInByte = baos.toByteArray();
               final String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);


               RequestQueue requestQueue=Volley.newRequestQueue(TrainerRegistration.this);
               String Url="http://www.slight.fabstudioz.com/fitness/trainer_registration.php";
               StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.v("response",response);
                       if (response.equals(username))
                       {
                           Toast.makeText(getApplicationContext(),"registration successfully completed",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
                       params.put("username",username);
                       params.put("email",email);
                       params.put("age",age);
                       params.put("password",password);
                       params.put("phone_no",phone);
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

            ImageView imageView = (ImageView) findViewById(R.id.profile_id);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));



        }
    }
}
