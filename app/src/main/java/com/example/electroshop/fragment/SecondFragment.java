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


public class SecondFragment extends Fragment {

    TextView next, skipText;
    ViewPager viewPAGER;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        viewPAGER = getActivity().findViewById(R.id.viewPAGER);

        skipText = view.findViewById(R.id.skipIntro);
        next = view.findViewById(R.id.sideOneNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPAGER.setCurrentItem(2);
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