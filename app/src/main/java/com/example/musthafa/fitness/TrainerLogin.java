package com.example.musthafa.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.musthafa.fitness.MainActivity.pref_email;

public class TrainerLogin extends AppCompatActivity {
    EditText edt_email,edt_password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pref_email = "usenamekey";
    public static final String pref_password = "passkey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);
        edt_email=(EditText)findViewById(R.id.trainer_email_id);
        edt_password=(EditText)findViewById(R.id.trainer_pass_id);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        findViewById(R.id.textView_trainer_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TrainerRegistration.class));
            }
        });
        findViewById(R.id.trainer_login_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String trainer_email=edt_email.getText().toString();
                final String trainer_password=edt_password.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(pref_email,trainer_email);
                editor.apply();
                RequestQueue requestQueue=Volley.newRequestQueue(TrainerLogin.this);
                String URL = "http://www.slight.fabstudioz.com/fitness/trainerlogin.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals(trainer_email))
                        {

                            startActivity(new Intent(getApplicationContext(),TrainerProfile.class));

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email",trainer_email);
                        params.put("password",trainer_password);

                        return params;
                    }
                };

                requestQueue.add(stringRequest);


            }

        });
    }
}
