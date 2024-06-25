package com.example.appquanlichitieu_android.Actiity_Khac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appquanlichitieu_android.Pager_Khac_Adapter.Tab_Report_Dm_Ki_Adapter;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.pagerAdapter.TabViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class DM_KiActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm_ki);
        viewPager = findViewById(R.id.pager_CatalogKi);
        tabLayout = findViewById(R.id.tabbarLayoutDmKi);
        imgback = findViewById(R.id.back_insert);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        Tab_Report_Dm_Ki_Adapter tabViewPagerAdapter = new Tab_Report_Dm_Ki_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}