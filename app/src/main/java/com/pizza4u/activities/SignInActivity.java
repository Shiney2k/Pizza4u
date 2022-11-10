package com.pizza4u.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pizza4u.MainActivity;
import com.pizza4u.R;
import com.pizza4u.models.UserModel;

public class SignInActivity extends AppCompatActivity {

    Button buttonSignIn;
    Button buttonCancelSignIn;
    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        buttonSignIn = findViewById(R.id.buttonSignIn2);
        buttonCancelSignIn = findViewById(R.id.buttonCancelSignIn);
        editTextEmail = findViewById(R.id.editTextEmailSignIn);
        editTextPassword = findViewById(R.id.editTextPasswordSignIn);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users")
                        .whereEqualTo("email", editTextEmail.getText().toString().trim())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if(!task.getResult().isEmpty()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                            // Log.d(TAG, document.getId() + " => " + document.getData());
                                            // String email = document.get("email").toString();
                                            // Log.d("Email", email);

                                        UserModel userModel = document.toObject(UserModel.class);

                                            if(userModel.getPassword().equals(editTextPassword.getText().toString().trim())) {
                                                editTextEmail.setText("");
                                                editTextPassword.setText("");
                                                switch (userModel.getAcctype()) {
                                                    case "Customer":
                                                        Intent intent1 = new Intent(SignInActivity.this, CustomerMainActivity.class);
                                                        intent1.putExtra("userData", userModel);
                                                        startActivity(intent1);
                                                        break;
                                                    case "Manager":
                                                        Intent intent2 = new Intent(SignInActivity.this, ManagerMainActivity.class);
                                                        intent2.putExtra("userData", userModel);
                                                        startActivity(intent2);
                                                        break;
                                                    case "Employee":
                                                        Intent intent3 = new Intent(SignInActivity.this, EmployeeMainActivity.class);
                                                        intent3.putExtra("userData", userModel);
                                                        startActivity(intent3);
                                                        break;
                                                }
                                            } else {
                                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        switch (which){
                                                            case DialogInterface.BUTTON_POSITIVE:
                                                                //Yes button clicked
                                                                break;
                                                        }
                                                    }
                                                };

                                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                                builder.setMessage("Please enter correct password.").setPositiveButton("Ok", dialogClickListener)
                                                        .show();
                                            }
                                    }} else {
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        //Yes button clicked
                                                        break;
                                                }
                                            }
                                        };

                                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                        builder.setMessage("Please enter valid email.").setPositiveButton("Ok", dialogClickListener)
                                                .show();
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });

        buttonCancelSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to cancel?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }
}