package com.ventpar.skurdnja.SkurdnjaViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ventpar.skurdnja.SkurdnjaDB.OrderDB.Suborder;
import com.ventpar.skurdnja.SkurdnjaDB.SkurdnjaRepository;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private SkurdnjaRepository mRepository;
    private LiveData<List<User>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SkurdnjaRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers()  {return mAllUsers; }

    public void insertUser(User user)
    {
        mRepository.insertUser(user);
    }

    public void updateUser(User user)
    {
        mRepository.updateUser(user);
    }

    public void deleteUser(User user)
    {
        mRepository.deleteUser(user);
    }
}
