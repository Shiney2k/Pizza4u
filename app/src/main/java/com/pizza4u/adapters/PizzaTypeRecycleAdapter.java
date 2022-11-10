package com.pizza4u.adapters;

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

import com.pizza4u.activities.CusPizzaListActivity;
import com.pizza4u.R;
import com.pizza4u.models.PizzaTypeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PizzaTypeRecycleAdapter extends RecyclerView.Adapter<PizzaTypeRecycleAdapter.PTypesViewHolder> {

    private Context mContext;
    public List<PizzaTypeModel> pizzaTypeModelList;
    private int position;

    public PizzaTypeRecycleAdapter(Context mContext,List<PizzaTypeModel> pizzaTypeModels) {
        this.mContext = mContext;
        this.pizzaTypeModelList=pizzaTypeModels;
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

        holder.txtPtype.setText(pizzaTypeModelList.get(position).getTypeName());
        Picasso.get().load(pizzaTypeModelList.get(position).getPhoto_url()).into(holder.image);

        holder.ptypesLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, CusPizzaListActivity.class);
            intent.putExtra("ptypeID",pizzaTypeModelList.get(position).getTypeID());
            intent.putExtra("ptypeName",pizzaTypeModelList.get(position).getTypeName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzaTypeModelList.size();
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
