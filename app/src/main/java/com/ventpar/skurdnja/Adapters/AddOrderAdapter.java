package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.TempSuborder;

import java.util.ArrayList;

public class AddOrderAdapter extends RecyclerView.Adapter<AddOrderAdapter.AddOrderAdapterViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<TempSuborder> mData;

    public AddOrderAdapter(Context ctx){
        this.mInflater = LayoutInflater.from(ctx);
    }
    @NonNull
    @Override
    public AddOrderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_add_order,parent,false);
        return new AddOrderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddOrderAdapterViewHolder holder, int position) {
        holder.mProductName.setText(mData.get(position).getProduct().getName());
        holder.mQuantity.setText(Integer.toString(mData.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (mData == null)
        {
            return 0;
        }
        else {
            return mData.size();
        }
    }

    class AddOrderAdapterViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        public TextView mProductName;
        public TextView mQuantity;

        public AddOrderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mProductName = itemView.findViewById(R.id.add_order_product_name);
            mQuantity = itemView.findViewById(R.id.add_order_quantity);
        }
    }

    public void setData(ArrayList<TempSuborder> data){
        mData = data;
        notifyDataSetChanged();
    }
}
