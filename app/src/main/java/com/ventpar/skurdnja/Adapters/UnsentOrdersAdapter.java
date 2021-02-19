package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUser;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UnsentOrdersAdapter extends RecyclerView.Adapter<UnsentOrdersAdapter.UnsentOrdersAdapterViewHolder> {

    private LayoutInflater mInflater;
    private List<OrderWithUser> mData;
    private OnItemClickListener mListener;

    public UnsentOrdersAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UnsentOrdersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.orders_layout,parent,false);

        return new UnsentOrdersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsentOrdersAdapterViewHolder holder, int position) {

        String name = mData.get(position).user.getUserName();
        String surname = mData.get(position).user.getUserSurname();
        holder.userName.setText(name + " " + surname);
        holder.dateOfOrder.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mData.get(position).order.getDateOfOrder()));
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        else
        {
            return mData.size();
        }
    }

    public class UnsentOrdersAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView dateOfOrder;
        public View mView;

        public UnsentOrdersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            userName = mView.findViewById(R.id.order_user_name);
            dateOfOrder = mView.findViewById(R.id.order_date_of_order);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(mListener != null && pos != RecyclerView.NO_POSITION){
                        mListener.onItemClickListener(mData.get(pos));
                    }
                }
            });
        }
    }

    public void setData(List<OrderWithUser> data){
        mData = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        public void onItemClickListener(OrderWithUser data);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
