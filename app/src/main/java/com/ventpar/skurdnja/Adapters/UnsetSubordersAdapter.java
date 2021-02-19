package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborder;

import java.util.List;

public class UnsetSubordersAdapter extends RecyclerView.Adapter<UnsetSubordersAdapter.UnsetSubordersViewHolder> {

    private List<ProductFromSuborder> mData;
    private LayoutInflater mInflater;

    public UnsetSubordersAdapter(Context ctx, List<ProductFromSuborder> data) {
        mInflater = LayoutInflater.from(ctx);
        mData = data;
    }


    @NonNull
    @Override
    public UnsetSubordersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_unsent_products,parent,false);
        return new UnsetSubordersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsetSubordersViewHolder holder, int position) {
        holder.productName.setText(mData.get(position).product.getName());
        holder.quantity.setText("Kolicina : " + Integer.toString(mData.get(position).suborder.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class UnsetSubordersViewHolder extends RecyclerView.ViewHolder{

        public View mView;
        public TextView productName;
        public TextView quantity;

        public UnsetSubordersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            productName = itemView.findViewById(R.id.unsent_product_name);
            quantity = itemView.findViewById(R.id.unsent_details_quantity);
        }
    }
}
