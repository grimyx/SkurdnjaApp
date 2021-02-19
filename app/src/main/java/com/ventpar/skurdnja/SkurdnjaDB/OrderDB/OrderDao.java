package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    // da li mi treba ovo ?
    //@Query("SELECT * FROM `order`")
    //LiveData<List<Orders>> getAllOrders();

    @Query("SELECT * FROM MainOrders WHERE buyerID = :userID")
    LiveData<List<Orders>> findByUserId(long userID);

    @Insert
    long insertOrder(Orders orders);

    @Update
    void updateOrder(Orders orders);

    @Delete
    void deleteOrder(Orders orders);


}
