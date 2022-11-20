package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.CusOrderItemsRecycleAdapter;
import com.pizza4u.models.OrderItemModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;

public class CusOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;
    ArrayList<OrderItemModel> orderItemModelArrayList;
    CusOrderItemsRecycleAdapter orderItemsRecycleAdapter;
    private String orderid,tot;
    private TextView txtOrderid,txttot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_order);

        txtOrderid = findViewById(R.id.txt_orderId);
        txttot = findViewById(R.id.txt_totalPrice);
        recyclerView = findViewById(R.id.recycler_orderItems);

        orderid=getIntent().getStringExtra("orderId");
        tot=getIntent().getStringExtra("price");
        email=getIntent().getStringExtra("email");
        Log.d("mail",email);

        txttot.setText(tot);
        txtOrderid.setText(orderid);

        orderItemModelArrayList=new ArrayList<>();

        db.collection("order-items")
                .whereEqualTo("userEmail",email)
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
                                    orderItemsRecycleAdapter = new CusOrderItemsRecycleAdapter(CusOrderActivity.this,orderItemModelArrayList);
                                    orderItemsRecycleAdapter.notifyDataSetChanged();

                                }
                                if(!orderItemModelArrayList.isEmpty()){
                                    recyclerView.setAdapter(orderItemsRecycleAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(CusOrderActivity.this));
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }else {
                                Log.d("Items","Empty");
                            }
                        }
                    }

                });


    }
}