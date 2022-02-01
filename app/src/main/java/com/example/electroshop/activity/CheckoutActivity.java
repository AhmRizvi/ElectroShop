package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electroshop.R;
import com.example.electroshop.adapter.CheckOutExistuserCartAdapter;
import com.example.electroshop.adapter.CheckOutTempCartAdapter;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Customer;
import com.example.electroshop.model.Ordertable;
import com.example.electroshop.model.Tempordertable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView checkoutrecyclerView;
    DatabaseHelper databaseHelper;
    TextView deliveryPRice, subtotlPRICE, totalAMOUNT;
    EditText cardNumber, bkashNumber, bkashtransNumber;

    RadioGroup paymentOptionGROUP, deliveryoptionGROUP;
    RadioButton cashPayment, cardPayment, bkashPayment, homeDelivery, storePickup;

    Button confirmORDER;

    Customer customer;
    private EditText cusNAME, cusPHONE, cusADDRESS;

    String paymenttype = "";
    String cardno = "";
    String bkashno = "";
    String bkashtransaction = "";
    String delivery = "";
    String cusNAme;
    String cusNUmber;
    String cusADdress;

    List<Tempordertable> checkoutcart = new ArrayList<>();
    List<Ordertable> checkoutordercart = new ArrayList<>();
    SharedPreferences getsharedpreferences;
    String email;
    Ordertable orderconfirmDetails;

    ////////////////////////////////////////////////////////////
    ////////////////////////////hide///////////////////////////

    LinearLayout cardDIV, cashDIV, bkashDIV;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), CartActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        idcasting();
        databaseHelper = new DatabaseHelper(this);
        confirmORDER.setOnClickListener(this);


        cashDIV.setVisibility((cardDIV.VISIBLE));
        cardDIV.setVisibility((cardDIV.GONE));
        bkashDIV.setVisibility((cardDIV.GONE));
        getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value


        paymentOptionGROUP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.cashpayment:

                        paymenttype = "cash";

                        cashDIV.setVisibility((cardDIV.VISIBLE));
                        cardDIV.setVisibility((cardDIV.GONE));
                        bkashDIV.setVisibility((cardDIV.GONE));
//                        Toast.makeText(getBaseContext(), "Cash", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cardpayment:

                        paymenttype = "card";


                        cashDIV.setVisibility((cardDIV.GONE));
                        cardDIV.setVisibility((cardDIV.VISIBLE));
                        bkashDIV.setVisibility((cardDIV.GONE));
//                        Toast.makeText(getBaseContext(), "cardpayment", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bkashpayment:

                        paymenttype = "bkash";

//
                        cashDIV.setVisibility((cardDIV.GONE));
                        cardDIV.setVisibility((cardDIV.GONE));
                        bkashDIV.setVisibility((cardDIV.VISIBLE));
//                        Toast.makeText(getBaseContext(), "bkashpayment", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        deliveryoptionGROUP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.homedelivery:
                        delivery = "60.0";
                        deliveryPRice.setText("60.0");
                        double amount = Double.parseDouble(deliveryPRice.getText().toString()) + Double.parseDouble(subtotlPRICE.getText().toString());
                        totalAMOUNT.setText(String.valueOf(Math.round(amount)));

                        if (Double.parseDouble(subtotlPRICE.getText().toString()) < 1) {
                            totalAMOUNT.setText("0.00");
                        }


//                        Toast.makeText(getBaseContext(), "home delivery", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.storepickup:

                        delivery = "0.0";
                        deliveryPRice.setText("0.00");
//                        Toast.makeText(getBaseContext(), "Store Pickup", Toast.LENGTH_SHORT).show();
                        double amountt = Double.parseDouble(deliveryPRice.getText().toString()) + Double.parseDouble(subtotlPRICE.getText().toString());
                        totalAMOUNT.setText(String.valueOf(Math.round(amountt)));
                        break;

                }
            }
        });

        if (!email.equals("")) {
            //Todo with ligin  coiding
            checkoutordercart = databaseHelper.alldataordertable(email, "checkout");
            ////////////create adpter
            CheckOutExistuserCartAdapter checkOutExistuserCartAdapter = new CheckOutExistuserCartAdapter(this, checkoutordercart);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            checkoutrecyclerView.setAdapter(checkOutExistuserCartAdapter);
            checkoutrecyclerView.setLayoutManager(layoutManager);

//todo code
            subtotlPRICE.setText(String.valueOf(databaseHelper.totalOrdertablecheckoutprice("checkout", email)));
            totalAMOUNT.setText(String.valueOf(Math.round(databaseHelper.totalOrdertablecheckoutprice("checkout", email) + Double.parseDouble(deliveryPRice.getText().toString()))));

        }


    }


    private void idcasting() {

        checkoutrecyclerView = (RecyclerView) findViewById(R.id.checkoutrecyclerview);
        paymentOptionGROUP = (RadioGroup) findViewById(R.id.paymentOptiongroup);
        cashPayment = findViewById(R.id.cashpayment);
        cardPayment = findViewById(R.id.cardpayment);
        bkashPayment = findViewById(R.id.bkashpayment);

        cardNumber = findViewById(R.id.cardnumber);
        bkashNumber = findViewById(R.id.bkashnumber);
        bkashtransNumber = findViewById(R.id.bkashtransaction);

        deliveryoptionGROUP = findViewById(R.id.deliveryoptiongroup);
        homeDelivery = findViewById(R.id.homedelivery);
        storePickup = findViewById(R.id.storepickup);
        deliveryPRice = findViewById(R.id.deliveryprice);
        subtotlPRICE = findViewById(R.id.subtotlprice);
        totalAMOUNT = findViewById(R.id.totalAmount);
        confirmORDER = findViewById(R.id.confirmorder);
        //registration
        cusNAME = (EditText) findViewById(R.id.customername);
        cusPHONE = (EditText) findViewById(R.id.customerphone);
        cusADDRESS = (EditText) findViewById(R.id.customeraddress);

        ///////////////////////////////////////////////////////
        //////////////////////hide/////////////////////////////
        cardDIV = (LinearLayout) findViewById(R.id.carddiv);
        cashDIV = (LinearLayout) findViewById(R.id.cashdiv);
        bkashDIV = (LinearLayout) findViewById(R.id.bkashdiv);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.confirmorder:
                cusNAme = cusNAME.getText().toString();
                cusNUmber = cusPHONE.getText().toString();
                cusADdress = cusADDRESS.getText().toString();
                cardno = cardNumber.getText().toString();
                bkashno = bkashNumber.getText().toString();
                bkashtransaction = bkashtransNumber.getText().toString();
                Ordertable ordertable = new Ordertable(paymenttype, cardno, bkashno, bkashtransaction, delivery,
                        "checkout", email, cusNAme, cusNUmber, cusADdress);

                if (databaseHelper.orderConform(ordertable) == true) {
                    Toast.makeText(getBaseContext(), "Order complete", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    Log.d("onClick: ", databaseHelper.alldataordertable(email, "order").toString());
                }
                break;
        }


    }


}