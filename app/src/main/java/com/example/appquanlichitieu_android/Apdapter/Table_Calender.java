package com.example.appquanlichitieu_android.Apdapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Table_Calender extends RecyclerView.Adapter<Table_Calender.ViewHolder> {
    private List<Date> dates;
    private ExpensesDAO expensesDAO;
    private String formattedDate = "";

    public Table_Calender(List<Date> dates) {
        this.dates = dates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date = dates.get(position);
        expensesDAO = new ExpensesDAO(holder.itemView.getContext());
        setDataCalender(holder,date);

        holder.bind(date);
    }
    private int currentYear;

    public void updateYear(int newYear) {
        this.currentYear = newYear;
        notifyDataSetChanged();
    }

    private String formatDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView, tienthu, tienchi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            tienchi = itemView.findViewById(R.id.tienchi);
            tienthu = itemView.findViewById(R.id.tien_thu);
        }

        public void bind(Date date) {
            // Set ngày vào TextView
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            String dateString = sdf.format(date);
            dateTextView.setText(dateString);
        }
    }
    public void setDataCalender(ViewHolder holder,Date date ){
        DecimalFormat formatter = new DecimalFormat("#,###");
        HashMap<String, Integer> revenueByDayMap = expensesDAO.getTotalRevenueByDayInMonth(currentYear);
        HashMap<String, Integer> ExpensesByDayMap = expensesDAO.getTotalExpensesByDayInMonth(currentYear);
// Lấy danh sách các khóa (ngày)
        List<String> dates1 = new ArrayList<>(revenueByDayMap.keySet());
        List<String> datesChi = new ArrayList<>(ExpensesByDayMap.keySet());
// Lấy danh sách các giá trị (tổng thu)
        String dateString = String.valueOf(date);
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            Date dateformart = inputFormat.parse(dateString);
            formattedDate = outputFormat.format(dateformart);
            System.out.println(formattedDate); // Output: 13/05/2024
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Integer> revenues = new ArrayList<>(revenueByDayMap.values());
        List<Integer> expenses = new ArrayList<>(ExpensesByDayMap.values());
        //Lặp qua danh sách các khóa và giá trị
        for (int i = 0; i < dates1.size(); i++) {
            String datee = dates1.get(i);
            int revenue = revenues.get(i);
            if (datee.equals(formattedDate)) {
                holder.tienthu.setText(formatter.format(revenue) + "đ");
            }
        }
        for (int i = 0; i < datesChi.size(); i++) {
            String datee = datesChi.get(i);
            int revenue = expenses.get(i);
            if (datee.equals(formattedDate)) {
                holder.tienchi.setText(formatter.format(revenue) + "đ");
            }
        }
    }
}
