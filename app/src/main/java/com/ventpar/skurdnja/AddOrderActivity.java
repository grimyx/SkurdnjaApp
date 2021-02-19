package com.ventpar.skurdnja;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ventpar.skurdnja.Adapters.AddOrderAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Suborder;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaViewModels.AddProductViewModel;
import com.ventpar.skurdnja.SkurdnjaViewModels.OrderViewModel;
import com.ventpar.skurdnja.SkurdnjaViewModels.UserViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddOrderActivity extends AppCompatActivity implements
    SelectProductDialog.ProductSelectDialogInterface {

    private ImageButton mAddUserBtn;
    private FloatingActionButton mSelectProductBtn;
    private RecyclerView mSubordersRecyclerView;
    private AutoCompleteTextView mUser;
    private AddProductViewModel mViewModel;
    private AddOrderAdapter mAdapter;
    private UserViewModel mUserViewModel;
    private OrderViewModel mOrderViewModel;
    private User mTempUser;

    private final int ADD_USER = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Toolbar toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mOrderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        mAddUserBtn = findViewById(R.id.add_buyer);
        mAddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddOrderActivity.this, AddUser.class);
                startActivityForResult(intent,ADD_USER);
            }
        });


        mSelectProductBtn = findViewById(R.id.select_product_btn);
        mSelectProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df = new SelectProductDialog();
                df.show(getSupportFragmentManager(), "aa");
            }
        });

        mAdapter = new AddOrderAdapter(this);

        mSubordersRecyclerView = findViewById(R.id.add_order_recycler_view);
        mSubordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSubordersRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mSubordersRecyclerView.addItemDecoration(dividerItemDecoration);

        mUser = findViewById(R.id.buyer_order_search);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                ArrayAdapter<User> adapter = new ArrayAdapter<User>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,users);
                //adapter.setNotifyOnChange(true);
                mUser.setAdapter(adapter);

            }
        });

        mUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTempUser = (User) parent.getItemAtPosition(position);
            }
        });
        }

    @Override
    public void onDialogPositiveClick(int quantity, Product product) {
        mViewModel.addTempSuborder(new TempSuborder(quantity, product));
        mAdapter.setData(mViewModel.getTempSuborders());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_product){
            if (mTempUser == null){
                mUser.setError("Odaberite ime kupca !");
                mUser.requestFocus();
            }
            else if (mViewModel.getTempSuborders().isEmpty()){
                Toast.makeText(this,"Porudzbina je prazna !!!",Toast.LENGTH_LONG).show();
            }
            else {
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY, HH:mm:ss");
                Orders orders = new Orders(mTempUser.getId(),
                        Calendar.getInstance().getTime(),
                        0);
                long tmp = mOrderViewModel.insertOrder(orders);

                ArrayList<Suborder> tempSuborders = new ArrayList<Suborder>();

                for (TempSuborder t : mViewModel.getTempSuborders()){
                    /*mOrderViewModel.insertSuborder(new Suborder(
                            t.getProduct().getId(),
                            tmp,
                            t.getQuantity()
                    ));*/
                    Suborder sub = new Suborder(t.getProduct().getId(),tmp,t.getQuantity());
                    tempSuborders.add(sub);
                }

                mOrderViewModel.insertSuborders(tempSuborders.toArray(new Suborder[tempSuborders.size()]));
                finish();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_USER) {
            if (resultCode == RESULT_OK) {
                mUser.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUser.setText(data.getStringExtra("NAME"));
                        mUser.showDropDown();
                        mUser.setSelection(mUser.getText().length());
                    }
                },500);
            }

               // mUser.setText(data.getStringExtra("NAME"));

            }
        }

}
