package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SuborderDao {

    // da li mi treba ovo ????
    //@Query("SELECT * FROM suborder")
    //LiveData<List<Suborder>> getAll();

    @Query("SELECT * FROM suborder WHERE order_id = :orderID")
    LiveData<List<Suborder>> getByOrderID(int orderID);

    @Insert
    void insertSuborder(Suborder suborder);

    @Insert
    void insertSuborders(Suborder...suborders);

    @Update
    void updateSuborder(Suborder suborder);

    @Delete
    void deleteSuborder(Suborder suborder);
}
