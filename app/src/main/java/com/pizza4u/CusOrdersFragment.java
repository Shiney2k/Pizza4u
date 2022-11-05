package com.pizza4u;

import androidx.fragment.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class CusOrdersFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList orderid,price,date,status;
    private OrdersRecycleAdapter ordersRecycleAdapter;
    //private DatabaseHelper newDB;

    public CusOrdersFragment() {
        // Required empty public constructor
    }

    public static CusOrdersFragment newInstance(String param1, String param2) {
        CusOrdersFragment fragment = new CusOrdersFragment();
        Bundle args = new Bundle();
    
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cus_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler_orders);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);
        orderid = new ArrayList<>();
        price = new ArrayList<>();
        status = new ArrayList<>();
        date = new ArrayList<>();

        displayData();

        ordersRecycleAdapter = new OrdersRecycleAdapter(getContext(),orderid,price,status,date);
        recyclerView.setAdapter(ordersRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void displayData() {
//        Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
//            imgNoNotes.setVisibility(View.VISIBLE);
//            txtNoNotes.setVisibility(View.VISIBLE);
//        } else {
//            while(cursor.moveToNext()){
//                orderid.add(cursor.getString(0));
//                price.add(cursor.getString(1));
//                date.add(cursor.getString(2));
//                status.add(cursor.getString(3));
//            }
//            imgNoNotes.setVisibility(View.GONE);
//            txtNoNotes.setVisibility(View.GONE);
    }

}
