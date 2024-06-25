package com.example.appquanlichitieu_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.Apdapter.GridAdapterThu;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;

import java.text.DecimalFormat;
import java.util.List;

public class Update_DM_Thu extends AppCompatActivity {
    private TextView date_Update,headerKhoan,txtTien;
    private EditText edt_ghichu_Update,edttien_Update;
    private GridView gridView_Update;
    private Button btnUpdate_thu;
    private String selectedCatalogThu = "";
    private String txtImgThu = "";
    private int selectedItemPosition = -1;
    private List<Category_Autumn_Model> listCategory;
    private Category_Autumn_DAO categoryAutumnDao;
    private GridAdapterThu gridAdapterThu;
    private ExpensesModel expensesModel;
    private ExpensesDAO expensesDAO;
    private int parsed;
    private DecimalFormat decimalFormat;
    private ImageView imgback;
    private DecimalFormat formatter;
    private String loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dm_thu);
        getidAndNew();

        getBundel();
        setDataGridView();


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });
        edttien_Update.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                edttien_Update.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();
                    String cleanString = originalString.replaceAll(",", "");
                    parsed = Integer.parseInt(cleanString);
                    String formatted = decimalFormat.format(parsed);
                    edttien_Update.setText(formatted);
                    edttien_Update.setSelection(formatted.length());
                } catch (NumberFormatException e) {
                }

                edttien_Update.addTextChangedListener(this);
            }
        });



        gridView_Update.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category_Autumn_Model selectedItem = (Category_Autumn_Model) adapterView.getItemAtPosition(i);
                selectedCatalogThu = selectedItem.getDanhmucThu();
                txtImgThu = selectedItem.getImageThu();
                Toast.makeText(Update_DM_Thu.this, selectedCatalogThu, Toast.LENGTH_SHORT).show();
                if (selectedItemPosition != -1) {
                    View previousSelectedView = gridView_Update.getChildAt(selectedItemPosition);
                    if (previousSelectedView != null) {
                        previousSelectedView.setBackgroundResource(R.drawable.boder_danhmuc);
                    }
                }
                selectedItemPosition = i;
                view.setBackgroundResource(R.drawable.click_item);
            }
        });
        btnUpdate_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ghiChu = edt_ghichu_Update.getText().toString();
                String tienThu = edttien_Update.getText().toString();
                String date = date_Update.getText().toString();
                expensesModel.setNgay(date);
                expensesModel.setDanhMuc(selectedCatalogThu);
                expensesModel.setImgThu(txtImgThu);
                expensesModel.setGhiChu(ghiChu);
                expensesModel.setTienThu(Integer.parseInt(tienThu));
                expensesModel.setLoai(loai);
                if(expensesDAO.update(expensesModel)>0){
                    edt_ghichu_Update.setText("");
                    edttien_Update.setText("");
                    Toast.makeText(Update_DM_Thu.this, "Bạn đã chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void getBundel(){
        Intent intent = getIntent();
        int Id = intent.getIntExtra("id", 0); // Lấy giá trị itemId, nếu không tìm thấy thì mặc định là 0
        String image = intent.getStringExtra("img"); // Lấy giá trị image
        String danhmuc = intent.getStringExtra("danhmuc"); // Lấy giá trị category
        String tienthu = intent.getStringExtra("tienthu");
        String date = intent.getStringExtra("date");
        String ghichu = intent.getStringExtra("ghichu");
         loai = intent.getStringExtra("loai");
        int dolar = Integer.valueOf(tienthu);
        date_Update.setText(date);
        edt_ghichu_Update.setText(ghichu);
        edttien_Update.setText(String.valueOf(formatter.format(dolar)));
        if(loai.equals("KhoanChi")){
            headerKhoan.setText("Chỉnh sửa Khoản chi");
            txtTien.setText("Tiền chi");
        }
        if (loai.equals("KhoanThu")){
            headerKhoan.setText("Chỉnh sửa khoản thu");
            txtTien.setText("Tiền thu");
        }
    }
    public void setDataGridView(){
        categoryAutumnDao = new Category_Autumn_DAO(this);
        listCategory = categoryAutumnDao.getAll();
        gridAdapterThu = new GridAdapterThu(this,listCategory);
        gridView_Update.setAdapter(gridAdapterThu);
    }
    public void getidAndNew(){
        date_Update =  findViewById(R.id.dateThu_Update);
        edt_ghichu_Update = findViewById(R.id.edtGhiChuThu);
        edttien_Update = findViewById(R.id.EdtTienthu_Update);
        btnUpdate_thu = findViewById(R.id.btnThu_Update);
        gridView_Update = findViewById(R.id.gridDanhmucThu_Update);
        imgback = findViewById(R.id.back_insert);
        headerKhoan = findViewById(R.id.headerKhoan);
        txtTien = findViewById(R.id.txttien);
        expensesDAO = new ExpensesDAO(this);
        expensesModel = new ExpensesModel();
        decimalFormat = new DecimalFormat("#,##0");
         formatter   = new DecimalFormat("#,###");
    }

}