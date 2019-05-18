package com.example.musthafa.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class  Registration extends AppCompatActivity {
    EditText edtusername,edtemail,edtpassword;
//    RadioGroup radiogender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edtusername=(EditText)findViewById(R.id.editTextUsername);
        edtemail=(EditText)findViewById(R.id.editTextEmail);
        edtpassword=(EditText)findViewById(R.id.editTextPassword);

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=edtusername.getText().toString();
                final String email=edtemail.getText().toString();
                final String password=edtpassword.getText().toString();
//                final String gender=((RadioButton)findViewById(radiogender.getCheckedRadioButtonId())).getText().toString();

                if (TextUtils.isEmpty(username))
                {
                    edtusername.setError("please enter username");
                    edtusername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email))
                {
                    edtemail.setError("please enter email");
                    edtemail.requestFocus();
                    return;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtemail.setError("Enter a valid email");
                    edtemail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    edtpassword.setError("please en ter password");
                    edtpassword.requestFocus();
                    return;
                }


                RequestQueue requestQueue=Volley.newRequestQueue(Registration.this);

                String Url="http://www.slight.fabstudioz.com/fitness/registration.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                        params.put("password",password);
//                        params.put("gender",gender);

                        return params;
                    }
                };
               requestQueue.add(stringRequest);
            }
        });
    }
}
