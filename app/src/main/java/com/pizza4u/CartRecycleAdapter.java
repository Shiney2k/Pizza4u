package com.pizza4u;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartRecycleAdapter.CartViewHolder> {

    private Context mContext;
    private ArrayList name,photo,price,count;
    private int position;

    public CartRecycleAdapter(Context mContext,ArrayList name,ArrayList photo,ArrayList price,ArrayList count) {
        this.mContext=mContext;
        this.name=name;
        this.photo=photo;
        this.price=price;
        this.count=count;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_cart_item,parent,false);
        return new CartRecycleAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;
        holder.txtName.setText(String.valueOf(name.get(position)));
        holder.txtPrice.setText(String.valueOf(price.get(position)));
        holder.txtCount.setText(String.valueOf(count.get(position)));

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtPrice,txtCount;
        public ImageButton btnplus,btnminus;
        public ImageView img;
        ConstraintLayout cartItemLayout;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtPizzaNameCart);
            txtPrice=itemView.findViewById(R.id.txtPizzaPriceCart);
            txtCount=itemView.findViewById(R.id.txtPizzaCount);
            btnminus=itemView.findViewById(R.id.btnMinus);
            btnplus=itemView.findViewById(R.id.btnPlus);
            cartItemLayout=itemView.findViewById(R.id.cartItemLayout);

            btnplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            btnminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
