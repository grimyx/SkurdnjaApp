package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ventpar.skurdnja.SkurdnjaDB.Converters;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "UserId",
        childColumns = "buyerID",
        onDelete = CASCADE), tableName = "MainOrders")

@TypeConverters(Converters.class)
public class Orders implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    // id kupca
    @ColumnInfo(index = true, name = "buyerID")
    @NonNull
    private long mBuyerID;

    // datum narucivanja
    @ColumnInfo(name = "date_of_order")
    @NonNull
    private Date mDateOfOrder;

    // datum slanja
    @ColumnInfo(name = "delivered_date")
    private Date mDeliveredDate;

    // da li je isporuceno ili nije
    // ako je 1 onda jeste
    // ako je 0 nije
    @ColumnInfo(name = "delivered")
    @NonNull
    private int mDelivered;

    // ukupna cena narudzbine
    @ColumnInfo(name = "total_price")
    private Double mTotalPrice;

    public Orders(long buyerID, Date dateOfOrder, int delivered){
        this.mBuyerID = buyerID;
        this.mDateOfOrder = dateOfOrder;
        this.mDelivered = delivered;;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getBuyerID() {
        return mBuyerID;
    }

    public void setBuyerID(int buyerID) {
        mBuyerID = buyerID;
    }

    @NonNull
    public Date getDateOfOrder() {
        return mDateOfOrder;
    }

    public void setDateOfOrder(@NonNull Date dateOfOrder) {
        mDateOfOrder = dateOfOrder;
    }

    public int getDelivered() {
        return mDelivered;
    }

    public void setDelivered(int delivered) {
        mDelivered = delivered;
    }

    public Date getDeliveredDate() {
        return mDeliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        mDeliveredDate = deliveredDate;
    }

    public Double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        mTotalPrice = totalPrice;
    }
}
