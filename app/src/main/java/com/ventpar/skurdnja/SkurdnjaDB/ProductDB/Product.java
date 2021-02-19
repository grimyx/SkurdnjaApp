package com.ventpar.skurdnja.SkurdnjaDB.ProductDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @ColumnInfo(name = "name")
    @NonNull
    private String mName;

    @ColumnInfo(name = "price")
    @NonNull
    private Double mPrice;

    @ColumnInfo(name = "comments")
    private String mComments;

    @ColumnInfo(name = "unsent")
    private int mUnsent;

    @ColumnInfo(name = "sold_total")
    private int mSoldTotal;

    public Product(String name, Double price, String comments){
        this.mName = name;
        this.mPrice = price;
        this.mComments = comments;
        this.mUnsent = 0;
        this.mSoldTotal = 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @NonNull
    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(@NonNull Double price) {
        mPrice = price;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }

    public int getUnsent() {
        return mUnsent;
    }

    public void setUnsent(int unsent) {
        mUnsent = unsent;
    }

    public int getSoldTotal() {
        return mSoldTotal;
    }

    public void setSoldTotal(int soldTotal) {
        mSoldTotal = soldTotal;
    }

    @Ignore
    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
