package com.example.appquanlichitieu_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appquanlichitieu_android.Apdapter.DM_Thu_ADapter;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.Fragment.ExpensesFragment;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;

import java.util.List;

public class Update extends AppCompatActivity {
    private RecyclerView view_item;
    private List<Category_Autumn_Model> autumn_modelList;
    private Category_Autumn_DAO dao;
    private DM_Thu_ADapter dmADapter;
    private ImageView btnInsert,imgBack;
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

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update.this, Insert_danhmuc.class);
                startActivity(intent);
            }
        });




    }
    public void Load_data(){
        view_item.setLayoutManager(new LinearLayoutManager(this));
        dao = new Category_Autumn_DAO(this);
        autumn_modelList = dao.getAll();
        dmADapter = new DM_Thu_ADapter(this,autumn_modelList);
        view_item.setAdapter(dmADapter);
    }

}