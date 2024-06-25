package com.example.appquanlichitieu_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.appquanlichitieu_android.pagerAdapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.ViewPage);
        bottomNavigationView = findViewById(R.id.botomNavigation);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_item1).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_item2).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_item3).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_item4).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_item1) {
                    // Xử lý khi chọn item 1
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.menu_item2) {
                    // Xử lý khi chọn item 2
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (item.getItemId() == R.id.menu_item3) {
                    // Xử lý khi chọn item 3
                    viewPager.setCurrentItem(2);
                    return true;
                }else if (item.getItemId() == R.id.menu_item4) {
                    // Xử lý khi chọn item 3
                    viewPager.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });
    }
}