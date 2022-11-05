package com.pizza4u;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersRecycleAdapter extends RecyclerView.Adapter<OrdersRecycleAdapter.OrdersViewHolder>{
    Context mContext;
    private ArrayList orderid,price,date,status;
    private int position;

    public OrdersRecycleAdapter(Context mContext,ArrayList orderid,ArrayList price,ArrayList status,ArrayList date) {
        this.mContext=mContext;
        this.orderid=orderid;
        this.price=price;
        this.date = date;
        this.status=status;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_order_container,parent,false);
        return new OrdersRecycleAdapter.OrdersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;

        holder.txtOrderid.setText(String.valueOf(orderid.get(position)));
        holder.txtPrice.setText(String.valueOf(price.get(position)));
        holder.txtDate.setText(String.valueOf(date.get(position)));
        holder.txtStatus.setText(String.valueOf(status.get(position)));

        holder.oLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,CusOrderActivity.class);
                intent.putExtra("orderId",String.valueOf(orderid.get(position)));
                intent.putExtra("price",String.valueOf(price.get(position)));
                //intent.putExtra("date",String.valueOf(date.get(position)));
                //intent.putExtra("status",String.valueOf(status.get(position)));

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder{

        public TextView txtOrderid,txtDate,txtPrice,txtStatus;
        ConstraintLayout oLayout;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderid=itemView.findViewById(R.id.txtOrderNum);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtStatus=itemView.findViewById(R.id.txtStatus);
        }
    }
}
