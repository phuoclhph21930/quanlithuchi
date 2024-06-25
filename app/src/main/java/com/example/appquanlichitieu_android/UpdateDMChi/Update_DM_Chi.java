package com.example.appquanlichitieu_android.UpdateDMChi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.Activity.Insert_danhmuc;
import com.example.appquanlichitieu_android.Update_Icon_Chi_Adapter.Update_Icon_DM_Chi_ADapter;
import com.example.appquanlichitieu_android.DAO.CatalogSpendDAO;
import com.example.appquanlichitieu_android.Fragment.RevenueFragment;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;

import java.util.List;
import java.util.Objects;

public class Update_DM_Chi extends AppCompatActivity {
    private RecyclerView view_item;
    private List<catalogSpendModel> autumn_modelList;
    private CatalogSpendDAO dao;
    private Update_Icon_DM_Chi_ADapter dmADapter;
    private ImageView btnInsert,imgBack;
    private static final int REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        view_item = findViewById(R.id.list_itemExpensesUpdate);
        btnInsert = findViewById(R.id.btnInsertDanhmuc);
        imgBack = findViewById(R.id.back_Update);
        Load_data();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_DM_Chi.this, Insert_icon_DMChi.class);
                startActivity(intent);
            }
        });
    }



    public void Load_data(){
        view_item.setLayoutManager(new LinearLayoutManager(this));
        dao = new CatalogSpendDAO(this);
        autumn_modelList = dao.getAll();
        dmADapter = new Update_Icon_DM_Chi_ADapter(this,autumn_modelList);
        view_item.setAdapter(dmADapter);
    }

}