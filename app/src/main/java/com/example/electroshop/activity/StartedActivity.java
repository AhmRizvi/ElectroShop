package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.electroshop.R;

public class StartedActivity extends AppCompatActivity {
    Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);
        getSupportActionBar().hide();
        getStartedBtn = findViewById(R.id.getStarted);
        SharedPreferences getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        String user = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "Shared get: " + user);
        if (!user.equals("")) {
            startActivity(new Intent(getBaseContext(), HomeActivity.class));

        }

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), IntroScreenActivity.class));

            }
        });


    }
}