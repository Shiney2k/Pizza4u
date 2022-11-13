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

public class EmployeeMainActivity extends AppCompatActivity {

    ImageView imageViewEmp;
    Button buttonCheckOrders;
    TextView txtEmpName;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);


        imageViewEmp = findViewById(R.id.imageViewEmp);
        buttonCheckOrders = findViewById(R.id.buttonCheckOrders);
        txtEmpName=findViewById(R.id.textEmp_name);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userData");
        Log.d("UserData from Manager Home", userModel.getEmail() + " " + userModel.getFname());

        Picasso.get().load(userModel.getProfilepic()).into(imageViewEmp);
        txtEmpName.setText(userModel.getFname()+" "+userModel.getLname());

        buttonCheckOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeMainActivity.this , CheckOrdersActivity.class);
                startActivity(intent);
            }
        });

    }
}