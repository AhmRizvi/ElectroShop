package com.example.electroshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.electroshop.R;
import com.example.electroshop.adapter.MyAdapter;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView laptopRecyclerView, deskRecyclerView, accessoriesRecyclerview;
    MenuItem menuItem, searchItem;
    TextView cartcounter;
    int cartiteamquantity = 0;
    ImageView cartIMG;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseHelper databaseHelper;

    String email;
    SharedPreferences getsharedpreferences;


    List<Product> allLap = new ArrayList<>();
    List<Product> allDESK = new ArrayList<>();
    List<Product> allACCERO = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_notification, menu);


        menuItem = menu.findItem(R.id.cart_notifi);
        searchItem = menu.findItem(R.id.searchicon);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getBaseContext(), ALLproductSearchActivity.class));
                return false;
            }
        });

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


        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseHelper = new DatabaseHelper(this);


        getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        email = (getsharedpreferences.getString("Email", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "User login email: " + email);

        //todo check
        if (email != "") {
            cartiteamquantity = databaseHelper.totalcartordertablequantity("cart", email);
            Log.d("onClickfhhh: ", String.valueOf(cartiteamquantity));
//            cartcounter.setText(String.valueOf(cartiteamquantity));

        }
        if (email == "") {
            cartiteamquantity = databaseHelper.totalcartTemptablequantity("cart");
//            cartcounter.setText(String.valueOf(cartiteamquantity));
        }


        ////todo code laptop

        laptopRecyclerView = findViewById(R.id.laptoprecyclerVIEW);
        try {
            allLap = databaseHelper.allLaptop();
            ////////////1st
            MyAdapter myAdapter = new MyAdapter(this, allLap);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            laptopRecyclerView.setAdapter(myAdapter);
            laptopRecyclerView.setLayoutManager(layoutManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////todo code laptop end

        ///todo code Desktop

        deskRecyclerView = findViewById(R.id.desktoprecyclerVIEW);
        try {
            allDESK = databaseHelper.allDesktop();
            ////////////1st
            MyAdapter deskAdapter = new MyAdapter(this, allDESK);
            LinearLayoutManager desklayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            deskRecyclerView.setAdapter(deskAdapter);
            deskRecyclerView.setLayoutManager(desklayoutManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///todo code Desktop end
        //
        // /todo code Desktop

        accessoriesRecyclerview = findViewById(R.id.accessoriesRecy);
        try {
            allACCERO = databaseHelper.allAccessories();
            ////////////1st
            MyAdapter acceAdapter = new MyAdapter(this, allACCERO);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            accessoriesRecyclerview.setLayoutManager(gridLayoutManager);
            accessoriesRecyclerview.setAdapter(acceAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///todo code Desktop end


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        ////////////////////////////////////////////////
        /////////////////menu button //////////////////
        actionBarDrawerToggle.syncState();
        /////////////////menu button close //////////////
        ////////////////////////////////////////////////
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_profile:
                        Log.i("MenuDrawer_tag", "Profile item is Clicked");

                        Toast.makeText(getApplicationContext(), "Profile item is Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(getBaseContext(), ALLproductSearchActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_cart:
                        startActivity(new Intent(getBaseContext(), CartActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_order:
                        Toast.makeText(getApplicationContext(), "Order item View", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.nav_setting:
                        Log.i("MenuDrawer_tag", "Setting item is Clicked");

                        Toast.makeText(getApplicationContext(), "Setting item is Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.nav_logout:

                        SharedPreferences mypref = getsharedpreferences = getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditr = mypref.edit();
                        prefsEditr.putString("Email", "");
                        prefsEditr.commit();
//                        Toast.makeText(getApplicationContext(), "Delete share Preference", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));


                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logoimg:
                        Log.i("Logo", "Logo");
                        Toast.makeText(getApplicationContext(), "Logo", Toast.LENGTH_SHORT).show();

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


    }
}