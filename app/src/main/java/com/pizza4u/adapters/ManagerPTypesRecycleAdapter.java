package com.pizza4u.adapters;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pizza4u.R;
import com.pizza4u.activities.CusPizzaListActivity;
import com.pizza4u.models.PizzaTypeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ManagerPTypesRecycleAdapter extends RecyclerView.Adapter<ManagerPTypesRecycleAdapter.PTypesViewHolder>{

    private Context mContext;
    public List<PizzaTypeModel> pizzaTypeModelList;
    private int position;

    public ManagerPTypesRecycleAdapter(Context mContext, List<PizzaTypeModel> pizzaTypeModelList) {
        this.mContext = mContext;
        this.pizzaTypeModelList = pizzaTypeModelList;
    }

    @NonNull
    @Override
    public PTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_pizza_types,parent,false);
        return new ManagerPTypesRecycleAdapter.PTypesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PTypesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;

        holder.txtPtype.setText(pizzaTypeModelList.get(position).getTypeName());
        Picasso.get().load(pizzaTypeModelList.get(position).getPhoto_url()).into(holder.img);


        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                CollectionReference dbpt = db.collection("pizza-type");
                DocumentReference documentReference = dbpt.document(pizzaTypeModelList.get(position).getTypeID());

                    pizzaTypeModelList.get(position).setTypeName(holder.txtPtype.getText().toString());

                    documentReference.set(pizzaTypeModelList.get(position)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
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
        });

    }

    @Override
    public int getItemCount() {
        return pizzaTypeModelList.size();
    }

    public static class PTypesViewHolder extends RecyclerView.ViewHolder{

        EditText txtPtype;
        ImageView img;
        Button btnSave;

        public PTypesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPtype = itemView.findViewById(R.id.txtPTname);
            img = itemView.findViewById(R.id.imagePType);
            btnSave = itemView.findViewById(R.id.btnSavePtypeM);

        }

    }


}
