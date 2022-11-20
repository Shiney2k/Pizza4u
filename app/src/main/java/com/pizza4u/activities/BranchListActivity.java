package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.BranchRecyclerAdapter;
import com.pizza4u.adapters.PizzasRecycleAdapter;
import com.pizza4u.models.BranchModel;
import com.pizza4u.models.PizzaModel;

import java.util.ArrayList;

public class BranchListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<BranchModel> branchModelArrayList;
    BranchRecyclerAdapter branchRecycleAdapter;
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    Button btnAddBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);

        recyclerView = findViewById(R.id.recycler_branches);
        btnAddBranch = findViewById(R.id.btn_add_branch);


        branchModelArrayList=new ArrayList<>();

        db.collection("branch")
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

                                    BranchModel branchModel = document.toObject(BranchModel.class);
                                    branchModelArrayList.add(branchModel);
                                    branchRecycleAdapter=new BranchRecyclerAdapter(BranchListActivity.this,branchModelArrayList);
                                    branchRecycleAdapter.notifyDataSetChanged();
                                }
                                if(!branchModelArrayList.isEmpty()){
                                    recyclerView.setAdapter(branchRecycleAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(BranchListActivity.this));
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }}
                    }

                });

        btnAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BranchListActivity.this , AddBranchActivity.class);
                startActivity(intent);
            }
        });

    }
}