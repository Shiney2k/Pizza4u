package com.pizza4u.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pizza4u.R;
import com.pizza4u.models.UserModel;

import java.io.ByteArrayOutputStream;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText editTextFirstNameEmp;
    EditText editTextLastNameEmp;
    EditText editTextEmailEmp;
    EditText editTextPhoneEmp;
    EditText editTextPasswordEmp;
    EditText editTextBranchIdEmp;
    EditText editTextEmployeeIdEmp;
    Button buttonAddProfilePictureEmp;
    Button buttonCreateAccountEmp;
    Button buttonCancelEmp;
    ImageView profilepicEmp;

    Bitmap image;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        editTextFirstNameEmp = findViewById(R.id.editTextFirstNameEmp);
        editTextLastNameEmp = findViewById(R.id.editTextLastNameEmp);
        editTextEmailEmp = findViewById(R.id.editTextEmailEmp);
        editTextPhoneEmp = findViewById(R.id.editTextPhoneEmp);
        editTextPasswordEmp = findViewById(R.id.editTextPasswordEmp);
        editTextBranchIdEmp = findViewById(R.id.editTextBranchIdEmp);
        editTextEmployeeIdEmp = findViewById(R.id.editTextEmployeeIdEmp);
        buttonAddProfilePictureEmp = findViewById(R.id.buttonAddProfilePictureEmp);
        buttonCreateAccountEmp = findViewById(R.id.buttonCreateAccountEmp);
        buttonCancelEmp = findViewById(R.id.buttonCancelEmp);
        profilepicEmp = findViewById(R.id.profilepicEmp);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();


        buttonCancelEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Intent intent = new Intent(AddEmployeeActivity.this , ManagerMainActivity.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to cancel?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        buttonAddProfilePictureEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto , 1);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(takePicture, 0);
                                break;

                            case DialogInterface.BUTTON_NEUTRAL:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Select image from gallery or capture from camera?").setPositiveButton("Gallery", dialogClickListener)
                        .setNegativeButton("Camera", dialogClickListener).setNeutralButton("Cancel",dialogClickListener).show();
            }
        });

        buttonCreateAccountEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image != null || selectedImage != null) {
                    StorageReference storageRef = storage.getReference();
                    StorageReference profilepicRef = storageRef.child(editTextEmailEmp.getText().toString().trim() + "/profilepic.jpg");

                    if(image != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageData = baos.toByteArray();

                        UploadTask uploadTask = profilepicRef.putBytes(imageData);
                        Task<Uri> urlTask = uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                long size = taskSnapshot.getMetadata().getSizeBytes();
                                Log.d(TAG, "Image uploaded to Firebase Storage: " + size);
                            }
                        }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }

                                return profilepicRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    Log.d("Profile picture download uri: ", downloadUri.toString());

                                    addEmployee(db,view,downloadUri.toString());

//                                    Map<String, Object> data = new HashMap<>();
//                                    data.put("acctype", "Manager");
//                                    data.put("fname", editTextFirstNameManager.getText().toString().trim());
//                                    data.put("lname", editTextLastNameManager.getText().toString().trim());
//                                    data.put("email", editTextEmailManager.getText().toString().trim());
//                                    data.put("phone", Integer.parseInt(editTextPhoneManager.getText().toString().trim()));
//                                    data.put("password", editTextPasswordManager.getText().toString().trim());
//                                    data.put("branchid", Integer.parseInt(editTextBranchIdManager.getText().toString().trim()));
//                                    data.put("employeeid", Integer.parseInt(editTextEmployeeIdManager.getText().toString().trim()));
//                                    data.put("profilepic", downloadUri.toString());
//
//                                    db.collection("users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    switch (which){
//                                                        case DialogInterface.BUTTON_POSITIVE:
//                                                            //Yes button clicked
//                                                            Intent intent = new Intent(getContext(),MainActivity.class);
//                                                            startActivity(intent);
//                                                            break;
//                                                    }
//                                                }
//                                            };
//
//                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                                            builder.setMessage("Account created successfully.").setPositiveButton("Ok", dialogClickListener)
//                                                    .show();
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w(TAG, "Error adding document", e);
//                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    switch (which){
//                                                        case DialogInterface.BUTTON_POSITIVE:
//                                                            break;
//                                                    }
//                                                }
//                                            };
//
//                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                                            builder.setTitle("Failed to create account.").setMessage("Error: " + String.valueOf(e)).setPositiveButton("Ok", dialogClickListener)
//                                                    .show();
//                                        }
//                                    });
                                } else {
                                    Log.d(TAG, "Failed to get image download URL");
                                }
                            }
                        });
                    } else if(selectedImage != null) {
                        UploadTask uploadTask = profilepicRef.putFile(selectedImage);
                        Task<Uri> urlTask = uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                long size = taskSnapshot.getMetadata().getSizeBytes();
                                Log.d(TAG, "Image uploaded to Firebase Storage: " + size);
                            }
                        }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }

                                return profilepicRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    Log.d("Profile picture download uri: ", downloadUri.toString());

                                    addEmployee(db,view,downloadUri.toString());

//                                    Map<String, Object> data = new HashMap<>();
//                                    data.put("acctype", "Manager");
//                                    data.put("fname", editTextFirstNameManager.getText().toString().trim());
//                                    data.put("lname", editTextLastNameManager.getText().toString().trim());
//                                    data.put("email", editTextEmailManager.getText().toString().trim());
//                                    data.put("phone", Integer.parseInt(editTextPhoneManager.getText().toString().trim()));
//                                    data.put("password", editTextPasswordManager.getText().toString().trim());
//                                    data.put("branchid", Integer.parseInt(editTextBranchIdManager.getText().toString().trim()));
//                                    data.put("employeeid", Integer.parseInt(editTextEmployeeIdManager.getText().toString().trim()));
//                                    data.put("profilepic", downloadUri.toString());
//
//                                    db.collection("users").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    switch (which){
//                                                        case DialogInterface.BUTTON_POSITIVE:
//                                                            //Yes button clicked
//                                                            Intent intent = new Intent(getContext(),MainActivity.class);
//                                                            startActivity(intent);
//                                                            break;
//                                                    }
//                                                }
//                                            };
//
//                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                                            builder.setMessage("Account created successfully.").setPositiveButton("Ok", dialogClickListener)
//                                                    .show();
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w(TAG, "Error adding document", e);
//                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    switch (which){
//                                                        case DialogInterface.BUTTON_POSITIVE:
//                                                            break;
//                                                    }
//                                                }
//                                            };
//
//                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                                            builder.setTitle("Failed to create account.").setMessage("Error: " + String.valueOf(e)).setPositiveButton("Ok", dialogClickListener)
//                                                    .show();
//                                        }
//                                    });
                                } else {
                                    Log.d(TAG, "Failed to get image download URL");
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
//                    selectedImage = imageReturnedIntent.getData();
//                    profilepicManager.setImageURI(selectedImage);

                    image = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    profilepicEmp.setImageBitmap(image);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    profilepicEmp.setImageURI(selectedImage);

//                    image = (Bitmap) imageReturnedIntent.getExtras().get("data");
//                    profilepicManager.setImageBitmap(image);
                }
                break;
        }
    }

    public void addEmployee(FirebaseFirestore db, View view, String downloadUri){
        CollectionReference dbUsers = db.collection("users");
        DocumentReference documentReference = dbUsers.document();

        UserModel userModel = new UserModel(
                "Employee",
                editTextFirstNameEmp.getText().toString().trim(),
                editTextLastNameEmp.getText().toString().trim(),
                editTextEmailEmp.getText().toString().trim(),
                Integer.parseInt(editTextPhoneEmp.getText().toString().trim()),
                editTextPasswordEmp.getText().toString().trim(),
                Integer.parseInt(editTextBranchIdEmp.getText().toString().trim()),
                Integer.parseInt(editTextEmployeeIdEmp.getText().toString().trim()),
                downloadUri,
                documentReference.getId()
        );

        documentReference.set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Account created successfully.").setPositiveButton("Ok", dialogClickListener)
                        .show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Failed to create account.").setMessage("Error: " + String.valueOf(e)).setPositiveButton("Ok", dialogClickListener)
                        .show();
            }
        });

    }
}