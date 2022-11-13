package com.pizza4u.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.activities.CusOrderActivity;
import com.pizza4u.models.OrderModel;

import java.util.List;
import java.util.Objects;

public class EmpOrderRecycleAdapter extends RecyclerView.Adapter<EmpOrderRecycleAdapter.OrdersViewHolder> {

    Context mContext;
    private int position;
    List<OrderModel> orderModelList;

    public EmpOrderRecycleAdapter(Context mContext, List<OrderModel> orderModelList) {
        this.mContext = mContext;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_order_container,parent,false);
        return new EmpOrderRecycleAdapter.OrdersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;

        holder.txtOrderid.setText("Order "+orderModelList.get(position).getOrderId());
        holder.txtPrice.setText(orderModelList.get(position).getTotal().toString());
        holder.txtDate.setText(orderModelList.get(position).getDateTime().toString());
        holder.txtLocation.setText(orderModelList.get(position).getLatitude()+","+orderModelList.get(position).getLatitude());
        int s = 1;
        switch(orderModelList.get(position).getStatus()){
            case "Queued" : s=1;
            case "Preparing" : s=2;
            case "Delivering" : s=3;
            case "Completed" : s=4;
        }

        holder.spinnerStatus.setSelection(s);

        holder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderModelList.get(position).setStatus(holder.spinnerStatus.getSelectedItem().toString());
            }
        });

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
        return 0;
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder{

        public TextView txtOrderid,txtDate,txtPrice,txtLocation;
        public Spinner spinnerStatus;
        public Button btnSave,btnLocation;
        ConstraintLayout oLayout;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderid=itemView.findViewById(R.id.txtOrderNum_emp);
            txtPrice=itemView.findViewById(R.id.txtOrderPrice_emp);
            txtDate=itemView.findViewById(R.id.txtDate_emp);
            spinnerStatus=itemView.findViewById(R.id.spinnerStatus);
            btnSave=itemView.findViewById(R.id.btnSave);
            btnLocation=itemView.findViewById(R.id.btn_location);
        }
    }
}
