package com.example.appquanlichitieu_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.pagerAdapter.Tab_Layout_Month;
import com.example.appquanlichitieu_android.pagerAdapter.Tab_Layout_Report_YeahAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ReportYearFragment extends Fragment {
    TabLayout tabMonth;
    ViewPager page_Month;
    private DbQuanli dbQuanli;
    private ExpensesDAO ex;
    private ImageView imgtruocReport,imgnextReport;
    private TextView dateReportYeah,tvChiYeah,tvThuYeah,tvThuChiyeah;
    private Tab_Layout_Report_YeahAdapter tabViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_year, container, false);
        tabMonth = view.findViewById(R.id.tabbarLayoutYeah);
        page_Month = view.findViewById(R.id.pager_TabYeah);
        getIdReport(view);


        //set năm cho textview
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        dateReportYeah.setText(ngayThangNam);

        //lấy năm của textview
        String monthDate = dateReportYeah.getText().toString();
        Date date = null;
        try {
            date = dateFormat.parse(monthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendar.setTime(date);

         int month = calendar.get(Calendar.MONTH) + 1; // Lấy tháng (giá trị từ 0 - 11)
        int year = calendar.get(Calendar.YEAR);
        Bundle bundle = new Bundle();
        bundle.putString("MMreport", String.valueOf(month));
        bundle.putString("YYYYreport", String.valueOf(year));
        tabViewPagerAdapter = new Tab_Layout_Report_YeahAdapter(
                getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, bundle
        );
        page_Month.setAdapter(tabViewPagerAdapter);
        tabMonth.setupWithViewPager(page_Month);
        setDoanhThu(year);



        imgtruocReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy năm hiện tại từ txtDateThu
                String selectedDate = dateReportYeah.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, -1);
                int newYear = calendar.get(Calendar.YEAR);
                dateReportYeah.setText(String.valueOf(newYear));
                setDoanhThu(newYear);
                Bundle bundle = new Bundle();
                bundle.putString("thang", String.valueOf(month));
                bundle.putString("nam", String.valueOf(newYear));
                tabViewPagerAdapter.setdata(bundle);
            }
        });
        imgnextReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy năm hiện tại từ txtDateThu
                String selectedDate = dateReportYeah.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate));
                calendar.add(Calendar.YEAR, 1);
                int newYear = calendar.get(Calendar.YEAR);
                dateReportYeah.setText(String.valueOf(newYear));
                setDoanhThu(newYear);
                Bundle bundle = new Bundle();
                bundle.putString("thang", String.valueOf(month));
                bundle.putString("nam", String.valueOf(newYear));
                tabViewPagerAdapter.setdata(bundle);
            }
        });
        return view;
    }
    public void getIdReport(View view){

        tvChiYeah = view.findViewById(R.id.ChiReportYeah);
        tvThuYeah = view.findViewById(R.id.thuReportYeah);
        tvThuChiyeah = view.findViewById(R.id.thuChiReportYeah);
        dateReportYeah = view.findViewById(R.id.dateReportYeah);
        imgnextReport = view.findViewById(R.id.imgnextReportYeah);
        imgtruocReport = view.findViewById(R.id.imgtruocReportYeah);
        // khởi tạo
        ex = new ExpensesDAO(getContext());
    }

    //set doanh thu và khoản chi
    public void setDoanhThu(int yeah){
        DecimalFormat formatter = new DecimalFormat("#,###");
        int tong_thu = ex.getTotalRevenueInYear(yeah);
        int tong_Chi = ex.getTotalExpensesInYear(yeah);

        tvChiYeah.setText(formatter.format(tong_Chi) + "đ");
        tvThuYeah.setText(formatter.format(tong_thu) + "đ");
        int tong_Thu_Chi = tong_thu - tong_Chi;
        tvThuChiyeah.setText(formatter.format(tong_Thu_Chi) + "đ");
        if (tong_Thu_Chi>0){
            tvThuChiyeah.setTextColor(getResources().getColor(R.color.blue));
        }else{
            tvThuChiyeah.setTextColor(getResources().getColor(R.color.red));
        }
    }
}