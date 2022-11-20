package com.pizza4u.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizza4u.R;
import com.pizza4u.models.UserModel;
import com.squareup.picasso.Picasso;

public class ManagerMainActivity extends AppCompatActivity {
    ImageView imageViewManager;
    Button buttonAddBranch;
    Button buttonAddEmployee;
    Button buttonAddMenu;
    TextView txtManagerName;
    


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        imageViewManager = findViewById(R.id.imageViewManager);
        buttonAddBranch = findViewById(R.id.buttonAddBranch);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonAddMenu = findViewById(R.id.buttonAddMenu);
        txtManagerName = findViewById(R.id.textManager_name);

        if (getIntent().hasExtra("userData")) {
            UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
            Log.d("UserData from Manager Home", userModel.getEmail() + " " + userModel.getFname());


        Picasso.get().load(userModel.getProfilepic()).into(imageViewManager);
        txtManagerName.setText(userModel.getFname()+" "+userModel.getLname());

      

        buttonAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMainActivity.this , BranchListActivity.class);
                startActivity(intent);
            }
        });

        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMainActivity.this , EmployeeListActivity.class);
                startActivity(intent);
            }
        });

        buttonAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMainActivity.this , ManagerMenuActivity.class);
                startActivity(intent);
            }
        });
        }
    }
}