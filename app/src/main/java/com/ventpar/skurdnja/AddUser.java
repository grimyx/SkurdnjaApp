package com.ventpar.skurdnja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.SkurdnjaViewModels.UserViewModel;

public class AddUser extends AppCompatActivity {

    private EditText mUserName;
    private EditText mUserSurname;
    private EditText mAddress;
    private EditText mCity;
    private EditText mTelephoneNumber;
    private EditText mEmail;
    private EditText mComments;

    private UserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mUserName = findViewById(R.id.user_name);
        mUserSurname = findViewById(R.id.user_surname);
        mAddress = findViewById(R.id.user_address);
        mCity = findViewById(R.id.user_city);
        mTelephoneNumber = findViewById(R.id.user_telephone_number);
        mEmail = findViewById(R.id.user_email);
        mComments = findViewById(R.id.user_comments);

        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_product) {
            if (checkFields()){
                String name = mUserName.getText().toString().trim();
                String surname = mUserSurname.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String city = mCity.getText().toString().trim();
                String telephoneNumber = mTelephoneNumber.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String comments = mComments.getText().toString().trim();

                User user= new User(name,surname,address,telephoneNumber,city,email,comments);
                mViewModel.insertUser(user);

                /*Intent intent = new Intent(AddUser.this, AddOrderActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);*/

                Intent resultIntent = getIntent();
                resultIntent.putExtra("NAME", user.toString());
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }


    private boolean checkFields(){
        if (mUserName.getText().toString().isEmpty()){
            mUserName.setError("Unesite ime kupca !");
            mUserName.requestFocus();
            return false;
        }
        else if (mUserSurname.getText().toString().isEmpty()){
            mUserSurname.setError("Unesite prezime kupca !");
            mUserSurname.requestFocus();
            return false;
        }
        else if (mAddress.getText().toString().isEmpty()){
            mAddress.setError("Unesite adresu kupca !");
            mAddress.requestFocus();
            return false;
        }
        else if (mCity.getText().toString().isEmpty()){
            mCity.setError("Unesite u kom gradu zivi kupac !");
            mCity.requestFocus();
            return false;
        }
        else if (mTelephoneNumber.getText().toString().isEmpty()) {
            mTelephoneNumber.setError("Unesite broj telefona kupca !");
            mTelephoneNumber.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }
}
