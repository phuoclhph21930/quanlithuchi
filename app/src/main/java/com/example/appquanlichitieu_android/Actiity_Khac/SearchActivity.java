package com.example.appquanlichitieu_android.Actiity_Khac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.Apdapter.List_itemCalenderADapter;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.Fragment.CalendarFragment;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;

import java.text.DecimalFormat;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List_itemCalenderADapter listItemCalenderADapter;
    private EditText edtSearch;
    private TextView txtThu, txtChi, txtTong,back;
    private RecyclerView recyclerView;
    private ExpensesDAO expensesDAO;
    private ImageView imgback;
    private List<ExpensesModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getId();
        DecimalFormat formatter = new DecimalFormat("#,###");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list = expensesDAO.searchExpenses(charSequence.toString());
                listItemCalenderADapter = new List_itemCalenderADapter(getApplicationContext(),list);
                recyclerView.setAdapter(listItemCalenderADapter);
                int totalIncome = 0;
                int totalExpenses = 0;
                for (ExpensesModel expense : list) {
                    if (expense.getLoai() != null && expense.getLoai().equals("KhoanThu")) {
                        totalIncome += expense.getTienThu();
                    }

                    if (expense.getLoai() != null && expense.getLoai().equals("KhoanChi")) {
                        totalExpenses += expense.getTienThu();
                    }
                }

                if (list.size() > 0) {
                    txtThu.setText(String.valueOf(formatter.format(totalIncome) + "đ"));
                    txtChi.setText(String.valueOf(formatter.format(totalExpenses) + "đ"));
                    txtTong.setText(String.valueOf(formatter.format(totalIncome - totalExpenses) + "đ"));
                } else {
                    txtThu.setText("0đ");
                    txtChi.setText("0đ");
                    txtTong.setText("0đ");
                }
                if (totalIncome - totalExpenses>0){
                    txtTong.setTextColor(getColor(R.color.blue));
                }else{
                    txtTong.setTextColor(getColor(R.color.red));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void getId() {
        edtSearch = findViewById(R.id.edtSearch);
        recyclerView = findViewById(R.id.listSearch);
        txtChi = findViewById(R.id.txtChiSearch);
        txtThu = findViewById(R.id.txtThuSearch);
        txtTong = findViewById(R.id.txtTongSearch);
        imgback = findViewById(R.id.back_back);
        back = findViewById(R.id.backk);
        expensesDAO = new ExpensesDAO(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}