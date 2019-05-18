package com.example.musthafa.fitness;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.musthafa.fitness.MainActivity.MyPREFERENCES;
import static com.example.musthafa.fitness.MainActivity.pref_email;

public class TrainerProfile extends AppCompatActivity {
    ImageView image_profile;
    TextView txt_name,txt_age,txt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_profile);
        image_profile=(ImageView)findViewById(R.id.profile_imgview_id);
        txt_name=(TextView)findViewById(R.id.profile_name_id);
        txt_age=(TextView)findViewById(R.id.profile_age_id);
        txt_phone=(TextView)findViewById(R.id.profile_phone_id);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String email=prefs.getString(pref_email,null);
        Log.v("email",email);
        String Url="http://www.slight.fabstudioz.com/fitness/trainer_profile.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    int length=jsonArray.length();
                    for (int i=0;i<length;i++){
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String name=jsonObject.optString("Name");
                        String age=jsonObject.optString("age");
                        String phone=jsonObject.optString("phone");
                        String image=jsonObject.optString("image");
                        txt_name.setText(name);
                        txt_age.setText(age);
                        txt_phone.setText(phone);
                        String Url="http://192.168.43.193/fitness/"+image;
                        Glide.with(getApplicationContext()).load(Url).into(image_profile);
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
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
