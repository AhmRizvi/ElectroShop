package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electroshop.R;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Product;

public class SplashScreenActivity extends AppCompatActivity {
    Animation animateBtn, animateText;
    TextView welcome, slogan;
DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();


        welcome = findViewById(R.id.welcome);
        slogan = findViewById(R.id.slogan);
        animateBtn = AnimationUtils.loadAnimation(this, R.anim.animate_btn);
        animateText = AnimationUtils.loadAnimation(this, R.anim.animate_texts);

        welcome.setAnimation(animateBtn);
        slogan.setAnimation(animateText);

        SharedPreferences getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        String user = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
        String onboarding = (getsharedpreferences.getString("onboarding", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "Shared get: " + user);

                 new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, StartedActivity.class));
                }
            }, 2000);
        }




}
