package com.example.appquanlichitieu_android.UpdateDMChi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.Activity.Update_iconDM;
import com.example.appquanlichitieu_android.DAO.CatalogSpendDAO;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ImageChi_DAO;
import com.example.appquanlichitieu_android.DAO.ImageThu_DAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.Update_Icon_Chi_Adapter.Adapter_ImageChi;
import com.example.appquanlichitieu_android.Update_icon_DMThhu_Adapter.Adapter_ImageThu;
import com.example.appquanlichitieu_android.Update_icon_DMThhu_Adapter.Insert_icon_DMThu_Adapter;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;
import com.example.appquanlichitieu_android.modelQuanLi.imageChi_model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;

import java.util.List;

public class Update_icon_DMChi extends AppCompatActivity {
    private ImageView imgUpdateDM,backUpdate;
    private EditText edtNameDM;
    private GridView gridUpdateDM;
    private List<imageThu_Model> list;
    private ImageThu_DAO imageThuDao;
    private Button btnUpdateDM;
    private Insert_icon_DMThu_Adapter adapterImageChi;
    private String txtImgThu = "";
    private int selectedItemPosition = -1;
    private CatalogSpendDAO catalogSpendDAO;
    private catalogSpendModel spendModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_icon_dmchi);
        edtNameDM = findViewById(R.id.nameUpdateDMChi);
        gridUpdateDM = findViewById(R.id.gridDanhmucThu_UPDATE_Chi);
        btnUpdateDM= findViewById(R.id.btnUpdateDM_Chi);
        backUpdate = findViewById(R.id.backUpdateChi);
        imageThuDao = new ImageThu_DAO(this);
        catalogSpendDAO = new CatalogSpendDAO(this);
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("itemId", 0); // Lấy giá trị itemId, nếu không tìm thấy thì mặc định là 0
        String image = intent.getStringExtra("image"); // Lấy giá trị image
        String category = intent.getStringExtra("category"); // Lấy giá trị category

        //hien thi icon danh muc
        list = imageThuDao.getAll();
        adapterImageChi= new Insert_icon_DMThu_Adapter(Update_icon_DMChi.this,list);
        gridUpdateDM.setAdapter(adapterImageChi);
        edtNameDM.setText(category);
        //lấy dữ liệu từ db đổ lên gridview icon ảnh cho danh mục
        gridUpdateDM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageThu_Model selectedItem = (imageThu_Model) adapterView.getItemAtPosition(i);
                txtImgThu = selectedItem.getImageDM();
                if (selectedItemPosition != -1) {
                    View previousSelectedView = gridUpdateDM.getChildAt(selectedItemPosition);
                    if (previousSelectedView != null) {
                        previousSelectedView.setBackgroundResource(R.drawable.boder_danhmuc);
                    }
                }
                selectedItemPosition = i;
                view.setBackgroundResource(R.drawable.click_item);
            }
        });
        backUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        //chỉnh sửa lại icon và tên danh mục cho khoản chi

        btnUpdateDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name  =edtNameDM.getText().toString();
                spendModel = new catalogSpendModel();
                spendModel.setIdCatalog(itemId);
                spendModel.setImageSpend(txtImgThu);
                spendModel.setCatalog(name);
                catalogSpendDAO.update(spendModel);
                if (catalogSpendDAO.update(spendModel)>0){
                    Toast.makeText(Update_icon_DMChi.this, "Bạn đã sửa danh mục thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}