package com.pizza4u.activities;

import static android.content.ContentValues.TAG;
import static com.pizza4u.R.id.txtPizzaTypeNameManagerAdd;

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
import android.widget.ImageButton;
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
import com.pizza4u.models.PizzaTypeModel;
import com.pizza4u.models.UserModel;

import java.io.ByteArrayOutputStream;

public class AddPizzaTypeActivity extends AppCompatActivity {

    EditText txtPTname;
    ImageView imgPT;
    Button btnSave;
    ImageButton btnAddPic;
    Bitmap image;
    Uri selectedImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pizza_type);

        imgPT=findViewById(R.id.imagePizzaTypeManagerAdd);
        txtPTname=findViewById(txtPizzaTypeNameManagerAdd);
        btnSave=findViewById(R.id.btnSave_manager_pizzaTypeAdd);
        btnAddPic=findViewById(R.id.btnAddPic_manager_pizzaTypeAdd);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();


        btnAddPic.setOnClickListener(new View.OnClickListener() {
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image != null || selectedImage != null) {
                    StorageReference storageRef = storage.getReference();
                    StorageReference profilepicRef = storageRef.child(txtPTname.getText().toString().trim() + "/profilepic.jpg");

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

                                    addPizzaType(db,view,downloadUri.toString());
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

                                    addPizzaType(db,view,downloadUri.toString());
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
                    imgPT.setImageBitmap(image);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imgPT.setImageURI(selectedImage);

//                    image = (Bitmap) imageReturnedIntent.getExtras().get("data");
//                    profilepicManager.setImageBitmap(image);
                }
                break;
        }
    }

    public void addPizzaType(FirebaseFirestore db, View view, String downloadUri){
        CollectionReference dbpt = db.collection("pizza-type");
        DocumentReference documentReference = dbpt.document();

        PizzaTypeModel pizzaTypeModel = new PizzaTypeModel(
                documentReference.getId(),
                txtPTname.getText().toString().trim(),
                downloadUri
        );

        documentReference.set(pizzaTypeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                builder.setMessage("Pizza type created successfully.").setPositiveButton("Ok", dialogClickListener)
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
                builder.setTitle("Failed to create Pizza type.").setMessage("Error: " + String.valueOf(e)).setPositiveButton("Ok", dialogClickListener)
                        .show();
            }
        });

    }


}