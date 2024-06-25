package com.example.appquanlichitieu_android.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.ExpensesFragment;
import com.example.appquanlichitieu_android.Fragment.ReportMonthFragment;
import com.example.appquanlichitieu_android.Fragment.ReportYearFragment;
import com.example.appquanlichitieu_android.Fragment.RevenueFragment;

public class TabReportAdapter extends FragmentStatePagerAdapter {
    public TabReportAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ReportMonthFragment();
            case 1:
                return new ReportYearFragment();

            default:
                return new ReportMonthFragment();
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
                return "Tháng";
            case 1:
                return "Năm";
            default:
                return "Tháng";
        }

    }
}
