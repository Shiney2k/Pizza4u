package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.CusOrderItemsRecycleAdapter;
import com.pizza4u.adapters.EmpOrderItemsRecycleAdapter;
import com.pizza4u.models.OrderItemModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;

public class EmpOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<OrderItemModel> orderItemModelArrayList;
    EmpOrderItemsRecycleAdapter orderItemsRecycleAdapter;
    private String orderid,tot;
    private TextView txtOrderid,txttot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_order);

        txtOrderid = findViewById(R.id.txt_orderId_emp);
        txttot = findViewById(R.id.txt_totalPrice_emp);
        recyclerView = findViewById(R.id.recycler_orderItems_emp);

        orderid=getIntent().getStringExtra("orderId");
        tot=getIntent().getStringExtra("price");

        txttot.setText(tot);
        txtOrderid.setText(orderid);

        orderItemModelArrayList=new ArrayList<>();

        db.collection("orders")
                .whereEqualTo("orderID",orderid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(!task.getResult().isEmpty()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Log.d(TAG, document.getId() + " => " + document.getData());
                                    String documentid = document.getId();
                                    // Log.d("Email", email);

                                    OrderItemModel orderItemModel = document.toObject(OrderItemModel.class);
                                    orderItemModelArrayList.add(orderItemModel);
                                    orderItemsRecycleAdapter.notifyDataSetChanged();

                                }
                            }}
                    }

                });


        orderItemsRecycleAdapter = new EmpOrderItemsRecycleAdapter(EmpOrderActivity.this,orderItemModelArrayList);
        recyclerView.setAdapter(orderItemsRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmpOrderActivity.this));

    }
}