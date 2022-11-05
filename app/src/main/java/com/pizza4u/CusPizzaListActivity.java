package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CusPizzaListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList name,photo,price,description;
    private TextView txtpType;
    private String pType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_pizza_list);

        txtpType = findViewById(R.id.txtPizzaType);
        recyclerView = findViewById(R.id.recyclerPList);

        getIntent().hasExtra("pType") ;

            // getting data from intent
            pType = getIntent().getStringExtra("pType");

            // setting intent data
            txtpType.setText(pType);

        //newDB = new DatabaseHelper(MedicalNotesActivity.this);
        name = new ArrayList<>();
        photo = new ArrayList<>();
        price = new ArrayList<>();
        description = new ArrayList<>();

//        Cursor cursor = newDB.displayNotes(Integer.valueOf(MainActivity.id.get(0)));
//
//        if (cursor.getCount() == 0) {
//            imgNoNotes.setVisibility(View.VISIBLE);
//            txtNoNotes.setVisibility(View.VISIBLE);
//        } else {
//            while(cursor.moveToNext()){
//                name.add(cursor.getString(0));
//                photo.add(cursor.getString(1));
//                price.add(cursor.getString(2));
//                description.add(cursor.getString(3));
//            }
//            imgNoNotes.setVisibility(View.GONE);
//            txtNoNotes.setVisibility(View.GONE);
//        }

        PizzasRecycleAdapter pizzaAdapter = new PizzasRecycleAdapter(CusPizzaListActivity.this, name, photo, price, description);
        recyclerView.setAdapter(pizzaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CusPizzaListActivity.this));

    }


};