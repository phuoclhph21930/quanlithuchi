package com.example.appquanlichitieu_android.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appquanlichitieu_android.Actiity_Khac.Catalog_Yeah_Activity;
import com.example.appquanlichitieu_android.Apdapter.List_itemCalenderADapter;
import com.example.appquanlichitieu_android.Apdapter.List_item_ExpensesADapter;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.inter.ReportOnclick;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tab_Expenses extends Fragment {
    private RecyclerView listChiReport;
    private ExpensesDAO expensesDAO;
    private List_item_ExpensesADapter listItemExpensesADapter;
    private List<ExpensesModel> list;
    private List_itemCalenderADapter listItemCalenderADapter;
    PieChart pieChart;
    String thang,nam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab__expenses, container, false);
        listChiReport = view.findViewById(R.id.listChiRepor);
        pieChart = (PieChart) view.findViewById(R.id.pieChartExpenses);

        if (getArguments() != null) {
            String value = getArguments().getString("YYYYreport");
            String month = getArguments().getString("MMreport");
            loadData(Integer.parseInt(month),Integer.parseInt(value));
            LoadPieChart(Integer.parseInt(month),Integer.parseInt(value));
        } else {

        }

        return view;
    }
    public void Reload(Bundle bundle){
        thang= bundle.getString("thang","");
         nam = bundle.getString("nam","");
        loadData(Integer.parseInt(thang),Integer.parseInt(nam));
        LoadPieChart(Integer.parseInt(thang),Integer.parseInt(nam));
    }
    public void LoadPieChart(int month,int year){
        HashMap<String, Integer> revenueByCategory = expensesDAO.getTotalExpensesByCategory(month,year);
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : revenueByCategory.entrySet()) {
            String category = entry.getKey();
            int totalRevenue = entry.getValue();
            Log.e("gia tri","phan tram "+totalRevenue);
            entries.add(new PieEntry(totalRevenue, category));
        }

// Tạo PieDataSet từ danh sách PieEntry
        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setValueTextSize(19f);
        PieData data = new PieData(dataSet);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED); // Màu cho phần tử 1
        colors.add(Color.BLUE); // Màu cho phần tử 2
        colors.add(Color.GREEN);
// ... Tiếp tục thêm màu cho các phần tử khác
        dataSet.setColors(colors);

// Tạo PieData từ PieDataSet
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setUsePercentValues(true);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(50f);
        pieChart.getLegend().setEnabled(false);
        pieChart.setTransparentCircleRadius(100f);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
    }
    public void loadData(int month, int year){
        expensesDAO = new ExpensesDAO(getContext());
        listChiReport.setLayoutManager(new LinearLayoutManager(getContext()));
        list = expensesDAO.getDataByMonthAndChi(month,year);
        listItemCalenderADapter = new List_itemCalenderADapter(getContext(), list);
        listChiReport.setAdapter(listItemCalenderADapter);
    }


}