package com.example.appquanlichitieu_android.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.Actiity_Khac.SearchActivity;
import com.example.appquanlichitieu_android.Apdapter.List_itemCalenderADapter;
import com.example.appquanlichitieu_android.Apdapter.Table_Calender;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.DAO.RevenueDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class CalendarFragment extends Fragment {

    private TableLayout tableLayout;
    private TextView tvTongThu, dateCalender, tvTongChi, tvTong;
    private RevenueDAO revenueDAO;
    private List<ExpensesModel> modelList;
    private List<ExpensesModel> listThu;
    private List<RevenueModel> list_item;
    private List<ExpensesModel> listEx_item;
    private ExpensesDAO expensesDAO;
    private RecyclerView list_khoan;
    private ImageView dateQua, nextDay, imgsearch,imgback;
    private List_itemCalenderADapter calenderADapter;
    private RecyclerView recyclerView;
    private Table_Calender adapter;
    private List<Date> dates;
    private int month, year;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        getid_New(view);


        // set thang nam cho date
        setdate();
        //set ngay cho thang tren table
        setDateCelender();
        //load data len view
        LoadData(month, year);
        //set tien thu chi
        SetDolar(month, year);
        // Lấy ra ngày của tháng và năm mới
        dates = getDates(month, year);

        // Cập nhật lại adapter và RecyclerView
        adapter = new Table_Calender(dates);
        recyclerView.setAdapter(adapter);
        updateData(month, year);
        SetDolar(month, year);
        adapter.updateYear(month);
        adapter.notifyDataSetChanged();




        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dateQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = dateCalender.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("/")[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("/")[1]));
                calendar.add(Calendar.MONTH, -1);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                String newDate = String.format(Locale.getDefault(), "%02d/%04d", month, year);
                dateCalender.setText(newDate);
                // Lấy ra ngày của tháng và năm mới
                dates = getDates(month, year);

                // Cập nhật lại adapter và RecyclerView
                adapter = new Table_Calender(dates);
                recyclerView.setAdapter(adapter);
                updateData(month, year);
                SetDolar(month, year);
                adapter.updateYear(month);
                adapter.notifyDataSetChanged();
            }
        });
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = dateCalender.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Integer.parseInt(selectedDate.split("/")[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(selectedDate.split("/")[1]));
                calendar.add(Calendar.MONTH, 1);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                String newDate = String.format(Locale.getDefault(), "%02d/%04d", month, year);
                dateCalender.setText(newDate);
                dates = getDates(month, year);
                adapter = new Table_Calender(dates);
                recyclerView.setAdapter(adapter);
                SetDolar(month, year);
                updateData(month, year);
                adapter.updateYear(month);
                adapter.notifyDataSetChanged();
            }
        });
        dateCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        return view;
    }

    private List<Date> getCalendarDates() {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < daysInMonth; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    public void setdate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        dateCalender.setText(ngayThangNam);
        month = Integer.parseInt(ngayThangNam.substring(0, 2));
        year = Integer.parseInt(ngayThangNam.substring(3, 7));
    }

    public void setDateCelender() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        dates = getCalendarDates();
        adapter = new Table_Calender(dates);
        recyclerView.setAdapter(adapter);
    }

    private List<Date> getDates(int month, int year) {
        List<Date> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < daysInMonth; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dates;
    }

    public void updateData(int month, int year) {
        listEx_item.clear();
        listEx_item = expensesDAO.getDataByMonth(month, year);
        calenderADapter = new List_itemCalenderADapter(getContext(), listEx_item);
        list_khoan.setAdapter(calenderADapter);
        calenderADapter.notifyDataSetChanged();
        SetDolar(month, year);
    }


    public void getid_New(View view) {
        recyclerView = view.findViewById(R.id.reDate);
        tvTongChi = view.findViewById(R.id.TongChi);
        dateCalender = view.findViewById(R.id.dateCalender);
        tvTong = view.findViewById(R.id.Tong);
        tvTongThu = view.findViewById(R.id.TongThu);
        list_khoan = view.findViewById(R.id.list_thu_chi);
        imgback = view.findViewById(R.id.back_insert);
        expensesDAO = new ExpensesDAO(getContext());
        list_item = new ArrayList<>();
        dateQua = view.findViewById(R.id.yesterday);
        nextDay = view.findViewById(R.id.nextDay);
        imgsearch = view.findViewById(R.id.searchCelender);
        listThu = new ArrayList<>();
        modelList = new ArrayList<>();
    }

    public void LoadData(int month, int Yeah) {
        list_khoan.setLayoutManager(new LinearLayoutManager(getContext()));
        listEx_item = expensesDAO.getDataByMonth(month, Yeah);
        calenderADapter = new List_itemCalenderADapter(getContext(), listEx_item);
        list_khoan.setAdapter(calenderADapter);
        calenderADapter.notifyDataSetChanged();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi ngày được chọn
                        String selectedDate = String.format(Locale.getDefault(), "%02d/%04d", month + 1, year);
                        SetDolar(month, year);
                        dateCalender.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }


    public void SetDolar(int month, int year) {
        DecimalFormat formatter = new DecimalFormat("#,###");

        int khoanChi = expensesDAO.getTotalExpensesForMonth(month, year);
        tvTongChi.setText(formatter.format(khoanChi) + "đ");

        Log.e("data", "month" + expensesDAO.getTotalExpensesMonth(2024));
        int tongThu = expensesDAO.getTotalRevenueForMonth(month, year);

        tvTongThu.setText(formatter.format(tongThu) + "đ");

        int Tong = tongThu - khoanChi;
        tvTong.setText(formatter.format(Tong) + "đ");
        if (Tong > 0) {
            tvTong.setTextColor(getResources().getColor(R.color.blue));
        } else {
            tvTong.setTextColor(getResources().getColor(R.color.red));
        }
    }

}