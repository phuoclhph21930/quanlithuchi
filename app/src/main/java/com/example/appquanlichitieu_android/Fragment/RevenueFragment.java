package com.example.appquanlichitieu_android.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu_android.Activity.Update;
import com.example.appquanlichitieu_android.Apdapter.GridAdapterChi;
import com.example.appquanlichitieu_android.Apdapter.GridAdapterThu;
import com.example.appquanlichitieu_android.Apdapter.List_itemCalenderADapter;
import com.example.appquanlichitieu_android.DAO.CatalogSpendDAO;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.DAO.RevenueDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.UpdateDMChi.Update_DM_Chi;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class RevenueFragment extends Fragment {
    private EditText edtGhiChu,edtTienChi;
    private TextView tvDateChi,txtUpdate;
    private ImageView imgTang_Date,imgGiam_Date;
   private Button btnNhapChi;
   private ExpensesDAO revenueDAO;
   private GridView gridViewChi;
   private Category_Autumn_DAO catalogSpendDAO;
    private List<Category_Autumn_Model> list;
    private GridAdapterThu gridAdapterChi;
    private String selectedCatalog = "";
    private String imgChis = "";
    private int selectedItemPosition = -1;
    private DecimalFormat decimalFormat;
    int parsed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_revenue, container, false);
        getIdLayout(view);date();
        LoadData();
        decimalFormat = new DecimalFormat("#,##0");

        imgGiam_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = tvDateChi.getText().toString();
                String[] parts = selectedDate.split("/");
                int dayOfMonth = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]) - 1;
                int year = Integer.parseInt(parts[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                String newDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
                tvDateChi.setText(newDate);
            }
        });
        imgTang_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = tvDateChi.getText().toString();
                String[] parts = selectedDate.split("/");
                int dayOfMonth = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]) - 1;
                int year = Integer.parseInt(parts[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                String newDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
                tvDateChi.setText(newDate);
            }
        });
        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Update.class);
                activityResultLauncher.launch(intent);
            }
        });
        edtTienChi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                edtTienChi.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();
                    String cleanString = originalString.replaceAll(",", "");
                    parsed = Integer.parseInt(cleanString);

                    String formatted = decimalFormat.format(parsed);
                    edtTienChi.setText(formatted);
                    edtTienChi.setSelection(formatted.length());
                } catch (NumberFormatException e) {
                    // Xử lí ngoại lệ khi không thể chuyển đổi thành số
                }

                edtTienChi.addTextChangedListener(this);
            }
        });
        tvDateChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


        btnNhapChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRevenue();
            }
        });
        gridViewChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category_Autumn_Model selectedItem = (Category_Autumn_Model) adapterView.getItemAtPosition(i);
                 selectedCatalog = selectedItem.getDanhmucThu();
                 imgChis = selectedItem.getImageThu();
                if (selectedItemPosition != -1) {
                    View previousSelectedView = gridViewChi.getChildAt(selectedItemPosition);
                    if (previousSelectedView != null) {
                        previousSelectedView.setBackgroundResource(R.drawable.boder_danhmuc);
                    }
                }
                selectedItemPosition = i;
                view.setBackgroundResource(R.drawable.click_item);
            }
        });



        return view;
    }
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Xử lý kết quả từ Activity
                    LoadData();
                }
            });

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
                        String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        tvDateChi.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
    public void insertRevenue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo")
                .setMessage("Bạn đã xác nhận đúng thông tin?")
                .setNegativeButton("Ghi Lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Bạn Hãy Nhập Đúng", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Ghi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = tvDateChi.getText().toString();
                        // Xử lý sự kiện khi người dùng nhấn nút OK
                        String ghiChu = edtGhiChu.getText().toString();
                        String TienChi = edtTienChi.getText().toString();
                        String ngay = date;
                        String DanhMuc = selectedCatalog;
                        ExpensesModel revenue = new ExpensesModel();
                        revenue.ngay = ngay;
                        revenue.ghiChu = ghiChu;
                        revenue.tienThu = parsed;
                        revenue.danhMuc = DanhMuc;
                        revenue.imgThu = imgChis;
                        revenue.loai = "KhoanThu";
                        if(revenueDAO.insert(revenue)>0){
                            edtGhiChu.setText("");
                            edtTienChi.setText("");
                        }
                    }
                });
        builder.create().show();
    }
    public void getIdLayout(View view){
        edtGhiChu = view.findViewById(R.id.EdtGhiChuChi);
        edtTienChi = view.findViewById(R.id.edtTienchi);
        btnNhapChi = view.findViewById(R.id.btnNhapChi);
        gridViewChi = view.findViewById(R.id.gridDanhmucChi);
        tvDateChi = view.findViewById(R.id.DateChi);
        txtUpdate = view.findViewById(R.id.chinhsuaChi);
        imgTang_Date = view.findViewById(R.id.tangNgay);
        imgGiam_Date = view.findViewById(R.id.giamNgay);
        revenueDAO = new ExpensesDAO(getContext());
        catalogSpendDAO = new Category_Autumn_DAO(getContext());
        list = new ArrayList<>();


    }
    public void LoadData(){
        list = catalogSpendDAO.getAll();
        gridAdapterChi = new GridAdapterThu(getContext(),list);
        gridViewChi.setAdapter(gridAdapterChi);
    }
    public void date(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        tvDateChi.setText(ngayThangNam);

    }

}