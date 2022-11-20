package com.pizza4u.adapters;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.fragments.CusCartFragment;
import com.pizza4u.models.CartItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartRecycleAdapter extends RecyclerView.Adapter<CartRecycleAdapter.CartViewHolder> {

    private Context mContext;
    public List<CartItemModel> cartItemModelList;
    public int position;
    public TextView txtTot;
    public float tot=0;


    public CartRecycleAdapter(Context mContext, List<CartItemModel> cartItemModelList,TextView txtTot) {
        this.mContext = mContext;
        this.cartItemModelList = cartItemModelList;
        this.txtTot=txtTot;
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
        holder.txtCount.setText(String.valueOf(cartItemModelList.get(position).getCount()));
        holder.initPrice=cartItemModelList.get(position).getSubTotal();
        holder.txtSize.setText(cartItemModelList.get(position).getSize());
        Picasso.get().load(cartItemModelList.get(position).getPhoto_url()).into(holder.image);
        tot+=parseFloat(holder.txtPrice.getText().toString());
        txtTot.setText(String.valueOf(tot));

        cartItemModelList.get(position).setCount(parseInt(holder.txtCount.getText().toString()));
        cartItemModelList.get(position).setSubTotal(parseFloat(holder.txtPrice.getText().toString()));

        holder.cartItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tot+=parseFloat(holder.txtPrice.getText().toString())-cartItemModelList.get(position).getSubTotal();
                txtTot.setText(String.valueOf(tot));
                cartItemModelList.get(position).setSubTotal(parseFloat(holder.txtPrice.getText().toString()));
                cartItemModelList.get(position).setCount(parseInt(holder.txtCount.getText().toString()));
            }
        });
//        holder.btnplus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(holder.txtCount.getText().toString());
//                count++;
//                holder.txtCount.setText(String.valueOf(count));
//                float sub = count*holder.initPrice;
//                holder.txtPrice.setText(Float.toString(sub));
//                tot+=parseFloat(holder.txtPrice.getText().toString())-holder.initPrice;
//                txtTot.setText(String.valueOf(tot));
//            }
//        });
//
//        holder.btnplus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(holder.txtCount.getText().toString());
//                if(count>=2){
//                    count--;
//                    holder.txtCount.setText(String.valueOf(count));
//                    float sub = count*holder.initPrice;
//                    holder.txtPrice.setText(Float.toString(sub));
//                    tot+=parseFloat(holder.txtPrice.getText().toString())-holder.initPrice;
//                    txtTot.setText(String.valueOf(tot));
//                }
//            }
//        });
  }


    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtPrice,txtCount,txtSize;
        public ImageButton btnplus,btnminus;
        Float initPrice;
        ConstraintLayout cartItemLayout;
        ImageView image;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtPizzaNameCart);
            txtPrice=itemView.findViewById(R.id.txtPizzaPriceCart);
            txtCount=itemView.findViewById(R.id.txtPizzaCount);
            btnminus=itemView.findViewById(R.id.btnMinus);
            txtSize=itemView.findViewById(R.id.txtPizzaSize);
            btnplus=itemView.findViewById(R.id.btnPlus);
            cartItemLayout=itemView.findViewById(R.id.cartItemLayout);
            image=itemView.findViewById(R.id.imageView);

            btnplus.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    int count = parseInt(txtCount.getText().toString());
                    count++;
                    txtCount.setText(String.valueOf(count));
                    float sub = count*initPrice;
                    txtPrice.setText(Float.toString(sub));
                }
            });

            btnminus.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    int count = parseInt(txtCount.getText().toString());
                    if(count>=2){
                        count--;
                        txtCount.setText(String.valueOf(count));
                        float sub = count*initPrice;
                        txtPrice.setText(Float.toString(sub));
                    }
                }
            });
        }
    }
}
