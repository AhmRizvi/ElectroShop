package com.example.electroshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.electroshop.R;
import com.example.electroshop.activity.LoginActivity;


public class FirstFragment extends Fragment {
    TextView next, skipText;
    ViewPager viewPAGER;

    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        viewPAGER = getActivity().findViewById(R.id.viewPAGER);
        next = view.findViewById(R.id.sideOneNext);
        skipText = view.findViewById(R.id.skipIntro);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPAGER.setCurrentItem(1);
            }
        });
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
}