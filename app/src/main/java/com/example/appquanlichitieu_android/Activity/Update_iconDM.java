package com.example.appquanlichitieu_android.Activity;

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

import com.example.appquanlichitieu_android.Update_icon_DMThhu_Adapter.Adapter_ImageThu;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ImageThu_DAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;

import java.util.List;

public class Update_iconDM extends AppCompatActivity {
    private ImageView imgUpdateDM,backUpdate;
    private EditText edtNameDM;
    private GridView gridUpdateDM;
    private List<imageThu_Model> list;
    private ImageThu_DAO imageThuDao;
    private Button btnUpdateDM;
    private Adapter_ImageThu adapterImageThu;
    private String selectedCatalogThu = "";
    private String txtImgThu = "";
    private int selectedItemPosition = -1;
    private Category_Autumn_DAO autumnDao;
    private Category_Autumn_Model autumnModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_icon_dm);
        edtNameDM = findViewById(R.id.nameUpdateDM);
        gridUpdateDM = findViewById(R.id.gridDanhmucThu_UPDATE);
        btnUpdateDM= findViewById(R.id.btnUpdateDM);
        imageThuDao = new ImageThu_DAO(this);
        autumnDao = new Category_Autumn_DAO(this);
        backUpdate = findViewById(R.id.back_insert);

        //lấy dữ liệu từ adapter
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("itemId", 0); // Lấy giá trị itemId, nếu không tìm thấy thì mặc định là 0
        String image = intent.getStringExtra("image"); // Lấy giá trị image
        String category = intent.getStringExtra("category"); // Lấy giá trị category

        //hien thi icon danh muc
        list = imageThuDao.getAll();
        adapterImageThu = new Adapter_ImageThu(Update_iconDM.this,list);
        gridUpdateDM.setAdapter(adapterImageThu);
        edtNameDM.setText(category);
        Log.e("du lieu image",""+list.get(0).getImageDM());

        backUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
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
        btnUpdateDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name  =edtNameDM.getText().toString();


                autumnModel = new Category_Autumn_Model();
                autumnModel.setIdCatalogThu(itemId); // ID của danh mục cần cập nhật
                autumnModel.setImageThu(txtImgThu); // Giá trị mới cho cột "imageThu"
                autumnModel.setDanhmucThu(name); // Giá trị mới cho cột "DanhmucThu"
                if (autumnDao.update(autumnModel)>0){
                    edtNameDM.setText("");
                    Toast.makeText(Update_iconDM.this, "Bạn đã sửa danh mục thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}