package com.example.appquanlichitieu_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.inter.ReportOnclick;
import com.example.appquanlichitieu_android.pagerAdapter.TabReportAdapter;
import com.example.appquanlichitieu_android.pagerAdapter.TabViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class ReportFragment extends Fragment {
    private ViewPager viewPager;
    private View view;
    private TabLayout tabLayout;


    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        // Inflate the layout for this fragment
        viewPager = view.findViewById(R.id.pagerReport);
        tabLayout = view.findViewById(R.id.tabbarLayoutReport);
        TabReportAdapter tabViewPagerAdapter = new TabReportAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}