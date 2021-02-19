package com.ventpar.skurdnja.SkurdnjaDB;

import android.content.Context;

import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Orders;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.OrderWithUserDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.ProductFromSuborderDao;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Suborder;
import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.SuborderDao;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.ProductDao;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Product.class, Orders.class, Suborder.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao mUserDao();
    public abstract ProductDao mProductDao();
    public abstract OrderDao mOrderDao();
    public abstract SuborderDao mSuborderDao();
    public abstract OrderWithUserDao mOrderWithUserDao();
    public abstract ProductFromSuborderDao mProductFromSuborderDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "skurdnja_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
