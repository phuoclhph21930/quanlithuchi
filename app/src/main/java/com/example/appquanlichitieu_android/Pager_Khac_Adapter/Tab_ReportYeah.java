package com.example.appquanlichitieu_android.Pager_Khac_Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.Tab_Expenses;
import com.example.appquanlichitieu_android.Fragment.Tab_Income;

public class Tab_ReportYeah extends FragmentStatePagerAdapter {

    public Tab_ReportYeah(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tab_Expenses();
            case 1:
                return new Tab_Income();

            default:
                return new Tab_Expenses();
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
