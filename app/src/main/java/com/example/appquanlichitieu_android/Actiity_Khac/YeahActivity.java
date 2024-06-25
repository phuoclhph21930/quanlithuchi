package com.example.appquanlichitieu_android.Actiity_Khac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.Pager_Khac_Adapter.Tab_ReportYeahKhac_Apdapter;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.inter.OnclickYeah;
import com.example.appquanlichitieu_android.inter.oncClickRevenueYeah;
import com.example.appquanlichitieu_android.pagerAdapter.TabViewPagerAdapter;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportCatalog_YeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportYeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_ReportYeahFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class YeahActivity extends AppCompatActivity implements OnclickYeah, oncClickRevenueYeah {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView imgTruoc,imgNext,imgback;
    private TextView txtDate;
    private OnclickYeah onclickYeah;
    private oncClickRevenueYeah oncClickRevenueYeah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeah);
        getId();



//set năm cho textview
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());

        txtDate.setText(ngayThangNam);

        // Trong Activity
        Bundle bundle = new Bundle();
        bundle.putString("key", ngayThangNam);
        Tab_ReportYeahKhac_Apdapter tabViewPagerAdapter = new Tab_ReportYeahKhac_Apdapter(
                getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle
        );
        viewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = txtDate.getText().toString();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, 1);
                int newYear = calendar.get(Calendar.YEAR);
                txtDate.setText(String.valueOf(newYear));
                if (onclickYeah!=null){
                    onclickYeah.onTextChangedClick(newYear);
                }
                if(oncClickRevenueYeah!=null){
                    oncClickRevenueYeah.onTextChangedClickRevenueYeah(newYear);
                }
            }
        });
        imgTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy năm hiện tại từ txtDateThu
                String selectedDate = txtDate.getText().toString();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, -1);
                int newYear = calendar.get(Calendar.YEAR);
                txtDate.setText(String.valueOf(newYear));
                if (onclickYeah!=null){
                    onclickYeah.onTextChangedClick(newYear);
                }
                if(oncClickRevenueYeah!=null){
                    oncClickRevenueYeah.onTextChangedClickRevenueYeah(newYear);
                }
            }
        });
    }


    public void getId(){
        viewPager = findViewById(R.id.pager_TabYeahac);
        tabLayout = findViewById(R.id.tabbarLayoutYeahh);
        imgTruoc = findViewById(R.id.imgtruocReportYeah);
        imgNext = findViewById(R.id.imgnextReportYeah);
        txtDate = findViewById(R.id.dateReportMonthYeah);
        imgback = findViewById(R.id.back_insert);
    }

    @Override
    public void onTextChangedClick(int year) {
        updateFragmentData(year);
    }
    public void updateFragmentData(int year) {
        // Tìm Fragment và cập nhật dữ liệu
        Expenses_ReportYeahFragment fragment = (Expenses_ReportYeahFragment) getSupportFragmentManager().findFragmentById(R.id.reportYear);
        if (fragment != null) {
            fragment.setTienDate(year);
            fragment.setbarchart(year);
        }
    }
    public void setOnclickYear(OnclickYeah onclickYeah){
        this.onclickYeah = onclickYeah;
    }
    // lắng nghe sự kiện onclick revenue Yeah
    @Override
    public void onTextChangedClickRevenueYeah(int year) {
        updateFragmentRevenueYeah(year);
    }
    public void updateFragmentRevenueYeah(int year) {
        // Tìm Fragment và cập nhật dữ liệu
        Revenue_ReportYeahFragment fragment = (Revenue_ReportYeahFragment) getSupportFragmentManager().findFragmentById(R.id.revenueYeah);
        if (fragment != null) {
            fragment.setTienDate(year);
            fragment.setdataBarchart(year);
        }
    }
    public void setOnclickRevenueYear(oncClickRevenueYeah oncClickRevenueYeah){
        this.oncClickRevenueYeah = oncClickRevenueYeah;
    }

}