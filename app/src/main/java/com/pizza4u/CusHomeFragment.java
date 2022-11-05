package com.pizza4u;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class CusHomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList pizzaType, ptPhoto;
    private PizzaTypeRecycleAdapter pizzaTypeRecycleAdapter;
    //private DatabaseHelper newDB;

    public static CusHomeFragment newInstance(String param1, String param2) {
        CusHomeFragment fragment = new CusHomeFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cus_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerPTypes);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);
        pizzaType = new ArrayList<>();
        ptPhoto = new ArrayList<>();

        displayData();

        pizzaTypeRecycleAdapter = new PizzaTypeRecycleAdapter(getContext(), pizzaType, ptPhoto);
        recyclerView.setAdapter(pizzaTypeRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }        //if (cursor.getCount() == 0) {


    private void displayData() {
        //Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
//            imgNoNotes.setVisibility(View.VISIBLE);
//            txtNoNotes.setVisibility(View.VISIBLE);
//        } else {
//            while(cursor.moveToNext()){
//                pizzaType.add(cursor.getString(0));
//                ptPhoto.add(cursor.getString(1));
//            }
//            imgNoNotes.setVisibility(View.GONE);
//            txtNoNotes.setVisibility(View.GONE);
//            }
    }
}