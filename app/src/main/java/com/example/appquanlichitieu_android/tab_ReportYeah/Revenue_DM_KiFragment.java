package com.example.appquanlichitieu_android.tab_ReportYeah;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu_android.Apdapter.List_itemCalenderADapter;
import com.example.appquanlichitieu_android.Apdapter.List_item_ExpensesADapter;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
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


public class Revenue_DM_KiFragment extends Fragment {
    private PieChart pieChart;
    private RecyclerView rcvDM_Ki;
    private List_itemCalenderADapter expensesADapter;
    private ExpensesDAO ex;
    private List<ExpensesModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_revenue_dm_ki, container, false);

        getId_Dmki(view);
        setData_Dmki();
        setDataPieChart();
        // Inflate the layout for this fragment
        return view;
    }
    private void getId_Dmki(View view){
        ex = new ExpensesDAO(getContext());

        pieChart = view.findViewById(R.id.pieChartRevenue_Dm_Ki);
        rcvDM_Ki= view.findViewById(R.id.listChiRepor_Revenue_DMki);
    }
    private void setData_Dmki(){
        rcvDM_Ki.setLayoutManager(new LinearLayoutManager(getContext()));
        list = ex.getDataThu();
        expensesADapter = new List_itemCalenderADapter(getContext(),list);
        rcvDM_Ki.setAdapter(expensesADapter);
    }
    private void setDataPieChart(){
        HashMap<String, Integer> revenueByCategory = ex.getTotalRevenueDM_ki();
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : revenueByCategory.entrySet()) {
            String category = entry.getKey();
            int totalRevenue = entry.getValue();
            entries.add(new PieEntry(totalRevenue, category));
        }

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
}