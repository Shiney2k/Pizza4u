package com.pizza4u.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pizza4u.R;
import com.pizza4u.models.UserModel;
import com.squareup.picasso.Picasso;

public class ManagerMainActivity extends AppCompatActivity {
    ImageView imageViewManager;
    Button buttonAddBranch;
    Button buttonAddEmployee;
    Button buttonAddMenu;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        imageViewManager = findViewById(R.id.imageViewManager);
        buttonAddBranch = findViewById(R.id.buttonAddBranch);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonAddMenu = findViewById(R.id.buttonAddMenu);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
        Log.d("UserData from Manager Home", userModel.getEmail() + " " + userModel.getFname());

        Picasso.get().load(userModel.getProfilepic()).into(imageViewManager);

        buttonAddBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMainActivity.this , AddBranchActivity.class);
                startActivity(intent);
            }
        });
    }
}