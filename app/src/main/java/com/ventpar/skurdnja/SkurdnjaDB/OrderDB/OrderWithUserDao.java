package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderWithUserDao {

    @Query("SELECT * FROM MainOrders,user WHERE delivered = 0 AND MainOrders.buyerID = UserId ORDER BY date_of_order ASC")
    LiveData<List<OrderWithUser>> getUndeliveredOrders();
}
