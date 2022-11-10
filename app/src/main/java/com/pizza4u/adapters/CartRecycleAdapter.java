package com.pizza4u.adapters;

import static java.lang.Float.parseFloat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.models.CartItemModel;

import java.util.List;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartRecycleAdapter.CartViewHolder> {

    private Context mContext;
    public List<CartItemModel> cartItemModelList;
    public int position;


    public CartRecycleAdapter(Context mContext, List<CartItemModel> cartItemModelList) {
        this.mContext = mContext;
        this.cartItemModelList = cartItemModelList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_cus_cart_item,parent,false);
        return new CartRecycleAdapter.CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;
        holder.txtName.setText(cartItemModelList.get(position).getPizzaName());
        holder.txtPrice.setText(cartItemModelList.get(position).getSubTotal().toString());
        holder.txtCount.setText(cartItemModelList.get(position).getCount());

        cartItemModelList.get(position).setCount(Integer.parseInt(holder.txtCount.getText().toString()));
        cartItemModelList.get(position).setSubTotal(parseFloat(holder.txtPrice.getText().toString()));

    }


    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtPrice,txtCount;
        public ImageButton btnplus,btnminus;
        ConstraintLayout cartItemLayout;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtPizzaNameCart);
            txtPrice=itemView.findViewById(R.id.txtPizzaPriceCart);
            txtCount=itemView.findViewById(R.id.txtPizzaCount);
            btnminus=itemView.findViewById(R.id.btnMinus);
            btnplus=itemView.findViewById(R.id.btnPlus);
            cartItemLayout=itemView.findViewById(R.id.cartItemLayout);

            btnplus.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(txtCount.getText().toString());
                    count++;
                    txtCount.setText(count);
                    float sub = count*parseFloat(txtPrice.getText().toString());
                    txtPrice.setText(Float.toString(sub));
                }
            });

            btnminus.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(txtCount.getText().toString());
                    if(count!=0){
                        count--;
                        txtCount.setText(count);
                        float sub = count*parseFloat(txtPrice.getText().toString());
                        txtPrice.setText(Float.toString(sub));
                    }
                }
            });
        }
    }
}
