package com.example.appquanlichitieu_android.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu_android.Fragment.CalendarFragment;
import com.example.appquanlichitieu_android.Fragment.InputFragment;
import com.example.appquanlichitieu_android.Fragment.ReportFragment;
import com.example.appquanlichitieu_android.Fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InputFragment();
            case 1:
                return new CalendarFragment();
            case 2:
                return new ReportFragment();
            case 3:
                return new SettingFragment();
            default:
                return new InputFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
