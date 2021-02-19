package com.ventpar.skurdnja;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDataFragment extends Fragment {


    private User mUser;
    private TextView mUserName;
    private TextView mUserSurname;
    private TextView mCity;
    private TextView mAddress;
    private TextView mTelephoneNumber;
    private TextView mEmail;
    private TextView mComments;

    public UserDataFragment() {
        // Required empty public constructor
    }

    public UserDataFragment(User user) {
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUserName = view.findViewById(R.id.data_user_name);
        mUserSurname = view.findViewById(R.id.data_user_surname);
        mAddress = view.findViewById(R.id.data_user_address);
        mCity = view.findViewById(R.id.data_user_city);
        mTelephoneNumber = view.findViewById(R.id.data_user_telephone_number);
        mEmail = view.findViewById(R.id.data_user_email);
        mComments = view.findViewById(R.id.data_user_comments);

        if (mUser != null) {
            mUserName.setText(mUser.getUserName());
            mUserSurname.setText(mUser.getUserSurname());
            mAddress.setText(mUser.getAddress());
            mCity.setText(mUser.getCity());
            mTelephoneNumber.setText(mUser.getTelephoneNumber());
            mEmail.setText(mUser.getEmail());
            mComments.setText(mUser.getComments());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (mUser == null) {
            inflater.inflate(R.menu.menu_add_product, menu);
        } else {
            inflater.inflate(R.menu.menu_update_user, menu);
        }
    }
}