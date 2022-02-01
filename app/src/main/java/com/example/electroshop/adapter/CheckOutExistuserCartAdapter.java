package com.example.electroshop.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.electroshop.R;
import com.example.electroshop.activity.CartActivity;
import com.example.electroshop.activity.CreateCustomerAccount;
import com.example.electroshop.activity.ProductDetailsActivity;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Ordertable;

import java.util.ArrayList;
import java.util.List;

public class CheckOutExistuserCartAdapter extends RecyclerView.Adapter<CheckOutExistuserCartAdapter.CartViewHolder> {
    Context context;

    Ordertable updatequantityOrdertablecart;


    DatabaseHelper databaseHelper;

    List<Ordertable> ordertableList = new ArrayList<>();
    String email;
    SharedPreferences getsharedpref;


    public CheckOutExistuserCartAdapter(Context ct, List<Ordertable> ordertableList) {
        this.context = ct;
        this.ordertableList = ordertableList;
        notifyDataSetChanged();
        getsharedpref = context.getSharedPreferences("LoginCustomer", Context.MODE_PRIVATE);
        email = (getsharedpref.getString("Email", ""));///// ----""(doublecode) means default value
        Log.d("TAG", "User login email: " + email);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.checkoutproductlist, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        databaseHelper = new DatabaseHelper(context);
        Glide.with(context).load(String.valueOf(ordertableList.get(position).getProdimage())).into(holder.cartIMG);

        holder.cartprodNAME.setText(ordertableList.get(position).getProdname());
        holder.cartprodPRICE.setText(String.valueOf(ordertableList.get(position).getProdprice()));
        holder.cartprodtotalPRICE.setText(String.valueOf(ordertableList.get(position).getTotalprice()));
        holder.quantity.setText(String.valueOf(ordertableList.get(position).getProdquantity()));


        holder.quanttyaddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quant = String.valueOf((Double.parseDouble(holder.quantity.getText().toString()) + 1));
                holder.quantity.setText(quant.substring(0, quant.indexOf(".")));
                holder.cartprodtotalPRICE.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) * Double.parseDouble(holder.cartprodPRICE.getText().toString())));

            }
        });

        holder.quanttyminusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.quantity.getText().toString()) > 0) {
                    String quant = String.valueOf((Double.parseDouble(holder.quantity.getText().toString()) - 1));
                    holder.quantity.setText(quant.substring(0, quant.indexOf(".")));
                    holder.cartprodtotalPRICE.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) * Double.parseDouble(holder.cartprodPRICE.getText().toString())));
                }
            }
        });


        /////////////////////////////////////////////////////////
        /////////////////////Remove Item///////////////////////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        //TODO delete and refresh

        holder.cartremoveProductBTN.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

                    builder1.setTitle(Html.fromHtml("<font color='#038C73'>Remove Product</font>"));

                    builder1.setMessage("Yes to remove product");
                    builder1.setCancelable(true);


                    builder1.setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Yes</font>"), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
//todo code for delete checkout

                            databaseHelper.deleteOrdercartProduct(ordertableList.get(position).getProdid(), email, "checkout");
                            ordertableList.remove(position);
                            notifyDataSetChanged();

                            dialog.cancel();
                        }
                    });
                    builder1.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    //// todo update in ordertable
                    if (Integer.parseInt(holder.quantity.getText().toString()) < 1) {
                        new AlertDialog.Builder(context)
                                .setTitle(Html.fromHtml("<font color='#038C73'>At least one product to Order</font>"))
                                .setMessage(Html.fromHtml("<font color='#038C73'>one product to Order or Remove from Checkout</font>"))
                                .setCancelable(false)
                                .setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Remove items</font>"), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        databaseHelper.deleteOrdercartProduct(ordertableList.get(position).getProdid(), email, "checkout");
                                        ordertableList.remove(position);
                                        notifyDataSetChanged();

                                    }
                                }).setNegativeButton("Add 1 quantity", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                holder.quantity.setText("1");
                                holder.cartprodtotalPRICE.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) * Double.parseDouble(holder.cartprodPRICE.getText().toString())));
                            }
                        }).show();

                    }

                    updatequantityOrdertablecart = new Ordertable(ordertableList.get(position).getProdid(),
                            (Integer.parseInt(holder.quantity.getText().toString())),
                            (Double.parseDouble(holder.quantity.getText().toString()) * ordertableList.get(position).getProdprice()), "checkout", email);
                    databaseHelper.updateordertablequantity(updatequantityOrdertablecart);


                } catch (Exception e) {

                    Log.d("Numberformaterror", "quantity number errror");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return ordertableList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartIMG;
        TextView quanttyaddBTN, quanttyminusBTN;
        EditText quantity;
        TextView cartprodNAME, cartprodPRICE, prodStock, cartprodtotalPRICE, cartremoveProductBTN;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartIMG = itemView.findViewById(R.id.cartprodimg);
            quantity = itemView.findViewById(R.id.cartprodquantity);
            cartprodNAME = itemView.findViewById(R.id.cartprodname);
            cartprodPRICE = itemView.findViewById(R.id.cartprodprice);
            prodStock = itemView.findViewById(R.id.cartprodstock);
            cartprodtotalPRICE = itemView.findViewById(R.id.cartprodtotalprice);


            ///// button
            quanttyaddBTN = itemView.findViewById(R.id.addprodquantitybtn);
            quanttyminusBTN = itemView.findViewById(R.id.minusquantitybtn);
            cartremoveProductBTN = itemView.findViewById(R.id.cartprodremovebtn);


            CardView cardV = itemView.findViewById(R.id.cartviewcheckout);

            cardV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////Todo pass single produc to product details
                    int i = getAdapterPosition();
                    Intent clickProduct = new Intent(context, ProductDetailsActivity.class);
                    clickProduct.putExtra("singleprodid", String.valueOf(ordertableList.get(i).getProdid()));
                    clickProduct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(clickProduct);
                    Log.d("ooo", String.valueOf(ordertableList.get(i).getProdname()));

                }
            });


        }
    }


}
