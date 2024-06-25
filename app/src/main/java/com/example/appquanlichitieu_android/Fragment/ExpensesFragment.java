package com.example.appquanlichitieu_android.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
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
import com.example.appquanlichitieu_android.DAO.CatalogSpendDAO;
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.UpdateDMChi.Update_DM_Chi;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ExpensesFragment extends Fragment {
    private TextView txtDateThu, txtChinhSua;
    private EditText edtKhoanThu, edtGhiChuThu;
    private Button btnThu;
    private ExpensesDAO expensesDAO;
    private GridView gridViewThu;
    private GridAdapterChi gridAdapterThu;
    private List<catalogSpendModel> listCategory;
    private CatalogSpendDAO categoryAutumnDao;
    private String selectedCatalogThu = "";
    private String txtImgThu = "";
    private int selectedItemPosition = -1;
    int numberOfDaysToSubtract = 1;
    private ImageView imgNextDay, imgyesterday;
    private DecimalFormat decimalFormat;
    private int parsed;
    private Boolean click = false;
    List<catalogSpendModel> updatedData;
    private static final int REQUEST_CODE = 123;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        getIdLayout(view);
        date();
        LoadData_Thu();
        decimalFormat = new DecimalFormat("#,##0");
        updatedData = categoryAutumnDao.getAll(); // Thay yourSQLQuery bằng truy vấn SQL để lấy dữ liệu mới
        updateData(updatedData);
        edtKhoanThu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                edtKhoanThu.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();

                    String cleanString = originalString.replaceAll(",", "");
                    parsed = Integer.parseInt(cleanString);
                    String formatted = decimalFormat.format(parsed);
                    edtKhoanThu.setText(formatted);
                    edtKhoanThu.setSelection(formatted.length());
                } catch (NumberFormatException e) {
                }

                edtKhoanThu.addTextChangedListener(this);
            }
        });

        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == false) {
                    Toast.makeText(getContext(), "ban chua chon bieu tuong", Toast.LENGTH_SHORT).show();
                }
                if (click == true) {
                    clickBtnThu();
                }

            }
        });

        txtChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Update_DM_Chi.class);
                activityResultLauncher.launch(intent);
            }
        });

        imgNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = txtDateThu.getText().toString();
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
                txtDateThu.setText(newDate);
            }
        });

        imgyesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày hiện tại từ txtDateThu
                String selectedDate = txtDateThu.getText().toString();
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
                txtDateThu.setText(newDate);
            }
        });

        txtDateThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        gridViewThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                catalogSpendModel selectedItem = (catalogSpendModel) adapterView.getItemAtPosition(i);
                selectedCatalogThu = selectedItem.getCatalog();
                txtImgThu = selectedItem.getImageSpend();

                if (selectedItemPosition != -1) {
                    View previousSelectedView = gridViewThu.getChildAt(selectedItemPosition);
                    if (previousSelectedView != null) {
                        previousSelectedView.setBackgroundResource(R.drawable.boder_danhmuc);
                    }
                }
                selectedItemPosition = i;
                view.setBackgroundResource(R.drawable.click_item);
                click = true;// Thay đổi màu sắc tùy ý
            }
        });


        return view;
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Xử lý kết quả từ Activity
                    updateData(updatedData);
                }
            });

    // lấy id layout
    public void getIdLayout(View view) {
        txtDateThu = view.findViewById(R.id.DateChi);
        edtGhiChuThu = view.findViewById(R.id.EdtGhiChuChi);
        edtKhoanThu = view.findViewById(R.id.edtTienchi);
        btnThu = view.findViewById(R.id.btnThu);
        expensesDAO = new ExpensesDAO(getContext());
        gridViewThu = view.findViewById(R.id.gridDanhmucThu);
        categoryAutumnDao = new CatalogSpendDAO(getContext());
        txtChinhSua = view.findViewById(R.id.insert_Update);
        imgyesterday = view.findViewById(R.id.yesterday_exp);
        imgNextDay = view.findViewById(R.id.nextDay_exp);
    }


    //Data danh muc thu
    public void LoadData_Thu() {
        listCategory = categoryAutumnDao.getAll();
        gridAdapterThu = new GridAdapterChi(getContext(), listCategory);
        gridViewThu.setAdapter(gridAdapterThu);

    }
    //lấy ngày

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
                        txtDateThu.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    public void updateData(List<catalogSpendModel> newData) {
        listCategory.clear();
        listCategory.addAll(newData);

    }

    public void date() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngayThangNam = dateFormat.format(calendar.getTime());
        txtDateThu.setText(ngayThangNam);
    }

    // Nhập khoản thu
    public void clickBtnThu() {
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
                        String date = txtDateThu.getText().toString();
                        // Xử lý sự kiện khi người dùng nhấn nút OK
                        String ghiChu = edtGhiChuThu.getText().toString();
                        String TienChi = edtKhoanThu.getText().toString();
                        String ngay = date;

                        String DanhMuc = selectedCatalogThu;
                        ExpensesModel revenue = new ExpensesModel();
                        revenue.ngay = ngay;
                        revenue.ghiChu = ghiChu;
                        revenue.tienThu = parsed;
                        revenue.danhMuc = DanhMuc;
                        revenue.imgThu = txtImgThu;
                        revenue.loai = "KhoanChi";
                        if (expensesDAO.insert(revenue) > 0) {
                            Toast.makeText(getContext(), "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();
                            edtGhiChuThu.setText("");
                            edtKhoanThu.setText("");
                        }

                    }
                });
        builder.create().show();

    }

}