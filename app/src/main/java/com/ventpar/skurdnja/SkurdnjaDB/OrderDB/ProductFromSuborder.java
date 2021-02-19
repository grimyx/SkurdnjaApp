package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import android.content.Intent;

import androidx.room.Embedded;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

public class ProductFromSuborder {

    @Embedded
    public Product product;

    @Embedded
    public Suborder suborder;
}
