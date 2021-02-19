package com.ventpar.skurdnja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ventpar.skurdnja.Adapters.UnsentOrdersAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUser;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaViewModels.OrderViewModel;

import java.util.List;

public class MainFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private OrderViewModel mViewModel;
    private UnsentOrdersAdapter mAdapter;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity().getApplicationContext();

        mFloatingActionButton = getView().findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFragment.this.getActivity(), AddOrderActivity.class);
                startActivity(intent);
            }
        });

        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        //DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);

        mRecyclerView = getView().findViewById(R.id.undelivered_orders_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
        //mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new UnsentOrdersAdapter(mContext);
        mAdapter.setOnItemClickListener(new UnsentOrdersAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(OrderWithUser data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), UnsentOrderDetailsActivity.class);
                intent.putExtra("ORDER", data);
                startActivity(intent);
            }
        });

        mViewModel.getOrdersWithUsers().observe(this, new Observer<List<OrderWithUser>>() {
            @Override
            public void onChanged(List<OrderWithUser> orderWithUsers) {
                mAdapter.setData(orderWithUsers);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }
}
