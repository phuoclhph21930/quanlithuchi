package com.example.appquanlichitieu_android.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.ExpensesFragment;
import com.example.appquanlichitieu_android.Fragment.RevenueFragment;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    public TabViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ExpensesFragment();
            case 1:
                return new RevenueFragment();

            default:
                return new ExpensesFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Tiền chi";
            case 1:
                return "Tiền thu";
            default:
                return "Tiền chi";
        }

    }
}
