package com.ventpar.skurdnja;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaViewModels.AddProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    private TextView productName;
    private TextView pricePerItem;
    private TextView comments;
    private AddProductViewModel mViewModel;

    private Bundle extras;
    public static final String PRODUCT = "product";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = findViewById(R.id.product_name);
        pricePerItem = findViewById(R.id.price_pi);
        comments = findViewById(R.id.product_comments);

        extras = getIntent().getExtras();

        if (extras != null ){
            Product product = (Product) extras.getSerializable(PRODUCT);
            productName.setText(product.getName());
            pricePerItem.setText(Double.toString(product.getPrice()));
            comments.setText(product.getComments());
            setTitle("Uredi proizvod");
        }

        mViewModel = ViewModelProviders.of(this).get(AddProductViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_product) {
                saveOrUpdateProduct();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveProduct(String name, Double price, String comments){
        mViewModel.insertProduct(name,price,comments);
    }

    private void updateProduct(Product product){
        mViewModel.updateProduct(product);
    }

    private void saveOrUpdateProduct() {
        String pName = productName.getText().toString().trim();
        Double price;
        String comm = comments.getText().toString().trim();

        if (pricePerItem.getText().toString().isEmpty()){
            price = 0.0;
        }
        else {
            price = Double.parseDouble(pricePerItem.getText().toString().trim());
        }

        if (pName.isEmpty()) {
            productName.setError("Unesite naziv proizvoda !");
            productName.requestFocus();
        } else if (price == 0.0) {
            pricePerItem.setError("Unesite cenu po komadu !");
            pricePerItem.requestFocus();
        } else {
                if (extras == null){
                    saveProduct(pName,price,comm);
                    finish();
                }
                else {
                    Product product = (Product) extras.getSerializable(PRODUCT);
                    product.setName(pName);
                    product.setPrice(price);
                    product.setComments(comm);
                    updateProduct(product);
                    finish();
                }
        }
    }
}
