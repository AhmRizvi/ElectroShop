package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electroshop.R;
import com.example.electroshop.adapter.CartAdapter;
import com.example.electroshop.adapter.ExistUserCartAdapter;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Ordertable;
import com.example.electroshop.model.Tempordertable;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;

    DatabaseHelper databaseHelper;
    TextView checkoutTEXT;
    SharedPreferences getsharedpreferences;
    String email;
    TextView checkoutQuantity;


    LinearLayoutManager tempcartlayoutManager;
    CartAdapter tempcartAdapter;

    LinearLayoutManager existlayoutManager;
    ExistUserCartAdapter existcartAdapter;


    List<Tempordertable> alltempcart = new ArrayList<>();

    List<Ordertable> allordertable = new ArrayList<>();

    Animation bounceanim;

    @Override
    protected void onStart() {
        super.onStart();
        bounceanim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(),HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        databaseHelper = new DatabaseHelper(this);
        idcasting();


        Log.d("salllllllllllllllllllll", databaseHelper.checktemporarycart("cart").toString());

        getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "User login email: " + email);
        if (email.equals("")) {

            alltempcart = databaseHelper.checktemporarycart("cart");
            ////////////create adpter
            tempcartAdapter = new CartAdapter(this, alltempcart);
            tempcartlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            cartRecyclerView.setAdapter(tempcartAdapter);
            cartRecyclerView.setLayoutManager(tempcartlayoutManager);
            ///////////amount
            checkoutQuantity.setText(String.valueOf(databaseHelper.totalcartTemptablequantity("checkout")));


        }

        if (!email.equals("")) {
            allordertable = databaseHelper.alldataordertable(email, "cart");

            existcartAdapter = new ExistUserCartAdapter(this, allordertable);
            existlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            cartRecyclerView.setAdapter(existcartAdapter);
            cartRecyclerView.setLayoutManager(existlayoutManager);
            ///////////todo
            checkoutQuantity.setText(String.valueOf(databaseHelper.totalcartordertablequantity("checkout",email)));


        }


        checkoutTEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.equals("")) {
                    startActivity(new Intent(getBaseContext(), CheckoutActivity.class));
                }
                if (email.equals("")) {
                    new AlertDialog.Builder(CartActivity.this)
                            .setTitle(Html.fromHtml("<font color='#038C73'>You don't have Account</font>"))
                            .setMessage("Please create account to Order item")
                            .setCancelable(true)
                            .setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Create Account</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                    startActivity(new Intent(getBaseContext(), CreateCustomerAccount.class));
                                }
                            }).setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    }).show();
                }
            }
        });


    }


    public void idcasting() {
        cartRecyclerView = (RecyclerView) findViewById(R.id.cartrecyclerview);
        checkoutTEXT = findViewById(R.id.checkoutBtn);
        checkoutQuantity = findViewById(R.id.checkoutquantity);


    }
}