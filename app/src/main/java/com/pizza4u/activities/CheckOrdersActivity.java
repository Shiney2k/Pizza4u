package com.pizza4u.activities;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.CusOrdersRecycleAdapter;
import com.pizza4u.adapters.EmpOrderRecycleAdapter;
import com.pizza4u.models.OrderModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;

public class CheckOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<OrderModel> orderModelArrayList;
    EmpOrderRecycleAdapter ordersRecycleAdapter;
    FirebaseFirestore db =FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_orders);

        recyclerView = findViewById(R.id.recycler_orders_employee);

        orderModelArrayList=new ArrayList<>();

        db.collection("orders")
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

                                    OrderModel orderModel = document.toObject(OrderModel.class);
                                    orderModelArrayList.add(orderModel);
                                    ordersRecycleAdapter.notifyDataSetChanged();

                                }
                            }}
                    }

                });

        ordersRecycleAdapter = new EmpOrderRecycleAdapter(this, orderModelArrayList);
        recyclerView.setAdapter(ordersRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}