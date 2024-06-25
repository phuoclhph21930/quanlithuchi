package com.example.appquanlichitieu_android.Pager_Khac_Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.Tab_Expenses;
import com.example.appquanlichitieu_android.Fragment.Tab_Income;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_DM_KiFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_DM_KiFragment;

public class Tab_Report_Dm_Ki_Adapter extends FragmentStatePagerAdapter {

    public Tab_Report_Dm_Ki_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Expenses_DM_KiFragment();
            case 1:
                return new Revenue_DM_KiFragment();

            default:
                return new Expenses_DM_KiFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chi Tiêu";
            case 1:
                return "Thu Nhập";
            default:
                return "Chi Tiêu";
        }
    }
}
