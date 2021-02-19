package com.ventpar.skurdnja.SkurdnjaDB.OrderDB;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.FtsOptions;
import androidx.room.Relation;

import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import java.io.Serializable;
import java.util.List;

public class OrderWithUser implements Serializable {

    @Embedded
    public User user;

    @Embedded
    public Orders order;

}
