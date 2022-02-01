package com.example.electroshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.electroshop.R;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Customer;
import com.example.electroshop.model.Ordertable;
import com.example.electroshop.model.Product;
import com.example.electroshop.model.Tempordertable;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView prodImag, minus, plus, backBtn, cartIMAGE;
    private TextView price, name, description, brand, quant, cartQuantity, type;
    private Button addtocartButton;
    String email;
    SharedPreferences getsharedpreferences;
    MenuItem menuItem, searchItem;
    TextView cartcounter;
    int cartiteamquantity = 0;
    ImageView cartIMG;
    List<Product> singleProduct = new ArrayList<>();


    ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseHelper databaseHelper;

    Ordertable ordertablecart, updateorder;

    Tempordertable tempordertablecart;
    Tempordertable updatetempordertablecart;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getBaseContext(), HomeActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_notification, menu);

        searchItem = menu.findItem(R.id.searchicon);
        searchItem.setVisible(false);

        menuItem = menu.findItem(R.id.cart_notifi);
        if (cartiteamquantity < 0) {
            menuItem.setActionView(null);

        } else {
            menuItem.setActionView(R.layout.cart_badge);
            View view = menuItem.getActionView();
            cartcounter = view.findViewById(R.id.cartvalue);
            cartIMG = view.findViewById(R.id.cartimg);
            cartcounter.setText(String.valueOf(cartiteamquantity));
            cartcounter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), CartActivity.class));
                }
            });
            cartIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), CartActivity.class));

                }
            });
        }


        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        try {

            if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

        } catch (Exception e) {
            ///todo error part
            Log.d("onOpterror", "ActionBarDrawerToggle.onOptionsItemSelected(android.view.MenuItem)' on a null object reference ");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        databaseHelper = new DatabaseHelper(this);
        casting();
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        addtocartButton.setOnClickListener(this);
        getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "User login email: " + email);

        //// todo sharefe check
        if (email != "") {

            cartiteamquantity = databaseHelper.totalcartordertablequantity("cart", email);

        }
        if (email == "") {
            cartiteamquantity = databaseHelper.totalcartTemptablequantity("cart");
        }

        try {
            Intent i = getIntent();
            singleProduct = databaseHelper.singleProdInfo(getIntent().getStringExtra("singleprodid"));
            name.setText(singleProduct.get(0).getProdname());
            type.setText(singleProduct.get(0).getProdtype());
            price.setText("Price : " + singleProduct.get(0).getProdprice());
            description.setText(singleProduct.get(0).getProddescription() + "\n");
            brand.setText("Brand : " + singleProduct.get(0).getProdbrand());
            Glide.with(this).load(String.valueOf(singleProduct.get(0).getProdimage())).into(prodImag);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void casting() {

        minus = (ImageView) findViewById(R.id.minusBTN);
        plus = (ImageView) findViewById(R.id.plusBTN);
        prodImag = (ImageView) findViewById(R.id.prodIMG);
        quant = (TextView) findViewById(R.id.quantity);
        name = (TextView) findViewById(R.id.productNAME);
        price = (TextView) findViewById(R.id.productPrice);
        type = (TextView) findViewById(R.id.prodtypeid);
        description = (TextView) findViewById(R.id.productDescription);
        brand = (TextView) findViewById(R.id.productBrand);
        addtocartButton = (Button) findViewById(R.id.addToCartBTN);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.plusBTN:
                quant.setText(String.valueOf(Integer.parseInt(quant.getText().toString()) + (1)));
                break;
            case R.id.minusBTN:
                if (Integer.parseInt(quant.getText().toString()) > 0) {
                    quant.setText(String.valueOf(Integer.parseInt(quant.getText().toString()) - (1)));
                }
                break;
            case R.id.addToCartBTN:

                getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
                email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
                Log.d("TAG", "User login email: " + email);

                if (email != "") {
                    ////// Todo exists customer cart

                    Intent i = getIntent();
                    singleProduct = databaseHelper.singleProdInfo(getIntent().getStringExtra("singleprodid"));
                    double totalprice = (singleProduct.get(0).getProdprice() * Integer.parseInt(quant.getText().toString()));

                    Log.d("TAG", databaseHelper.loginuserdetails(email).toString());
                    int cusid = databaseHelper.loginuserdetails(email).get(0).getCusid();
                    String cusemail = databaseHelper.loginuserdetails(email).get(0).getCusemail();
                    String cusname = databaseHelper.loginuserdetails(email).get(0).getCusname();
                    String cusnumber = databaseHelper.loginuserdetails(email).get(0).getCusnumber();
                    String cusaddress = databaseHelper.loginuserdetails(email).get(0).getCusaddress();


                    ordertablecart = new Ordertable(singleProduct.get(0).getProdid(), singleProduct.get(0).getProdname(), singleProduct.get(0).getProdtype(), singleProduct.get(0).getProdprice(),
                            singleProduct.get(0).getProdbrand(), singleProduct.get(0).getProddescription(), Integer.parseInt(quant.getText().toString()), singleProduct.get(0).getProdimage(),
                            totalprice, "cart", cusid, cusemail, cusname, cusnumber, cusaddress);

                    if (databaseHelper.checkproductordercart(singleProduct.get(0).getProdid(), email, "cart") == 1) {
                        int databasequantity = databaseHelper.getorderproductquntity(singleProduct.get(0).getProdid(), email, "cart");

                        databaseHelper.updateodertable((Integer.parseInt(quant.getText().toString()) + databasequantity), (Integer.parseInt(quant.getText().toString()) + databasequantity) * singleProduct.get(0).getProdprice(), singleProduct.get(0).getProdid(), email, "cart");

                    }


                    if (databaseHelper.checkproductordercart(singleProduct.get(0).getProdid(), email, "cart") != 1) {
                        databaseHelper.insertordertable(ordertablecart);

                    }

                    cartiteamquantity = databaseHelper.totalcartordertablequantity("cart", email);
                    Log.d("onClickfhhh: ", String.valueOf(cartiteamquantity));
                    cartcounter.setText(String.valueOf(cartiteamquantity));

                    Log.d("Check", databaseHelper.alldataordertable(email, "cart").toString());
////// Todo exists customer cart end

                }
                if (email == "") {
                    ////todo temptable cart

                    Intent i = getIntent();
                    singleProduct = databaseHelper.singleProdInfo(getIntent().getStringExtra("singleprodid"));
                    double totalprice = (singleProduct.get(0).getProdprice() * Integer.parseInt(quant.getText().toString()));

                    tempordertablecart = new Tempordertable(singleProduct.get(0).getProdid(), singleProduct.get(0).getProdname(), singleProduct.get(0).getProdtype(), singleProduct.get(0).getProdprice(),
                            singleProduct.get(0).getProdbrand(), singleProduct.get(0).getProddescription(), Integer.parseInt(quant.getText().toString()), singleProduct.get(0).getProdimage(),
                            totalprice, "cart"
                    );
                    ///todo edit temp done
                    updatetempordertablecart = new Tempordertable(singleProduct.get(0).getProdid(), (Integer.parseInt(quant.getText().toString()) + databaseHelper.gettempproductquntity(singleProduct.get(0).getProdid())), (Double.parseDouble(quant.getText().toString()) + databaseHelper.gettempproductquntity(singleProduct.get(0).getProdid())) * singleProduct.get(0).getProdprice());

//

                    if (databaseHelper.checkproducttempcart(singleProduct.get(0).getProdid()) == 1) {
                        databaseHelper.updateTempordertable(updatetempordertablecart);

                        databaseHelper.alldataTempordertable().toString();


                    }
                    if (databaseHelper.checkproducttempcart(singleProduct.get(0).getProdid()) != 1) {
                        databaseHelper.insertTempordertable(tempordertablecart);
                        databaseHelper.alldataTempordertable().toString();


                    }


                    cartiteamquantity = databaseHelper.totalcartTemptablequantity("cart");
                    cartcounter.setText(String.valueOf(cartiteamquantity));


                }


                break;
        }


    }
}