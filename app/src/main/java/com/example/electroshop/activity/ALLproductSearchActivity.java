package com.example.electroshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroshop.R;
import com.example.electroshop.adapter.MyAdapter;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ALLproductSearchActivity extends AppCompatActivity implements View.OnClickListener {
    SearchView search;
    RecyclerView recyc;
    DatabaseHelper databaseHelper;
    MyAdapter myAdapter;

    MenuItem searchItem, menuItem;
    ImageView cartIMG;


    String email;
    SharedPreferences getsharedpreferences;

    List<Product> allProd = new ArrayList<>();
    List<Product> queryProd = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        searchItem = menu.findItem(R.id.searchicon);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                    queryProd = databaseHelper.searchProduct(s);
                    ////////////1st
                    myAdapter = new MyAdapter(getBaseContext(), queryProd);
                    GridLayoutManager quegridLayoutManager = new GridLayoutManager(getBaseContext(), 3, GridLayoutManager.VERTICAL, false);

                    recyc.setAdapter(myAdapter);
                    recyc.setLayoutManager(quegridLayoutManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    if (s.equals("")) {
                        recyc.setAdapter(null);
                    } else {
                        queryProd = databaseHelper.searchProduct(s);
                        ////////////1st
                        myAdapter = new MyAdapter(getBaseContext(), queryProd);
                        GridLayoutManager quegridLayoutManager = new GridLayoutManager(getBaseContext(), 3, GridLayoutManager.VERTICAL, false);

                        recyc.setAdapter(myAdapter);
                        recyc.setLayoutManager(quegridLayoutManager);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproduct_search);
        databaseHelper = new DatabaseHelper(this);

        casting();


        try {
            allProd = databaseHelper.allProduct();
            ////////////1st
            myAdapter = new MyAdapter(this, allProd);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

            recyc.setAdapter(myAdapter);
            recyc.setLayoutManager(gridLayoutManager);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void casting() {
        recyc = findViewById(R.id.searchRecycl);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }


    }
}