package com.example.musthafa.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Config;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    EditText email,password;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pref_email = "usenamekey";
    public static final String pref_password = "passkey";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.id_email);
        password=(EditText)findViewById(R.id.id_password);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        findViewById(R.id.btnlogin_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin_email=email.getText().toString();
                String admin_password=password.getText().toString();

                if (TextUtils.isEmpty(admin_email))
                {
                    email.setError("please enter your user name");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(admin_password))
                {
                    password.setError("please enter your password");
                    password.requestFocus();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(admin_email).matches()) {
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }


                        userLogin();
                // Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT).show();

                    }


        });
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });



    }


    private void userLogin() {
        final String user_email=email.getText().toString();
        final String user_password=password.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(pref_email,user_email );
        editor.apply();
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        String URL = "http://www.slight.fabstudioz.com/fitness/login.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            if (response.equals(user_email))
            {
                startActivity(new Intent(getApplicationContext(),UserHome.class));
            }
            else {
                Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT).show();

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
                params.put("email",user_email);
                params.put("password",user_password);
                return params;
            }
        };
requestQueue.add(stringRequest);

    }


}
