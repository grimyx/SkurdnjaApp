package com.ventpar.skurdnja.SkurdnjaViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUser;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Suborder;
import com.ventpar.skurdnja.SkurdnjaDB.SkurdnjaRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private SkurdnjaRepository mRepository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SkurdnjaRepository(application);
    }

    // SUBORDER

    public LiveData<List<Suborder>> getSubordersByOrderId(int id){
        return mRepository.getSubordersFromSameOrder(id);
    }

    public void insertSuborder(Suborder suborder){
        mRepository.insertSuborder(suborder);
    }

    public void insertSuborders(Suborder...suborders){
        mRepository.insertSuborders(suborders);
    }

    public void deleteSuborder(Suborder suborder){
        mRepository.deleteSuborder(suborder);
    }

    public void updateSuborder(Suborder suborder){
        mRepository.updateSuborder(suborder);
    }

    // ORDER

    public LiveData<List<Orders>> getOrdersByUserId(long id){
        return mRepository.getOrdersByUserId(id);
    }

    public long insertOrder(Orders orders){
        return mRepository.insertOrder(orders);
    }

    public void updateOrder(Orders orders){
        mRepository.updateOrder(orders);
    }

    public void deleteOrder(Orders orders){
        mRepository.deleteOrder(orders);
    }

    public LiveData<List<OrderWithUser>> getOrdersWithUsers(){
        return mRepository.getUndeliveredOrdersWithUser();
    }
}
