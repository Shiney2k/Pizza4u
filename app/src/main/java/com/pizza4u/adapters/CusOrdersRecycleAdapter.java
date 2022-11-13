package com.pizza4u.adapters;

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

import com.pizza4u.activities.CusOrderActivity;
import com.pizza4u.R;
import com.pizza4u.models.OrderModel;

import java.util.List;

public class CusOrdersRecycleAdapter extends RecyclerView.Adapter<CusOrdersRecycleAdapter.OrdersViewHolder>{
    Context mContext;
    private int position;
    List<OrderModel> orderModelList;


    public CusOrdersRecycleAdapter(Context context, List<OrderModel> orderModelArrayList) {
        this.mContext=context;
        this.orderModelList=orderModelArrayList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_order_container,parent,false);
        return new CusOrdersRecycleAdapter.OrdersViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;

        holder.txtOrderid.setText("Order "+orderModelList.get(position).getOrderId());
        holder.txtPrice.setText(orderModelList.get(position).getTotal().toString());
        holder.txtDate.setText(orderModelList.get(position).getDateTime().toString());
        holder.txtStatus.setText(orderModelList.get(position).getStatus());

        holder.oLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, CusOrderActivity.class);
                intent.putExtra("orderId",String.valueOf(orderModelList.get(position).getOrderId()));
                intent.putExtra("price",String.valueOf(orderModelList.get(position).getTotal()));
                //intent.putExtra("date",String.valueOf(date.get(position)));
                //intent.putExtra("status",String.valueOf(status.get(position)));

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
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
