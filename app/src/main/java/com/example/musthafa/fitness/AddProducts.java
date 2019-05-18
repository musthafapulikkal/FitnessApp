package com.example.musthafa.fitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
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
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    EditText p_name,p_price,p_quantity;
    ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        choose=(Button)findViewById(R.id.btn_choose);
        choose.setTextColor(0xffffff);
        p_name=(EditText)findViewById(R.id.pname_id);
        p_price=(EditText)findViewById(R.id.price_id);
        p_quantity=(EditText)findViewById(R.id.quantity_id);
        imageView=(ImageView)findViewById(R.id.imageview_id);

        findViewById(R.id.btn_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String product_name=p_name.getText().toString();
                final String product_price=p_price.getText().toString();
                final String product_quantity=p_quantity.getText().toString();

                if (TextUtils.isEmpty(product_name)){
                    p_name.setError("please enter product name");
                    p_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(product_price)){
                    p_price.setError("please enter product price");
                    p_price.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(product_quantity)){
                    p_quantity.setError("please enter product quantity");
                    p_quantity.requestFocus();
                    return;
                }


                ImageView imageView = (ImageView) findViewById(R.id.imageview_id);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] imageInByte = baos.toByteArray();
                final String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                RequestQueue requestQueue=Volley.newRequestQueue(AddProducts.this);
                String Url="http://www.slight.fabstudioz.com/fitness/addproduct.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success"))
                        {
                            Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_LONG).show();
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
                        params.put("p_name",product_name);
                        params.put("price",product_price);
                        params.put("quantity",product_quantity);

                        params.put("images",image);

                        return params;
                    }
                };

requestQueue.add(stringRequest);



            }
        });

    }


//    public String getStringImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }


//    public Bitmap getBitmap(Uri uri){
//        InputStream inputStream = getContentResolver().openInputStream(fullPhotoUri);
//        return bitmap = BitmapFactory.decodeStream(inputStream);
//
//    }


//public String getRealPathFromURI(Context context, Uri contentUri) {
//    Cursor cursor = null;
//    try {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    } finally {
//        if (cursor != null) {
//            cursor.close();
//        }
//    }
//}

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

            ImageView imageView = (ImageView) findViewById(R.id.imageview_id);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));



    }

        }


    }


//    public Bitmap getBitmap(Uri uri){{
//        InputStream inputStream = getContentResolver().openInputStream(fullPhotoUri);
//        return bitmap = BitmapFactory.decodeStream(inputStream);
//
//    }



//private class UploadImageToServer extends AsyncTask<Void, Integer, String> {
//    @Override
//    protected void onPreExecute() {
//        // setting progress bar to zero
//        //  progressBar.setProgress(0);
//        super.onPreExecute();
//    }
//
//    @Override
//    protected String doInBackground(Void... params) {
//        return uploadFile();
//    }
//
//    @SuppressWarnings("deprecation")
//    private String uploadFile() {
//        String responseString = null;
//
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost(AppConfig.URL_PHOTO);
//
//        try {
//            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
//                    new AndroidMultiPartEntity.ProgressListener() {
//
//
//                        @Override
//                        public void transferred(long num) {
//                            publishProgress((int) ((num / (float) totalSize) * 100));
//                        }
//                    });
//
//
//            File sourceFile = new File(filePath);
//            ;
//            // Adding file data to http body
//            entity.addPart("image", new FileBody(sourceFile));
//
//            // Extra parameters if you want to pass to server
//            entity.addPart("userid",
//                    new StringBody(session.getuid()));
//
//
//            totalSize = entity.getContentLength();
//            httppost.setEntity(entity);
//
//            // Making server call
//
//            HttpResponse response = httpclient.execute(httppost);
//
//            HttpEntity r_entity = response.getEntity();
//
//            int statusCode = response.getStatusLine().getStatusCode();
//
//            if (statusCode == 200) {
//                // Server response
//
//                responseString = EntityUtils.toString(r_entity);
//
//            } else {
//                responseString = "Error occurred! Http Status Code: "
//                        + statusCode;
//            }
//
//        } catch (ClientProtocolException e) {
//            responseString = e.toString();
//        } catch (IOException e) {
//            responseString = e.toString();
//        }
//
//        return responseString;
//
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        Log.e(TAG, "Response from server: " + result);
//
//        // showing the server response in an alert dialog
//
//
//        super.onPostExecute(result);
//
//    }
//
//
//
//
//}



