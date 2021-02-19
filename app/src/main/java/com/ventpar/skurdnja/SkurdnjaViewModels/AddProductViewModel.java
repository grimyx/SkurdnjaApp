package com.ventpar.skurdnja.SkurdnjaViewModels;

import android.app.Application;
import android.app.ListActivity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaDB.SkurdnjaRepository;
import com.ventpar.skurdnja.TempSuborder;

import java.util.ArrayList;
import java.util.List;

public class AddProductViewModel extends AndroidViewModel {

    private SkurdnjaRepository mRepository;
    private LiveData<List<Product>> mProducts;
    private ArrayList<TempSuborder> mTempSuborders;

    public AddProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SkurdnjaRepository(application);
        mProducts = mRepository.getAllProducts();
        mTempSuborders = new ArrayList<TempSuborder>();

    }

    public void insertProduct(String name, Double price, String comments){
        mRepository.insertProduct(new Product(name, price, comments));
    }

    public void updateProduct(Product product){
        mRepository.updateProduct(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return mProducts;
    }

    public List<Product> getSpinerData() {
        return mProducts.getValue();
    }

    public void deleteProduct(Product product){
        mRepository.deleteProduct(product);
    }

    public void addTempSuborder(TempSuborder tempSuborder){
        mTempSuborders.add(tempSuborder);
    }

    public ArrayList<TempSuborder> getTempSuborders() {
        return mTempSuborders;
    }

    /*public void CleanTempSuborders() {
        mTempSuborders.
    }*/
}
