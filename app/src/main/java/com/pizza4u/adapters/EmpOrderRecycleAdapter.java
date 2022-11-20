package com.pizza4u.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pizza4u.R;
import com.pizza4u.activities.CusOrderActivity;
import com.pizza4u.activities.EmpOrderActivity;
import com.pizza4u.models.OrderModel;

import java.util.List;
import java.util.Objects;

public class EmpOrderRecycleAdapter extends RecyclerView.Adapter<EmpOrderRecycleAdapter.OrdersViewHolder> {

    Context mContext;
    List<OrderModel> orderModelList;

    public EmpOrderRecycleAdapter(Context mContext, List<OrderModel> orderModelList) {
        this.mContext = mContext;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_emp_order_container,parent,false);
        return new EmpOrderRecycleAdapter.OrdersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtmail.setText(orderModelList.get(position).getUserEmail());
        holder.txtOrderid.setText(orderModelList.get(position).getOrderId());
        holder.txtPrice.setText(orderModelList.get(position).getTotal().toString());
        holder.txtDate.setText(orderModelList.get(position).getDateTime().toString());
        holder.txtLocation.setText(orderModelList.get(position).getLatitude()+","+orderModelList.get(position).getLatitude());
        int s = 0;
        switch(orderModelList.get(position).getStatus()){
            case "Queued" : s=0;
                 break;
            case "Preparing" : s=1;
                 break;
            case "Delivering" : s=2;
                 break;
            case "Completed" : s=3;
                 break;
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

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                CollectionReference dbpt = db.collection("orders");
                DocumentReference documentReference = dbpt.document(orderModelList.get(position).getDocId());

                orderModelList.get(position).setStatus(holder.spinnerStatus.getSelectedItem().toString());

                documentReference.set(orderModelList.get(position)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Yes button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("Order Status Updated successfully.").setPositiveButton("Ok", dialogClickListener)
                                .show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Failed to update status.").setMessage("Error: " + String.valueOf(e)).setPositiveButton("Ok", dialogClickListener)
                                .show();
                    }
                });
            }
        });

        holder.oLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, EmpOrderActivity.class);
                intent.putExtra("orderId",String.valueOf(orderModelList.get(position).getOrderId()));
                intent.putExtra("price",String.valueOf(orderModelList.get(position).getTotal()));
                intent.putExtra("mail",orderModelList.get(position).getUserEmail());
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

        public TextView txtOrderid,txtDate,txtPrice,txtLocation,txtmail;
        public Spinner spinnerStatus;
        public Button btnSave;
        public ImageButton btnLocation;
        ConstraintLayout oLayout;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderid=itemView.findViewById(R.id.txtOrderNum_emp);
            txtPrice=itemView.findViewById(R.id.txtOrderPrice_emp);
            txtDate=itemView.findViewById(R.id.txtDate_emp);
            spinnerStatus=itemView.findViewById(R.id.spinnerStatus);
            btnSave=itemView.findViewById(R.id.btnSaveempo);
            btnLocation=itemView.findViewById(R.id.btn_location);
            txtLocation=itemView.findViewById(R.id.txt_location_order);
            txtmail=itemView.findViewById(R.id.txtOrdermailemp);
            oLayout=itemView.findViewById(R.id.layout_emporder);
        }
    }
}
