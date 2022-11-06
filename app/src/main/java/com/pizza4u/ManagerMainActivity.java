package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pizza4u.models.UserModel;

public class ManagerMainActivity extends AppCompatActivity {

    ImageView imageViewManager = findViewById(R.id.imageViewManager);
    Button buttonAddBranch = findViewById(R.id.buttonAddBranch);
    Button buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
    Button buttonAddMenu = findViewById(R.id.buttonAddMenu);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
        Log.d("UserData from Manager Home", userModel.getEmail() + " " + userModel.getFname());

        buttonAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMainActivity.this ,AddBranchActivity.class);
                startActivity(intent);
            }
        });
    }
}