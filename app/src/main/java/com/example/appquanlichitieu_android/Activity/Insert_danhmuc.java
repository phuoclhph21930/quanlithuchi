package com.example.appquanlichitieu_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.Update_icon_DMThhu_Adapter.Insert_icon_DMThu_Adapter;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ImageThu_DAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;

import java.util.List;

public class Insert_danhmuc extends AppCompatActivity {
    private GridView viewInsert;
    private EditText edtInsert;
    private Button btnInsert;
    private ImageView backInsert;
    private Category_Autumn_DAO autumnDao;
    private Category_Autumn_Model categoryAutumnModel;
    private ImageThu_DAO imageThuDao;
    private List<imageThu_Model> list;
    private Insert_icon_DMThu_Adapter imageDmThuAdapter;
    private String ImgThu = "";
    private int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_danhmuc);
        viewInsert = findViewById(R.id.viewInsert);
        edtInsert = findViewById(R.id.edtInsert);
        btnInsert = findViewById(R.id.btnInsert);
        backInsert = findViewById(R.id.back_insert);
        autumnDao = new Category_Autumn_DAO(this);
        imageThuDao = new ImageThu_DAO(this);
        list = imageThuDao.getAll();
        imageDmThuAdapter = new Insert_icon_DMThu_Adapter(this,list);
        viewInsert.setAdapter(imageDmThuAdapter);

        viewInsert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageThu_Model selectedItem = (imageThu_Model) adapterView.getItemAtPosition(i);
                ImgThu = selectedItem.getImageDM();
                if (selectedItemPosition != -1) {
                    View previousSelectedView = viewInsert.getChildAt(selectedItemPosition);
                    if (previousSelectedView != null) {
                        previousSelectedView.setBackgroundResource(R.drawable.boder_danhmuc);
                    }
                }
                selectedItemPosition = i;
                view.setBackgroundResource(R.drawable.click_item);

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDanhmuc= edtInsert.getText().toString();
                categoryAutumnModel= new Category_Autumn_Model(ImgThu,tenDanhmuc);
                if (autumnDao.insert(categoryAutumnModel)>0){
                    edtInsert.setText("");

                    Toast.makeText(Insert_danhmuc.this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }
}