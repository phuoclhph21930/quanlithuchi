package com.example.appquanlichitieu_android.tab_ReportYeah;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.Actiity_Khac.Catalog_Yeah_Activity;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Revenue_ReportCatalog_YeahFragment extends Fragment {
    private TextView txtTong;
    private RecyclerView Rsvthu;
    private PieChart pieChart;
    private ExpensesDAO ex;

    private List_itemCalenderADapter listItemCalenderADapter;
    private List<ExpensesModel> list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Catalog_Yeah_Activity) {
            ((Catalog_Yeah_Activity) context).setOncl((year) -> {
                LoadDataRCV(year);
                setDataPieChart(year);
                txtTong.setText(ex.getTotalRevenueYeah(year)+"đ");
                ((Catalog_Yeah_Activity) context).updateFragmentDataRevenue(year);
            });
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_revenue_catalog_yeah, container, false);
        getidThu(view);
        LoadDataRCV(2024);
        setDataPieChart(2024);
        txtTong.setText(ex.getTotalRevenueYeah(2024)+"đ");
        // Inflate the layout for this fragment
        return view;
    }
    private void getidThu(View view){
        ex = new ExpensesDAO(getContext());

        txtTong = view.findViewById(R.id.tongThuCatalog);
        pieChart = view.findViewById(R.id.pieChartRevenue_Catalog);
        Rsvthu = view.findViewById(R.id.listThuReport_Catalog);
    }
    public void LoadDataRCV(int year){
        Rsvthu.setLayoutManager(new LinearLayoutManager(getContext()));
        list = ex.getDataByRevenueyYeah(year);
        listItemCalenderADapter = new List_itemCalenderADapter(getContext(),list);
        Rsvthu.setAdapter(listItemCalenderADapter);

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