package com.example.appquanlichitieu_android.Pager_Khac_Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.Tab_Expenses;
import com.example.appquanlichitieu_android.Fragment.Tab_Income;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportCatalog_YeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_ReportCatalog_YeahFragment;

public class Tab_Report_Catalog_Yeah_Adapter extends FragmentStatePagerAdapter {
private Bundle bundle;
    public Tab_Report_Catalog_Yeah_Adapter(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Expenses_ReportCatalog_YeahFragment expensesReportCatalogYeahFragment = new Expenses_ReportCatalog_YeahFragment();
                expensesReportCatalogYeahFragment.setArguments(bundle);
                return expensesReportCatalogYeahFragment;
            case 1:
                Revenue_ReportCatalog_YeahFragment revenueReportCatalogYeahFragment = new Revenue_ReportCatalog_YeahFragment();
                revenueReportCatalogYeahFragment.setArguments(bundle);
                return revenueReportCatalogYeahFragment;

            default:
                return new Expenses_ReportCatalog_YeahFragment();
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
