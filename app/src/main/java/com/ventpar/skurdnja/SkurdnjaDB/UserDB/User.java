package com.ventpar.skurdnja.SkurdnjaDB.UserDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "UserId")
    private long mId;

    @ColumnInfo(name = "user_name")
    @NonNull
    private String mUserName;

    @ColumnInfo(name = "user_surname")
    @NonNull
    private String mUserSurname;

    @ColumnInfo(name = "address")
    @NonNull
    private String mAddress;

    @ColumnInfo(name = "tel_number")
    @NonNull
    private String mTelephoneNumber;

    @ColumnInfo(name = "user_city")
    @NonNull
    private String mCity;

    @ColumnInfo(name = "user_email")
    private String mEmail;

    @ColumnInfo(name = "comments")
    private String mComments;

    public User() {}

    public User(String name,
         String surname,
         String address,
         String tel_number,
         String city,
         String email,
         String comments)
    {
        this.mUserName = name;
        this.mUserSurname = surname;
        this.mAddress = address;
        this.mTelephoneNumber = tel_number;
        this.mCity = city;
        this.mEmail = email;
        this.mComments = comments;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @NonNull
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(@NonNull String userName) {
        mUserName = userName;
    }

    @NonNull
    public String getUserSurname() {
        return mUserSurname;
    }

    public void setUserSurname(@NonNull String userSurname) {
        mUserSurname = userSurname;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(@NonNull String address) {
        mAddress = address;
    }

    @NonNull
    public String getTelephoneNumber() {
        return mTelephoneNumber;
    }

    public void setTelephoneNumber(@NonNull String telephoneNumber) {
        mTelephoneNumber = telephoneNumber;
    }

    @NonNull
    public String getCity() {
        return mCity;
    }

    public void setCity(@NonNull String city) {
        mCity = city;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }

    @Ignore

    @NonNull
    @Override
    public String toString() {
        return this.mUserName + " " + this.mUserSurname
                + ", "
                + this.mCity;
    }
}
