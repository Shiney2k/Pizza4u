package com.pizza4u;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PizzasRecycleAdapter extends RecyclerView.Adapter<PizzasRecycleAdapter.PizzasViewHolder>{

    Context mContext;
    private ArrayList name,photo,price,description;
    private int position;

    public PizzasRecycleAdapter(Context mContext, ArrayList name, ArrayList photo,ArrayList price,ArrayList description) {
        this.mContext = mContext;
        this.name = name;
        this.photo= photo;
        this.price=price;
        this.description=description;
    }


    @NonNull
    @Override
    public PizzasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_pizzas,viewGroup,false);
        return new PizzasRecycleAdapter.PizzasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzasViewHolder pholder, @SuppressLint("RecyclerView") int position) {
        this.position =position;

        pholder.txtName.setText(String.valueOf(name.get(position)));
        pholder.txtDescription.setText(String.valueOf(description.get(position)));
        //pholder.img.setImageResource();

        pholder.pLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext,CusPizzaViewActivity.class);
            intent.putExtra("name",String.valueOf(name.get(position)));
            intent.putExtra("description",String.valueOf(description.get(position)));
            intent.putExtra("price",String.valueOf(price.get(position)));
            intent.putExtra("photo",String.valueOf(photo.get(position)));

            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public static class PizzasViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName;
        public TextView txtDescription;
        public ImageView img;
        ConstraintLayout pLayout;

        public PizzasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtPizzaName);
            img = itemView.findViewById(R.id.imagePizza);
            txtDescription = itemView.findViewById(R.id.txtPizzaDescription);
        }
    }
}
