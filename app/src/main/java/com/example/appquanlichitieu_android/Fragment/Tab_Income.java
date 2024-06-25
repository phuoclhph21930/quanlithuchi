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
import com.example.appquanlichitieu_android.Apdapter.List_item_Income_ADapter;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Income#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Income extends Fragment {
    private RecyclerView list_TabIncome;
    private ExpensesDAO expensesDAO;
    private List_item_Income_ADapter incomeADapter;
    private List<ExpensesModel> list;
    private PieChart pieChart;
    private List_itemCalenderADapter listItemCalenderADapter;

    public static Tab_Income newInstance(String param1, String param2) {
        Tab_Income fragment = new Tab_Income();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab__income, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        expensesDAO = new ExpensesDAO(getContext());
        list_TabIncome = view.findViewById(R.id.list_Tab_Income);

        if (getArguments() != null) {
            String value = getArguments().getString("YYYYreport");
            String month = getArguments().getString("MMreport");
            LoadData(Integer.parseInt(month),Integer.parseInt(value));
            LoadPieChartData(Integer.parseInt(month),Integer.parseInt(value));
        } else {

        }

        return view;
    }
    public void Reload(Bundle bundle){
        String thang= bundle.getString("thang","");
        String nam = bundle.getString("nam","");
        LoadData(Integer.parseInt(thang),Integer.parseInt(nam));
        LoadPieChartData(Integer.parseInt(thang),Integer.parseInt(nam));
    }
    public void LoadData(int month,int year){
        list_TabIncome.setLayoutManager(new LinearLayoutManager(getContext()));
        list = expensesDAO.getDataByMonthAndRevuene(month,year);
        listItemCalenderADapter = new List_itemCalenderADapter(getContext(),list);
        list_TabIncome.setAdapter(listItemCalenderADapter);
    }
    public void LoadPieChartData(int month,int year){
        HashMap<String, Integer> revenueByCategory = expensesDAO.getTotalRevenueByCategory(month,year);
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