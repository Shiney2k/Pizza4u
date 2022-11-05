package com.pizza4u;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CusCartFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList name,price,count;
    private CartRecycleAdapter cartRecycleAdapter;
    private TextView txttot;
    private Button btnOrder;
    //private DatabaseHelper newDB;


    public CusCartFragment() {
        // Required empty public constructor
    }

    public static CusCartFragment newInstance(String param1, String param2) {
        CusCartFragment fragment = new CusCartFragment();
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cus_cart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_cart);
        txttot = view.findViewById(R.id.textTotal);
        btnOrder=view.findViewById(R.id.btnPlaceOrder);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        count = new ArrayList<>();

        //Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
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
//            }

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}