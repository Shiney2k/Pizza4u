package com.pizza4u.fragments;

import static java.lang.Double.parseDouble;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.PizzaTypeRecycleAdapter;
import com.pizza4u.models.CartItemModel;
import com.pizza4u.models.PizzaTypeModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;
import java.util.Objects;


public class CusHomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private PizzaTypeRecycleAdapter pizzaTypeRecycleAdapter;
    ArrayList<PizzaTypeModel> pizzaTypeModelArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CusHomeFragment() {

    }

    public CusHomeFragment (ArrayList<PizzaTypeModel> pizzaTypeModelArrayList) {
        this.pizzaTypeModelArrayList=pizzaTypeModelArrayList;
    }

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
        view = inflater.inflate(R.layout.fragment_cus_cart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerPTypes);

        pizzaTypeModelArrayList=new ArrayList<>();

        db.collection("pizza-types")
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
                                    pizzaTypeRecycleAdapter.notifyDataSetChanged();

                                }
                            }}
                    }

                });


        pizzaTypeRecycleAdapter=new PizzaTypeRecycleAdapter(this.getContext(),pizzaTypeModelArrayList);
        if(!pizzaTypeModelArrayList.isEmpty()) {
            recyclerView.setAdapter(pizzaTypeRecycleAdapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

}
