package com.example.appquanlichitieu_android.Actiity_Khac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.Pager_Khac_Adapter.Tab_ReportYeahKhac_Apdapter;
import com.example.appquanlichitieu_android.Pager_Khac_Adapter.Tab_Report_Catalog_Yeah_Adapter;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.inter.OnTextChangeListener;
import com.example.appquanlichitieu_android.inter.onClick;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportCatalog_YeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_ReportCatalog_YeahFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Catalog_Yeah_Activity extends AppCompatActivity implements OnTextChangeListener, onClick {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView txttruoc,txtSau;
    private TextView txtdate_DM;
    private onClick onclick;
    private OnTextChangeListener onDateSelectedListener;
    private ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        getID();
        //set năm cho textview
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        txtdate_DM.setText(ngayThangNam);
        imgback = findViewById(R.id.back_insert);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        txttruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy năm hiện tại từ txtDateThu
                String selectedDate = txtdate_DM.getText().toString();
// Tạo đối tượng Calendar từ năm đã lấy
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, -1);
                int newYear = calendar.get(Calendar.YEAR);
                txtdate_DM.setText(String.valueOf(newYear));
                if (onDateSelectedListener != null) {
                    onDateSelectedListener.onTextChanged(newYear);
                }
                if (onclick != null) {
                    onclick.onTextChangedClick(newYear);
                }
            }
        });
        txtSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = txtdate_DM.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, 1);
                int newYear = calendar.get(Calendar.YEAR);
                txtdate_DM.setText(String.valueOf(newYear));
                if (onDateSelectedListener != null) {
                    onDateSelectedListener.onTextChanged(newYear);
                }
                if (onclick != null) {
                    onclick.onTextChangedClick(newYear);
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("YYYY", ngayThangNam);
        Tab_Report_Catalog_Yeah_Adapter tabViewPagerAdapter = new Tab_Report_Catalog_Yeah_Adapter(
                getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle
        );
        viewPager.setAdapter(tabViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void getID(){
        viewPager = findViewById(R.id.pager_CatalogYeah);
        tabLayout = findViewById(R.id.tabbarLayoutCatalog);
        txttruoc = findViewById(R.id.imgtruocReport_DM_Yeah);
        txtSau = findViewById(R.id.imgnextReport_Dm_Yeah);
        txtdate_DM = findViewById(R.id.dateReportMonth_Dm_Yeah);
    }

    @Override
    public void onTextChanged(int date) {
        txtdate_DM.setText(String.format("%d", date));

        // Thông báo cho Fragment để cập nhật RecyclerView
        updateFragmentData(date);
    }
    public void updateFragmentData(int year) {
        // Tìm Fragment và cập nhật dữ liệu
        Expenses_ReportCatalog_YeahFragment fragment = (Expenses_ReportCatalog_YeahFragment) getSupportFragmentManager().findFragmentById(R.id.expenCatalog);
        if (fragment != null) {
            fragment.setDataRCV(year);
        }
    }

    public void setOnDateSelectedListener(OnTextChangeListener listener) {
        this.onDateSelectedListener = listener;
    }
    public void updateFragmentDataRevenue(int year) {
        // Tìm Fragment và cập nhật dữ liệu
        Revenue_ReportCatalog_YeahFragment fragment = (Revenue_ReportCatalog_YeahFragment) getSupportFragmentManager().findFragmentById(R.id.revenueCatalog);
        if (fragment != null) {
            fragment.LoadDataRCV(year);
        }
    }
    @Override
    public void onTextChangedClick(int date) {
        txtdate_DM.setText(String.format("%d", date));

        // Thông báo cho Fragment để cập nhật RecyclerView
        updateFragmentDataRevenue(date);
    }

    public void setOncl(onClick oncl){
        this.onclick = oncl;
    }
}