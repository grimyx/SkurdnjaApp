package com.ventpar.skurdnja.SkurdnjaViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborder;
import com.ventpar.skurdnja.SkurdnjaDB.SkurdnjaRepository;

import java.util.List;

public class UnsentProductsViewModel extends AndroidViewModel {

    private SkurdnjaRepository mRepository;

    public UnsentProductsViewModel(Application application) {
        super(application);
        mRepository = new SkurdnjaRepository(application);
    }

    public LiveData<List<ProductFromSuborder>> getProductAndQuantity(long id) {
        return mRepository.getProductAndQuantity(id);
    }
}
