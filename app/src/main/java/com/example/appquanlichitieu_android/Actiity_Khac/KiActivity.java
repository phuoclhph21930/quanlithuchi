package com.example.appquanlichitieu_android.Actiity_Khac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.Fragment.SettingFragment;
import com.example.appquanlichitieu_android.R;

import java.text.DecimalFormat;
import java.util.Set;

public class KiActivity extends AppCompatActivity {

    private TextView txtChi,txtThu,txtTong;
    private ImageView imgback;
    private ExpensesDAO ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ki);
        DecimalFormat formatter = new DecimalFormat("#,###");
        txtChi = findViewById(R.id.ki_Chi);
        imgback = findViewById(R.id.back_ki);
        txtThu = findViewById(R.id.khoan_Thu_Ki);
        txtTong = findViewById(R.id.Tong_Ki);
        ex = new ExpensesDAO(this);
        txtThu.setText(String.valueOf(formatter.format(ex.getTotalRevenue()) + "đ"));
        txtChi.setText(String.valueOf(formatter.format(ex.getTotalExpenses()) + "đ"));
//        int tong = ex.getTotalIncomeByType()- ex.getTotalExpensesByType();
        txtTong.setText(String.valueOf(formatter.format(ex.getTotalRevenue()-ex.getTotalExpenses())+"đ"));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}