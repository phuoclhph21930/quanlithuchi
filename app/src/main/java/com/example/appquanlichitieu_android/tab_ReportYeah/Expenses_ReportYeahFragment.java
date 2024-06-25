package com.example.appquanlichitieu_android.tab_ReportYeah;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appquanlichitieu_android.Actiity_Khac.Catalog_Yeah_Activity;
import com.example.appquanlichitieu_android.Actiity_Khac.YeahActivity;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Expenses_ReportYeahFragment extends Fragment {
    private TextView txtTong,txtTrungBinh;
    private BarChart barChart;
    private ExpensesDAO ex;
    private ArrayList<BarEntry> barEntries;
    DecimalFormat formatter;
    private TextView thang1,thang2,thang3,thang4,thang5,thang6,thang7,thang8,thang9,thang10,thang11,thang12;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof YeahActivity) {
            ((YeahActivity) context).setOnclickYear((year) -> {
               setTienDate(year);
               setbarchart(year);
            });
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_expenses__report_yeah, container, false);
            getIdReportYeah(view);

        if (getArguments() != null) {
            String value = getArguments().getString("key");
            setTienDate(Integer.parseInt(value));
            txtTong.setText(String.valueOf(ex.getTotalExpensesInYear(Integer.parseInt(value))));
            setbarchart(Integer.parseInt(value));
        } else {
        }

        return view;
    }

    public void getIdReportYeah(View view){
        ex = new ExpensesDAO(getContext());
         formatter = new DecimalFormat("#,###");
        txtTong = view.findViewById(R.id.txtTongReportYeah);
        barChart = view.findViewById(R.id.pieChartExpensesreportYeah);
        thang1 = view.findViewById(R.id.thang1);
        thang2 = view.findViewById(R.id.thang2);
        thang3 = view.findViewById(R.id.thang3);
        thang4 = view.findViewById(R.id.thang4);
        thang5 = view.findViewById(R.id.thang5);
        thang6 = view.findViewById(R.id.thang6);
        thang7 = view.findViewById(R.id.thang7);
        thang8 = view.findViewById(R.id.thang8);
        thang9 = view.findViewById(R.id.thang9);
        thang10 = view.findViewById(R.id.thang10);
        thang11 = view.findViewById(R.id.thang11);
        thang12 = view.findViewById(R.id.thang12);

    }
    public void setTienDate(int year) {
        int t1 = ex.getTotalExpensesForMonth(1, year);
        int t2 = ex.getTotalExpensesForMonth(2, year);
        int t3 = ex.getTotalExpensesForMonth(3, year);
        int t4 = ex.getTotalExpensesForMonth(4, year);
        int t5 = ex.getTotalExpensesForMonth(5, year);
        int t6 = ex.getTotalExpensesForMonth(6, year);
        int t7 = ex.getTotalExpensesForMonth(7, year);
        int t8 = ex.getTotalExpensesForMonth(8, year);
        int t9 = ex.getTotalExpensesForMonth(9, year);
        int t10 = ex.getTotalExpensesForMonth(10, year);
        int t11 = ex.getTotalExpensesForMonth(11, year);
        int t12= ex.getTotalExpensesForMonth(12, year);
        int total = ex.getTotalExpensesYeah(year);


        thang1.setText(String.valueOf(formatter.format(t1) + "đ"));
        thang2.setText(String.valueOf(formatter.format(t2) + "đ"));
        thang3.setText(String.valueOf(formatter.format(t3) + "đ"));
        thang4.setText(String.valueOf(formatter.format(t4) + "đ"));
        thang5.setText(String.valueOf(formatter.format(t5) + "đ"));
        thang6.setText(String.valueOf(formatter.format(t6) + "đ"));
        thang7.setText(formatter.format(t7) + "đ");
        thang8.setText(String.valueOf(formatter.format(t8) + "đ"));
        thang9.setText(String.valueOf(formatter.format(t9) + "đ"));
        thang10.setText(String.valueOf(formatter.format(t10) + "đ"));
        thang11.setText(String.valueOf(formatter.format(t11)+ "đ"));
        thang12.setText(String.valueOf(formatter.format(t12) + "đ"));
        txtTong.setText(formatter.format(total) + "đ");
    }
    public void setbarchart(int yearr) {
        Map<String, Integer> data = ex.getTotalExpensesMonth(yearr);

        Map<String, Integer> totals = new HashMap<>();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String date = entry.getKey();
            int value = entry.getValue();

            String[] dateParts = date.split("/");
            int month = Integer.parseInt(dateParts[0]);
            int year = Integer.parseInt(dateParts[1]);
            int year3 = Integer.parseInt(dateParts[2]);
            String monthYear = String.format("%d/%d", year, year3);

            if (!totals.containsKey(monthYear)) {
                totals.put(monthYear, year);
            }

            totals.put(monthYear, totals.get(monthYear) + value);
        }


        List<BarEntry> barEntries = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : totals.entrySet()) {
            String monthYear = entry.getKey();
            int value = entry.getValue();
            String[] parts = monthYear.split("/");
            float month = Float.parseFloat(parts[0]);
            float year = Float.parseFloat(parts[1]);
            barEntries.add(new BarEntry(month, value));
            System.out.printf("Tháng %s: %d\n", monthYear, value);
        }


        // Thiết lập các thuộc tính của biểu đồ
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.getAxisRight().setEnabled(false); // Ẩn giá trị bên phải
        // Tạo dữ liệu cho biểu đồ
        BarDataSet barDataSet = new BarDataSet(barEntries, null);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barDataSet.setValueTextSize(9.5f); // Tăng kích thước của giá trị
        barDataSet.setValueTextColor(Color.BLACK); // Thiết lập màu cho giá trị
        barChart.setData(barData);
        barChart.invalidate(); // Cập nhật lại biểu đồ
    }
}