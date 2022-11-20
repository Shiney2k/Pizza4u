package com.pizza4u.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.activities.AddPizzaActivity;
import com.pizza4u.activities.CusPizzaListActivity;
import com.pizza4u.adapters.ManagerPizzasRecycleAdapter;
import com.pizza4u.adapters.PizzasRecycleAdapter;
import com.pizza4u.models.PizzaModel;

import java.util.ArrayList;


public class ManagerPizzasFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnNew;
    ArrayList<PizzaModel> pizzaModelArrayList;
    ManagerPizzasRecycleAdapter pizzasRecycleAdapter;
    FirebaseFirestore db =FirebaseFirestore.getInstance();

    public ManagerPizzasFragment() {
        // Required empty public constructor
    }

    public static ManagerPizzasFragment newInstance(String param1, String param2) {
        ManagerPizzasFragment fragment = new ManagerPizzasFragment();
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
        return inflater.inflate(R.layout.fragment_manager_pizzas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_pizzas_manager);
        btnNew=view.findViewById(R.id.btn_add_pizza);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPizzaActivity.class);
                startActivity(intent);
            }
        });

        pizzaModelArrayList=new ArrayList<>();

        db.collection("pizza")
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
                                    pizzasRecycleAdapter=new ManagerPizzasRecycleAdapter(getContext(),pizzaModelArrayList);
                                    pizzasRecycleAdapter.notifyDataSetChanged();

                                }
                                if(!pizzaModelArrayList.isEmpty()){
                                    recyclerView.setAdapter(pizzasRecycleAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }}
                    }

                });

    }
}