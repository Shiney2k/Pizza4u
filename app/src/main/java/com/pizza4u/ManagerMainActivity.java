package com.pizza4u;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pizza4u.models.UserModel;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

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
                Intent intent = new Intent(ManagerMainActivity.this ,AddBranchActivity.class);
                startActivity(intent);
            }
        });
    }
}