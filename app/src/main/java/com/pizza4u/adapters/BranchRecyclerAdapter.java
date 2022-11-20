package com.pizza4u.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pizza4u.R;
import com.pizza4u.activities.BranchListActivity;
import com.pizza4u.models.BranchModel;

import java.util.List;

public class BranchRecyclerAdapter extends RecyclerView.Adapter<BranchRecyclerAdapter.BranchViewHolder> {

    Context mContext;
    private final List<BranchModel> branchModelList;

    public BranchRecyclerAdapter(BranchListActivity mContext,List<BranchModel> branchModelList) {
        this.branchModelList = branchModelList;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_branch,viewGroup,false);
        return new BranchRecyclerAdapter.BranchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder holder, int position) {
        holder.txtBname.setText(branchModelList.get(position).getBranchname());
        holder.txtBid.setText(branchModelList.get(position).getBranchid());
        holder.txtlocation.setText(branchModelList.get(position).getLocationname());

    }

    @Override
    public int getItemCount() {
        return branchModelList.size();
    }

    public static class BranchViewHolder extends RecyclerView.ViewHolder{
        public TextView txtBid,txtBname,txtlocation;
        public Button btnLocation;


        public BranchViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBid=itemView.findViewById(R.id.txtbranchID);
            txtBname=itemView.findViewById(R.id.txtbranchName);
            txtlocation=itemView.findViewById(R.id.txtBlocation);
            btnLocation=itemView.findViewById(R.id.btn_add_branch);
        }
    }
}
