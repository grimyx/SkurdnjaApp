package com.ventpar.skurdnja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ventpar.skurdnja.Adapters.BuyerViewPager;
import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;

public class UserDetailsActivity extends AppCompatActivity {

    private TabItem userData;
    private TabItem userOrders;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mTabLayout = findViewById(R.id.tabLayout);
        userData = findViewById(R.id.tabItemBuyer);
        userOrders = findViewById(R.id.tabItemOrders);
        mViewPager = findViewById(R.id.tabViewPager);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra("USER");
        mViewPager.setAdapter(new BuyerViewPager(getSupportFragmentManager(),2,mUser));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
