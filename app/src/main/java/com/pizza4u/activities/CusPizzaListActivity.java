package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.PizzasRecycleAdapter;
import com.pizza4u.models.PizzaModel;
import com.pizza4u.models.PizzaTypeModel;

import java.util.ArrayList;

public class CusPizzaListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<PizzaModel> pizzaModelArrayList;
    PizzasRecycleAdapter pizzasRecycleAdapter;
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    private TextView txtpType;
    private String pType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_pizza_list);

        txtpType = findViewById(R.id.txtPizzaType);
        recyclerView = findViewById(R.id.recyclerPList);

        getIntent().hasExtra("pTypeName") ;

        // getting data from intent
        pType = getIntent().getStringExtra("pTypeName");

        // setting intent data
        txtpType.setText(pType);

        pizzaModelArrayList=new ArrayList<>();

        db.collection("pizza")
                .whereEqualTo("pizza_type",pType)
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

                                    PizzaModel pizzaModel = document.toObject(PizzaModel.class);
                                    pizzaModelArrayList.add(pizzaModel);
                                    pizzasRecycleAdapter.notifyDataSetChanged();

                                }
                            }}
                    }

                });


        pizzasRecycleAdapter=new PizzasRecycleAdapter(this,pizzaModelArrayList);
        recyclerView.setAdapter(pizzasRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CusPizzaListActivity.this));

    }


};