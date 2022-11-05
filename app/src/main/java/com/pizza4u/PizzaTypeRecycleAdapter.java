package com.pizza4u;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class PizzaTypeRecycleAdapter extends RecyclerView.Adapter<PizzaTypeRecycleAdapter.PTypesViewHolder> {

    private Context mContext;
    private ArrayList ptype,photo;
    private int position;

    public PizzaTypeRecycleAdapter(Context mContext,ArrayList ptype,ArrayList photo) {
        this.mContext = mContext;
        this.ptype = ptype;
        this.photo = photo;
    }


    @NonNull
    @Override
    public PTypesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_pizza_types,viewGroup,false);
        return new PTypesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaTypeRecycleAdapter.PTypesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;

        holder.txtPtype.setText(String.valueOf(ptype.get(position)));

        holder.ptypesLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext,CusPizzaListActivity.class);
            intent.putExtra("ptype",String.valueOf(ptype.get(position)));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ptype.size();
    }

    public static class PTypesViewHolder extends RecyclerView.ViewHolder{

        TextView txtPtype;
        ImageView image;
        ConstraintLayout ptypesLayout;

        public PTypesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPtype = itemView.findViewById(R.id.txtPizzaType);
            image = itemView.findViewById(R.id.imagePizzaType);
        }

    }

}
