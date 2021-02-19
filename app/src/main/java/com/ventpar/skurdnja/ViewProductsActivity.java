package com.ventpar.skurdnja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toolbar;

import com.ventpar.skurdnja.Adapters.AllProductsAdapter;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaViewModels.AddProductViewModel;

import java.util.List;

public class ViewProductsActivity extends AppCompatActivity {

    private RecyclerView mAllProducts;
    private AddProductViewModel mViewModel;
    private AllProductsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mAllProducts = findViewById(R.id.all_products_rv);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mAllProducts.addItemDecoration(dividerItemDecoration);
        //mAllProducts.fling(10,10);



        mAdapter = new AllProductsAdapter(this);

        mAllProducts.setAdapter(mAdapter);
        mAllProducts.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);
        mViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapter.setProducts(products);
                Log.v("Test: ", Integer.toString(products.size()));
            }

        });

        mAdapter.setOnItemClickListener(new AllProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(ViewProductsActivity.this, AddProductActivity.class);
                intent.putExtra(AddProductActivity.PRODUCT, product);
                startActivity(intent,null);
                Log.v("Product name", product.getName());
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                    /*
                    int pos = viewHolder.getAdapterPosition();
                    mViewModel.deleteProduct(mAdapter.getProduct(pos));
                    */

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewProductsActivity.this)
                        .setTitle("Brisanje")
                        .setMessage("Da li ste sigurni da zelite da obrisete ovaj proizvod !")
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int pos = viewHolder.getAdapterPosition();
                                mViewModel.deleteProduct(mAdapter.getProduct(pos));
                            }
                        })
                        .setNegativeButton("NE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                mAllProducts.setAdapter(mAdapter);
                            }
                        });
                alertDialog.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mAllProducts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Intent intent = new Intent(ViewProductsActivity.this, AddProductActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
