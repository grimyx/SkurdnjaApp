package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;

import java.util.List;

public class UserOrdersAdapter extends RecyclerView.Adapter<UserOrdersAdapter.UserOrdersAdapterViewHolder> {


    private List<Orders> mData;
    private LayoutInflater mInflater;

    public UserOrdersAdapter(Context ctx, List<Orders> data){
        mData = data;
        mInflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public UserOrdersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_orders_layout,parent,false);

        return new UserOrdersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserOrdersAdapterViewHolder holder, int position) {
        holder.mOrderId.setText(String.valueOf(mData.get(position).getId()));
        holder.mOrderDate.setText(mData.get(position).getDateOfOrder().toString());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class UserOrdersAdapterViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView mOrderId;
        private TextView mOrderDate;

        public UserOrdersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            mOrderDate = itemView.findViewById(R.id.user_orders_date_of_order);
            mOrderId = itemView.findViewById(R.id.user_orders_id);
        }
    }
}
