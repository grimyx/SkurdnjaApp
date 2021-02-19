package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductFromSuborderDao {

    @Query("SELECT * FROM suborder,product WHERE suborder.order_id = :id AND suborder.product_id = product.id")
    public LiveData<List<ProductFromSuborder>> getProductAndQuantity(long id);
}
