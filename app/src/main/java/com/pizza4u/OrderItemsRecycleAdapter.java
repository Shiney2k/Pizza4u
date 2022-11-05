package com.pizza4u;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderItemsRecycleAdapter extends RecyclerView.Adapter<OrderItemsRecycleAdapter.OrderItemsViewHolder> {

    Context mContext;
    private ArrayList name,count,price;
    private int position;

    public OrderItemsRecycleAdapter(Context mContext,ArrayList name,ArrayList count,ArrayList price) {
        this.mContext=mContext;
        this.name = name;
        this.count=count;
        this.price=price;
    }

    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_order_item,parent,false);
        return new OrderItemsRecycleAdapter.OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;

        holder.txtName.setText(String.valueOf(name.get(position)));
        holder.txtPrice.setText(String.valueOf(price.get(position)));
        holder.txtCount.setText(String.valueOf(count.get(position)));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class OrderItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtPrice,txtCount;
        ConstraintLayout oLayout;

        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtCount=itemView.findViewById(R.id.txtCount);

        }
    }
}