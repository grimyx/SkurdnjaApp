package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.ventpar.skurdnja.SkurdnjaDB.ProductDB.Product;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Orders.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = CASCADE),
        @ForeignKey(entity = Product.class,
                parentColumns = "id",
                childColumns = "product_id",
                onDelete = CASCADE)})

public class Suborder {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "suborder_id")
    private long id;

    @ColumnInfo(index = true)
    private long product_id;

    @ColumnInfo(index = true)
    private long order_id;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public Suborder(long product_id,long order_id, int quantity){
        this.product_id = product_id;
        this.order_id = order_id;
        this.quantity = quantity;
    }

    @Ignore
    public Suborder(long product_id, int quantity){
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quntity) {
        this.quantity = quntity;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}