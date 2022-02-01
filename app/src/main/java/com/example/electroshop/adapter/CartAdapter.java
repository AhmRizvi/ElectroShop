package com.example.electroshop.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.electroshop.activity.ProductDetailsActivity;
import com.example.electroshop.database.DatabaseHelper;
import com.example.electroshop.model.Tempordertable;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    DatabaseHelper databaseHelper;

    Tempordertable updatetempordertablecart;

    List<Tempordertable> tempcartlist = new ArrayList<>();

    public CartAdapter(Context ct, List<Tempordertable> tempcartlist) {
        this.context = ct;
        this.tempcartlist = tempcartlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cartproductlist, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

        databaseHelper = new DatabaseHelper(context);
        Glide.with(context).load(String.valueOf(tempcartlist.get(position).getProdimage())).into(holder.cartIMG);


        holder.cartprodNAME.setText(tempcartlist.get(position).getProdname());
        holder.cartprodPRICE.setText(String.valueOf(tempcartlist.get(position).getProdprice()));
        holder.cartprodtotalPRICE.setText(String.valueOf(tempcartlist.get(position).getTotalprice()));
        holder.quantity.setText(String.valueOf(tempcartlist.get(position).getProdquantity()));

        holder.quanttyaddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quant = String.valueOf((Integer.parseInt(holder.quantity.getText().toString()) + 1));
                holder.quantity.setText(quant);
                holder.cartprodtotalPRICE.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) * Double.parseDouble(holder.cartprodPRICE.getText().toString())));

            }
        });

        holder.quanttyminusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.quantity.getText().toString()) > 0) {
                    String quant = String.valueOf((Integer.parseInt(holder.quantity.getText().toString()) - 1));
                    holder.quantity.setText(quant);
                    holder.cartprodtotalPRICE.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) * Double.parseDouble(holder.cartprodPRICE.getText().toString())));
                }
            }
        });


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

                            databaseHelper.deleteTempcartProduct(tempcartlist.get(position).getProdname());
                            tempcartlist.remove(position);
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

                    Toast.makeText(context, "Product Remove from cart", Toast.LENGTH_SHORT).show();
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

                    updatetempordertablecart = new Tempordertable(tempcartlist.get(position).getProdid(), (Integer.parseInt(holder.quantity.getText().toString())),
                            (Double.parseDouble(holder.quantity.getText().toString()) * tempcartlist.get(position).getProdprice()));
                    databaseHelper.updateTempordertable(updatetempordertablecart);


                } catch (Exception e) {

                    Log.d("Numberformaterror", "quantity number errror");

                }
            }
        });

        holder.cartprodbuyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.quantity.getText().toString()) > 0) {
                    databaseHelper.checkouttemptabe("checkout", tempcartlist.get(position).getProdid());
                    tempcartlist.remove(position);
                    notifyDataSetChanged();
                    Snackbar.make(v, "Product Checkout complete", Snackbar.LENGTH_LONG).show();

                }
                if (Integer.parseInt(holder.quantity.getText().toString()) == 0) {
                    new AlertDialog.Builder(context)
                            .setTitle(Html.fromHtml("<font color='#FF7F27'>To checkout</font>"))
                            .setMessage(Html.fromHtml("<font color='#038C73'> takes at least one product to check out</font>"))
                            .setCancelable(true)
                            .setPositiveButton(Html.fromHtml("<font color='#FF7F27'>Ok</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return tempcartlist.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartIMG;
        TextView quanttyaddBTN, quanttyminusBTN;
        EditText quantity;
        TextView cartprodNAME, cartprodPRICE, prodStock, cartprodtotalPRICE, cartremoveProductBTN, cartprodbuyBTN;

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
            cartprodbuyBTN = itemView.findViewById(R.id.cartprodbuybtn);
            CardView cardV = itemView.findViewById(R.id.cartviewCart);

            cardV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////Todo pass single produc to product details
                    int i = getAdapterPosition();
                    Intent clickProduct = new Intent(context, ProductDetailsActivity.class);
                    clickProduct.putExtra("singleprodid", String.valueOf(tempcartlist.get(i).getProdid()));
                    clickProduct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(clickProduct);
                    Log.d("ooo", String.valueOf(tempcartlist.get(i).getProdname()));

                }
            });
        }
    }
}
