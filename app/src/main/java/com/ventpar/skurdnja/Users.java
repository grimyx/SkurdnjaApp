package com.ventpar.skurdnja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ventpar.skurdnja.Adapters.AllUsersAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaViewModels.UserViewModel;

import java.util.List;

public class Users extends AppCompatActivity {

    private RecyclerView mAllUsersRecyclerView;
    private UserViewModel mViewModel;
    private AllUsersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mAdapter = new AllUsersAdapter(this);
        mAdapter.setOnItemClickListener(new AllUsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(Users.this, UserDetailsActivity.class);
                intent.putExtra("USER",user);
                startActivity(intent);
            }
        });

        mAllUsersRecyclerView = findViewById(R.id.all_users_recycler_viewer);
        mAllUsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAllUsersRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mAllUsersRecyclerView.addItemDecoration(dividerItemDecoration);

        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mAdapter.setData(users);
                Log.v("size", Integer.toString(users.size()));
            }
        });
    }
}
