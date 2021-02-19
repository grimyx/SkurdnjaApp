package com.ventpar.skurdnja.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ventpar.skurdnja.SkurdnjaDB.UserDB.User;
import com.ventpar.skurdnja.UserDataFragment;
import com.ventpar.skurdnja.UserOrdersFragment;
import com.ventpar.skurdnja.UserProductsChartFragment;

public class BuyerViewPager extends FragmentPagerAdapter {

    private int mCount;
    private User mUser;

    public BuyerViewPager(FragmentManager fm, int count, User user) {
        super(fm);
        mCount = count;
        mUser = user;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UserDataFragment(mUser);
            case 1:
                //return new UserOrdersFragment(mUser);
                return new UserProductsChartFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
