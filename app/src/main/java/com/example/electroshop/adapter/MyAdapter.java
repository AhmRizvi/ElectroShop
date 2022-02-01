package com.example.electroshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.electroshop.R;
import com.example.electroshop.activity.ProductDetailsActivity;
import com.example.electroshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Product> allProduct= new ArrayList<>();
    Context context;

    public MyAdapter(Context ct, List<Product> allProduct) {
        this.context = ct;
        this.allProduct = allProduct;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.prodNAME.setText(allProduct.get(i).getProdname().substring(0, 15));
        holder.prodPRICE.setText(String.valueOf(allProduct.get(i).getProdprice()));
        Glide.with(context).load(String.valueOf(allProduct.get(i).getProdimage())).into(holder.prodIMAGE);



    }

    @Override
    public int getItemCount() {
        return allProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prodNAME, prodPRICE;
        ImageView prodIMAGE;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prodNAME = itemView.findViewById(R.id.productNAME);
            prodPRICE = itemView.findViewById(R.id.productPRICE);
            prodIMAGE = itemView.findViewById(R.id.productIMAGE);
            CardView cardV= itemView.findViewById(R.id.cardView);

            cardV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////Todo pass single produc to product details
                    int i = getAdapterPosition();
                    Intent clickProduct = new Intent(context, ProductDetailsActivity.class);
                    clickProduct.putExtra("singleprodid",String.valueOf(allProduct.get(i).getProdid()));
                    clickProduct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(clickProduct);
                    Log.d("ooo", String.valueOf(allProduct.get(i).getProdname()));

                }
            });


        }
    }
}
