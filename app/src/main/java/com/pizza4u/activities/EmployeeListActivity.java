package com.pizza4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.R;
import com.pizza4u.adapters.BranchRecyclerAdapter;
import com.pizza4u.adapters.EmployeeRecycleAdapter;
import com.pizza4u.models.BranchModel;
import com.pizza4u.models.UserModel;

import java.util.ArrayList;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    ArrayList<UserModel> userModelArrayList;
    EmployeeRecycleAdapter employeeRecycleAdapter;
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    Button btnAddemp;
    ImageButton btnSearch;
    EditText txtBid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        recyclerView =findViewById(R.id.recyclerview_employees);
        btnAddemp=findViewById(R.id.btn_add_emp);
        txtBid=findViewById(R.id.txtBid_emp);
        btnSearch=findViewById(R.id.btnSearch);

        userModelArrayList=new ArrayList<>();

        db.collection("users")
                .whereEqualTo("acctype","Employee")
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

                                    UserModel userModel = document.toObject(UserModel.class);
                                    userModelArrayList.add(userModel);
                                    employeeRecycleAdapter=new EmployeeRecycleAdapter(EmployeeListActivity.this,userModelArrayList);
                                    employeeRecycleAdapter.notifyDataSetChanged();
                                }
                                if(!userModelArrayList.isEmpty()){
                                    recyclerView.setAdapter(employeeRecycleAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeListActivity.this));
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }}
                    }

                });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userModelArrayList=new ArrayList<>();
                db.collection("users")
                        .whereEqualTo("acctype","Employee")
                        .whereEqualTo("branchid",txtBid.getText().toString())
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

                                            UserModel userModel = document.toObject(UserModel.class);
                                            userModelArrayList.add(userModel);
                                            employeeRecycleAdapter=new EmployeeRecycleAdapter(EmployeeListActivity.this,userModelArrayList);
                                            employeeRecycleAdapter.notifyDataSetChanged();
                                        }
                                        if(!userModelArrayList.isEmpty()){
                                            recyclerView.setAdapter(employeeRecycleAdapter);
                                            recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeListActivity.this));
                                        }else {
                                            recyclerView.setVisibility(View.GONE);
                                        }
                                    }}
                            }

                        });

            }
        });

        btnAddemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeListActivity.this , AddEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }
}