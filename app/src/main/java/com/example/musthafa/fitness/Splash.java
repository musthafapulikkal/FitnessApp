package com.example.musthafa.fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
//                Splash.this.startActivity(mainIntent);
//                Splash.this.finish();
//            }
//        },SPLASH_DISPLAY_LENGTH);

//        new CountDownTimer(5000,1000){
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//
//            }
//
//            @Override
//            public void onFinish() {
//                Splash.this.setContentView(R.layout.activity_main);
//            }
//        }.start();
//    }
    }
}