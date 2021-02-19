package com.ventpar.skurdnja.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventpar.skurdnja.R;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.AllUsersAdapterViewHolder> {

    private LayoutInflater mInflater;
    private List<User> mData;

    private OnItemClickListener mListener;


    public AllUsersAdapter(Context ctx) {
        mInflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public AllUsersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_all_users,parent,false);
        return new AllUsersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUsersAdapterViewHolder holder, int position) {
        String name = mData.get(position).getUserName();
        String surname = mData.get(position).getUserSurname();

        holder.mUserName.setText(name + " " + surname);
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

    private User getUser(int pos) { return mData.get(pos);}

    class AllUsersAdapterViewHolder extends RecyclerView.ViewHolder{

        private View mView;
        public TextView mUserName;

        public AllUsersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mUserName = itemView.findViewById(R.id.recycler_view_user_name);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(getUser(position));
                    }
                }
            });
        }
    }

    public void setData(List<User> users){
        mData = users;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
