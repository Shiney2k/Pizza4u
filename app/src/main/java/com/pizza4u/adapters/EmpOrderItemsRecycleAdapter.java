package com.pizza4u.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.models.OrderItemModel;

import java.util.List;

public class EmpOrderItemsRecycleAdapter extends RecyclerView.Adapter<EmpOrderItemsRecycleAdapter.OrderItemsViewHolder>{

    Context mContext;
    private List<OrderItemModel> orderItemModelList;

    public EmpOrderItemsRecycleAdapter(Context mContext, List<OrderItemModel> orderItemModelList) {
        this.mContext = mContext;
        this.orderItemModelList = orderItemModelList;
    }


    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_emp_order_item,parent,false);
        return new EmpOrderItemsRecycleAdapter.OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtName.setText(String.valueOf(orderItemModelList.get(position).getPizzaName()));
        holder.txtPrice.setText(String.valueOf(orderItemModelList.get(position).getSubTotal()));
        holder.txtCount.setText(String.valueOf(orderItemModelList.get(position).getCount()));
        holder.txtSize.setText(String.valueOf(orderItemModelList.get(position).getSize()));
    }

    @Override
    public int getItemCount() {
        return orderItemModelList.size();
    }

    public static class OrderItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtPrice,txtCount,txtSize;
        ConstraintLayout oLayout;

        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName_emp);
            txtPrice=itemView.findViewById(R.id.txtPrice_emp);
            txtCount=itemView.findViewById(R.id.txtCount_emp);
            txtSize=itemView.findViewById(R.id.txtSize_emp);

        }
    }
}
