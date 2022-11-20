package com.pizza4u.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.activities.AddPizzaActivity;
import com.pizza4u.activities.AddPizzaTypeActivity;
import com.pizza4u.adapters.ManagerPTypesRecycleAdapter;
import com.pizza4u.adapters.PizzaTypeRecycleAdapter;
import com.pizza4u.models.PizzaTypeModel;

import java.util.ArrayList;

public class ManagerPizzaTypesFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnNew;
    private ManagerPTypesRecycleAdapter pizzaTypeRecycleAdapter;
    ArrayList<PizzaTypeModel> pizzaTypeModelArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ManagerPizzaTypesFragment() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static ManagerPizzaTypesFragment newInstance(String param1, String param2) {
        ManagerPizzaTypesFragment fragment = new ManagerPizzaTypesFragment();
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
        return inflater.inflate(R.layout.fragment_manager_pizza_types, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_pizza_types_manager);
        btnNew=view.findViewById(R.id.btn_add_pizza_type);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPizzaTypeActivity.class);
                startActivity(intent);
            }
        });

        pizzaTypeModelArrayList=new ArrayList<>();

        db.collection("pizza-type")
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

                                    PizzaTypeModel pizzaTypeModel = document.toObject(PizzaTypeModel.class);
                                    pizzaTypeModelArrayList.add(pizzaTypeModel);
                                    pizzaTypeRecycleAdapter = new ManagerPTypesRecycleAdapter(getContext(), pizzaTypeModelArrayList);
                                    pizzaTypeRecycleAdapter.notifyDataSetChanged();
                                }
                                if(!pizzaTypeModelArrayList.isEmpty()) {
                                    recyclerView.setAdapter(pizzaTypeRecycleAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                }else{
                                    recyclerView.setVisibility(View.GONE);
                                    Log.d("else","Hi empty");
                                }
                            }}
                    }

                });



    }
}