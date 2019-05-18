package com.example.musthafa.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email=(EditText)findViewById(R.id.admin_email_id);
        password=(EditText)findViewById(R.id.admin_pass_id);
        findViewById(R.id.admin_login_id).setOnClickListener(new View.OnClickListener() {
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
                if (admin_email.equals("admin@gmail.com") && admin_password.equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), AdminHome.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"invalid user name or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
