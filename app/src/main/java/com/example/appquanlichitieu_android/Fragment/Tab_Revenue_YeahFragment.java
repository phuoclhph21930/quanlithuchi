package com.example.appquanlichitieu_android.Fragment;

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


public class Tab_Revenue_YeahFragment extends Fragment {

    private RecyclerView rcvCatalogYeah;
    private PieChart pieChart;
    private ExpensesDAO ex;
    private List_itemCalenderADapter listItemCalenderADapter;
    private List<ExpensesModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab__revenue__yeah, container, false);
        getIdCatalog(view);
        if (getArguments() != null) {
            String value = getArguments().getString("YYYYreport");
            String month = getArguments().getString("MMreport");
           setDataRCVRevenue(Integer.parseInt(value));
           setDataPieChart(Integer.parseInt(value));
        } else {

        }
        return view;
    }
    public void Reload(Bundle bundle){
        String thang= bundle.getString("thang","");
        String nam = bundle.getString("nam","");
        setDataPieChart(Integer.parseInt(nam));
        setDataRCVRevenue(Integer.parseInt(nam));
    }

    private void getIdCatalog(View view){
        ex = new ExpensesDAO(getContext());
        rcvCatalogYeah = view.findViewById(R.id.listThuReport_Year);
        pieChart = view.findViewById(R.id.pieChartRevenueYear);
    }
    public void setDataRCVRevenue(int yeah){
        rcvCatalogYeah.setLayoutManager(new LinearLayoutManager(getContext()));
        list = ex.getDataByRevenueyYeah(yeah);
        listItemCalenderADapter = new List_itemCalenderADapter(getContext(),list);
        rcvCatalogYeah.setAdapter(listItemCalenderADapter);
    }
    private void setDataPieChart(int year){
        HashMap<String, Integer> revenueByCategory = ex.getTotalRevenueByCtgYeah(year);
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