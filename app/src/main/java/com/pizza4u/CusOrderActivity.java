package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CusOrderActivity extends AppCompatActivity {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList name,price,count;
    private String orderid,tot;
    private TextView txtOrderid,txttot;
    private OrderItemsRecycleAdapter.OrderItemsViewHolder orderItemsViewHolder;
    //private DatabaseHelper newDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_order);

        txtOrderid = findViewById(R.id.txt_orderId);
        txttot = findViewById(R.id.txt_totalPrice);
        recyclerView = findViewById(R.id.recycler_orderItems);

        orderid=getIntent().getStringExtra("orderId");
        tot=getIntent().getStringExtra("price");

        txttot.setText(tot);
        txtOrderid.setText(orderid);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        count = new ArrayList<>();

        //        Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
//
//        if (cursor.getCount() == 0) {
//            imgNoNotes.setVisibility(View.VISIBLE);
//            txtNoNotes.setVisibility(View.VISIBLE);
//        } else {
//            while(cursor.moveToNext()){
//                name.add(cursor.getString(0));
//                price.add(cursor.getString(1));
//                count.add(cursor.getString(2));
//            }
//            imgNoNotes.setVisibility(View.GONE);
//            txtNoNotes.setVisibility(View.GONE);
//        }

        OrderItemsRecycleAdapter orderItemsRecycleAdapter = new OrderItemsRecycleAdapter(CusOrderActivity.this,name,count,price);
        recyclerView.setAdapter(orderItemsRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CusOrderActivity.this));


    }
}