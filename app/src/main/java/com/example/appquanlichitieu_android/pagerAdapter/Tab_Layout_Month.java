package com.example.appquanlichitieu_android.pagerAdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.Tab_Expenses;
import com.example.appquanlichitieu_android.Fragment.Tab_Income;

public class Tab_Layout_Month extends FragmentStatePagerAdapter {
private Bundle bundle;
private Tab_Expenses tabExpenses;
private Tab_Income tabIncome;



    public Tab_Layout_Month(@NonNull FragmentManager fm, int behavior, Bundle bundle) {
        super(fm, behavior);
        this.bundle= bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (tabExpenses==null){
                    tabExpenses = new Tab_Expenses();
                }
                tabExpenses.setArguments(bundle);
                return tabExpenses;
            case 1:
                if (tabIncome==null){
                    tabIncome = new Tab_Income();

                }
                tabIncome.setArguments(bundle);
                return tabIncome;

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
    public void setdata(Bundle bundle){
        this.bundle= bundle;
        if(tabExpenses!=null&&tabIncome!=null){
            tabExpenses.Reload(bundle);
            tabIncome.Reload(bundle);
        }
       /* if(tabIncome!=null){
            tabIncome.Reload(bundle);
        }*/


    }

}
