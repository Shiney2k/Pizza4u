package com.pizza4u.adapters;

import static java.lang.Float.parseFloat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.pizza4u.models.PizzaModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ManagerPizzasRecycleAdapter extends RecyclerView.Adapter<ManagerPizzasRecycleAdapter.PizzasViewHolder> {

    Context mContext;
    private List<PizzaModel> pizzaModelList;

    public ManagerPizzasRecycleAdapter(Context mContext, List<PizzaModel> pizzaModelList) {
        this.mContext = mContext;
        this.pizzaModelList = pizzaModelList;
    }

    @NonNull
    @Override
    public PizzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_manager_pizzas,parent,false);
        return new ManagerPizzasRecycleAdapter.PizzasViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PizzasViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtName.setText(pizzaModelList.get(position).getName());
        holder.txtDescription.setText(pizzaModelList.get(position).getDescription());
        holder.txtType.setText(pizzaModelList.get(position).getPizza_type());
        holder.txtPrice.setText(pizzaModelList.get(position).getPrice().toString());
        Picasso.get().load(pizzaModelList.get(position).getPhoto_url()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                CollectionReference dbpt = db.collection("pizza");
                DocumentReference documentReference = dbpt.document(pizzaModelList.get(position).getId());

                pizzaModelList.get(position).setName(holder.txtName.getText().toString());
                pizzaModelList.get(position).setDescription(holder.txtDescription.getText().toString());
                pizzaModelList.get(position).setPizza_type(holder.txtType.getText().toString());
                pizzaModelList.get(position).setPrice(parseFloat(holder.txtPrice.getText().toString()));

                documentReference.set(pizzaModelList.get(position)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        return pizzaModelList.size();
    }

    public static class PizzasViewHolder extends RecyclerView.ViewHolder{

        public EditText txtName,txtDescription,txtPrice,txtType;
        public ImageView img;
        ConstraintLayout pLayout;
        Button btnSave;

        public PizzasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtPizzaNameManager);
            img = itemView.findViewById(R.id.imagePizzaManager);
            txtDescription = itemView.findViewById(R.id.txtPizzaDescriptionManager);
            txtPrice=itemView.findViewById(R.id.txt_Price_manager);
            txtType=itemView.findViewById(R.id.txt_PType_manager);
            btnSave=itemView.findViewById(R.id.btnSave_manager_pizza);
        }
    }

}
