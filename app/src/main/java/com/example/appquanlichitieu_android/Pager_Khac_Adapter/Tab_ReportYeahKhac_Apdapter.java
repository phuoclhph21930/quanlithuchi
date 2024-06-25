package com.example.appquanlichitieu_android.Pager_Khac_Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.Tab_Expenses;
import com.example.appquanlichitieu_android.Fragment.Tab_Income;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportYeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_ReportYeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Total_ReportYeahFragment;

public class Tab_ReportYeahKhac_Apdapter extends FragmentStatePagerAdapter {
    private Bundle dataBundle;
    public Tab_ReportYeahKhac_Apdapter(@NonNull FragmentManager fm, int behavior, Bundle dataBundle) {
        super(fm, behavior);
        this.dataBundle = dataBundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Expenses_ReportYeahFragment expensesReportYeahFragment = new Expenses_ReportYeahFragment();
                expensesReportYeahFragment.setArguments(dataBundle);
                return expensesReportYeahFragment;
            case 1:
                Revenue_ReportYeahFragment revenueReportYeahFragment = new Revenue_ReportYeahFragment();
                revenueReportYeahFragment.setArguments(dataBundle);
                return revenueReportYeahFragment;
            default:
                return new Expenses_ReportYeahFragment();
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
