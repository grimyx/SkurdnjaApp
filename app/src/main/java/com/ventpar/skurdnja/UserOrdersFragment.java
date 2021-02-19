package com.ventpar.skurdnja;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventpar.skurdnja.Adapters.UserOrdersAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaViewModels.OrderViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserOrdersFragment extends Fragment {

    private User mUser;
    private OrderViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private UserOrdersAdapter mAdapter;
    private Context mContext;

    public UserOrdersFragment() {
        // Required empty public constructor
    }

    public UserOrdersFragment(User user){
        mUser = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        mRecyclerView = view.findViewById(R.id.user_order_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mViewModel.getOrdersByUserId(mUser.getId()).observe(this, new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> orders) {
                mAdapter = new UserOrdersAdapter(mContext,orders);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
