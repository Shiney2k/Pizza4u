package com.pizza4u.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.activities.EmployeeListActivity;
import com.pizza4u.models.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeeRecycleAdapter extends RecyclerView.Adapter<EmployeeRecycleAdapter.EmployeeViewHolder> {
    Context mContext;
    private final List<UserModel> userModelList;

    public EmployeeRecycleAdapter(EmployeeListActivity mContext,List<UserModel> userModelList) {
        this.mContext=mContext;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_employee,viewGroup,false);
        return new EmployeeRecycleAdapter.EmployeeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {

        holder.txtName.setText(userModelList.get(position).getFname()+" "+userModelList.get(position).getLname());
        holder.txtBranch.setText(String.valueOf(userModelList.get(position).getBranchid()));
        holder.txtMail.setText(userModelList.get(position).getEmail());
        holder.txtPhone.setText(String.valueOf(userModelList.get(position).getPhone()));
        Picasso.get().load(userModelList.get(position).getProfilepic()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName;
        public TextView txtBranch,txtMail,txtPhone;
        ImageView img;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtEname);
            txtBranch=itemView.findViewById(R.id.txtbranchIDemp);
            txtMail=itemView.findViewById(R.id.txtEmailEmp);
            txtPhone=itemView.findViewById(R.id.txtPhoneeEmp);
            img=itemView.findViewById(R.id.imgEmp);
        }
    }
}
