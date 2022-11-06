package com.pizza4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pizza4u.models.UserModel;

public class ManagerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
        Log.d("UserData from Manager Home", userModel.getEmail() + " " + userModel.getFname());
    }
}