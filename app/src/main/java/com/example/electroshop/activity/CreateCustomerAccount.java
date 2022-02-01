package com.example.electroshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.electroshop.R;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Customer;
import com.example.electroshop.model.Ordertable;
import com.example.electroshop.model.Tempordertable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateCustomerAccount extends AppCompatActivity implements View.OnClickListener {
    EditText cusNAME, cusEMAIL, cusNUMBER, cusADDRESS, cusPASSWORD;
    Button registerBUTTON;
    TextInputLayout setmessege;
    ConstraintLayout fullSCREEN;

    DatabaseHelper databaseHelper;


    Customer createCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer_account);
        getSupportActionBar().hide();
        idcasting();
        databaseHelper = new DatabaseHelper(this);
        registerBUTTON.setOnClickListener(this);


    }


    public void idcasting() {
        cusNAME = (EditText) findViewById(R.id.cusname);
        cusEMAIL = (EditText) findViewById(R.id.cusemail);
        cusNUMBER = (EditText) findViewById(R.id.cusnumber);
        cusADDRESS = (EditText) findViewById(R.id.cusaddress);
        cusPASSWORD = (EditText) findViewById(R.id.cuspassword);
        registerBUTTON = (Button) findViewById(R.id.registerbtn);
        fullSCREEN = findViewById(R.id.fullscreen);
        setmessege = (TextInputLayout) findViewById(R.id.cusemailLayout);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerbtn:

                //todo create with temporytabledata
                createCustomer = new Customer(cusNAME.getText().toString(), cusEMAIL.getText().toString(),
                        cusPASSWORD.getText().toString(), cusNUMBER.getText().toString(), cusADDRESS.getText().toString());
                if (databaseHelper.createcustomerAccount(createCustomer) == true) {
                    int userid = databaseHelper.customeridGET(cusEMAIL.getText().toString());


                    List<Tempordertable> temp = databaseHelper.alldataTempordertable();
                    for (Tempordertable tempallitem : temp) {
                        Ordertable temptoordertable = new Ordertable(tempallitem.getProdid(), tempallitem.getProdname(), tempallitem.getProdtype(),
                                tempallitem.getProdprice(), tempallitem.getProdbrand(), tempallitem.getProddescription(),
                                tempallitem.getProdquantity(), tempallitem.getProdimage(), (tempallitem.getProdquantity() * tempallitem.getProdprice()),
                                tempallitem.getStatus(), userid, cusEMAIL.getText().toString(), cusNAME.getText().toString(),
                                cusNUMBER.getText().toString(), cusADDRESS.getText().toString());
                        databaseHelper.insertordertable(temptoordertable);
                        databaseHelper.deleteaftercreateaccount();

                        startActivity(new Intent(getBaseContext(), LoginActivity.class));


                    }
                    Toast.makeText(getBaseContext(), "Account Create", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getBaseContext(), "Account didn't Create", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }


    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}