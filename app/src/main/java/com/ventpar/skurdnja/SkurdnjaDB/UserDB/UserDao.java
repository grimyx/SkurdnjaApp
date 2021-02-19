package com.ventpar.skurdnja.SkurdnjaDB.UserDB;

import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Insert
    void addUser(User user);

    @Insert
    void addUsers(User...users);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}
