package com.example.appquanlichitieu_android.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.Pager_Khac_Adapter.Tab_Report_Catalog_Yeah_Adapter;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.inter.ReportOnclick;
import com.example.appquanlichitieu_android.pagerAdapter.Tab_Layout_Month;
import com.example.appquanlichitieu_android.tab_ReportYeah.Expenses_ReportCatalog_YeahFragment;
import com.example.appquanlichitieu_android.tab_ReportYeah.Revenue_ReportCatalog_YeahFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ReportMonthFragment extends Fragment{
    TabLayout tabMonth;
    ViewPager page_Month;
    ImageView imgtruocReport, imgnextReport;
    private TextView tvReportMonth, tvChi, tvThu, tvThuChi;
    private DbQuanli dbQuanli;
    ExpensesDAO ex;
    public int month;
    private Tab_Layout_Month tabViewPagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_month, container, false);

        getIdReport(view);

        getMonth();
        setDolar(month);
        imgtruocReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = tvReportMonth.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("/")[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("/")[1]));
                calendar.add(Calendar.MONTH, -1);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                String newDate = String.format(Locale.getDefault(), "%02d/%04d", month, year);
                tvReportMonth.setText(newDate);
                setDolar(month);
                Bundle bundle = new Bundle();
                bundle.putString("thang", String.valueOf(month));
                bundle.putString("nam", String.valueOf(year));
                tabViewPagerAdapter.setdata(bundle);
            }
        });
        imgnextReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = tvReportMonth.getText().toString();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("/")[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("/")[1]));
                calendar.add(Calendar.MONTH, 1);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                String newDate = String.format(Locale.getDefault(), "%02d/%04d", month, year);
                tvReportMonth.setText(newDate);
                setDolar(month);
                Bundle bundle = new Bundle();
                bundle.putString("thang", String.valueOf(month));
                bundle.putString("nam", String.valueOf(year));
                tabViewPagerAdapter.setdata(bundle);


            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void getIdReport(View view) {
        tabMonth = view.findViewById(R.id.tabbarLayoutMonth);
        page_Month = view.findViewById(R.id.pager_TabMonth);
        tvChi = view.findViewById(R.id.ChiReportMonth);
        tvThu = view.findViewById(R.id.thuReportMonth);
        tvThuChi = view.findViewById(R.id.thuChiReportMonth);

        tvReportMonth = view.findViewById(R.id.dateReportMonth);
        imgnextReport = view.findViewById(R.id.imgnextReport);
        imgtruocReport = view.findViewById(R.id.imgtruocReport);
    }

    //set thu chi trong thang
    public void setDolar(int month) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        ex = new ExpensesDAO(getContext());
        int tong_thu = ex.getTotalExpensesInMonth(month);
        int tong = ex.getTotalRevenueInMonth(month);

        tvChi.setText(formatter.format(tong) + "đ");
        tvThu.setText(formatter.format(tong_thu) + "đ");
        int tong_Thu_Chi = tong_thu - tong;
        tvThuChi.setText(formatter.format(tong_Thu_Chi) + "đ");
        if(tong_Thu_Chi>0){
            tvThuChi.setTextColor(getResources().getColor(R.color.blue));
        }else{
            tvThuChi.setTextColor(getResources().getColor(R.color.red));
        }
    }

    //lay thang
    public void getMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        tvReportMonth.setText(ngayThangNam);
        String monthDate = tvReportMonth.getText().toString();
        try {
            Date date = dateFormat.parse(monthDate);
            calendar.setTime(date);
            month = calendar.get(Calendar.MONTH) + 1; // Lấy tháng (giá trị từ 0 - 11)
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //lay thang va nam
        String selectedDate = ngayThangNam;
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("/")[0]) - 1);
        calen.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("/")[1]));
        calendar.add(Calendar.MONTH, 1);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Bundle bundle = new Bundle();
        bundle.putString("MMreport", String.valueOf(month));
        bundle.putString("YYYYreport", String.valueOf(year));
        tabViewPagerAdapter = new Tab_Layout_Month(
                getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle
        );
        page_Month.setAdapter(tabViewPagerAdapter);
        tabMonth.setupWithViewPager(page_Month);
    }


}