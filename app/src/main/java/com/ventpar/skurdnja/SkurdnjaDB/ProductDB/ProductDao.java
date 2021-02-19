package com.ventpar.skurdnja.SkurdnjaDB.ProductDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAll();

    @Insert
    void addProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Query("UPDATE product SET unsent = unsent + 1 where id = :id")
    void incrementUnsentProduct(long id);

    @Query("UPDATE product SET unsent = unsent - 1 WHERE id = :id")
    void decrementUnsentProduct(long id);

    @Query("UPDATE product SET sold_total = sold_total + 1 WHERE id = :id")
    void incrementSoldTotal(long id);
}
