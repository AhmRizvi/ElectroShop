package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.electroshop.R;
import com.example.electroshop.adapter.IntroAdapter;

public class IntroScreenActivity extends AppCompatActivity {
    ViewPager viewP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        getSupportActionBar().hide();

        viewP= findViewById(R.id.viewPAGER);
        IntroAdapter introAdapter= new IntroAdapter(getSupportFragmentManager());
        viewP.setAdapter(introAdapter);
    }
}