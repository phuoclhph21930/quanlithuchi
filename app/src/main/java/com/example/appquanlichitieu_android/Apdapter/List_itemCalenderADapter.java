package com.example.appquanlichitieu_android.Apdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.Activity.Update_DM_Thu;
import com.example.appquanlichitieu_android.DAO.ExpensesDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class List_itemCalenderADapter extends RecyclerView.Adapter<List_itemCalenderADapter.ViewHolder> {
    private Context context;
    private List<ExpensesModel> list_item;
    private ExpensesDAO expensesDAO;

    public List_itemCalenderADapter(Context context, List<ExpensesModel> list_item) {
        this.context = context;
        this.list_item = list_item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khoans, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpensesModel model = list_item.get(position);
        DecimalFormat formatter = new DecimalFormat("#,###");
        expensesDAO = new ExpensesDAO(context);

        int tien = model.getTienThu();


        holder.tvTongTien.setText(formatter.format(tien) + "đ");
        holder.tvGhiChu.setText("("+model.getGhiChu()+")");
        holder.tvDanhMuc.setText(model.getDanhMuc());
        holder.tvDate_item.setText(model.getNgay());
        if (model.getLoai().equals("KhoanChi")) {
            holder.tvGhiChu.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
            holder.tvDanhMuc.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
            holder.tvTongTien.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
        }
        if (model.getLoai().equals("KhoanThu")) {
            holder.tvGhiChu.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.blue));
            holder.tvTongTien.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.blue));
            holder.tvDanhMuc.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.blue));

        }

        Picasso.get().load(model.getImgThu()).into(holder.img_item);
        Log.e("adapater", model.getDanhMuc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ban da click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Update_DM_Thu.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("date", model.getNgay());
                intent.putExtra("danhmuc", model.getDanhMuc());
                intent.putExtra("tienthu", String.valueOf(model.getTienThu()));
                intent.putExtra("ghichu", model.getGhiChu());
                intent.putExtra("img", model.getImgThu());
                intent.putExtra("loai", model.getLoai());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thông báo xóa")
                        .setMessage("Bạn có muốn xóa bản ghi này?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(view.getContext(), "Bạn Hãy Nhập Đúng", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (expensesDAO.delete(model.getId()) > 0) {
                                    List<ExpensesModel> updatedData = expensesDAO.getAll(); // Thay yourSQLQuery bằng truy vấn SQL để lấy dữ liệu mới
                                    updateData(updatedData);
                                    holder.delete.setVisibility(View.GONE);
                                    Toast.makeText(context, "Bạn đã xóa danh mục " + model.getDanhMuc(), Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                builder.create().show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void updateData(List<ExpensesModel> newData) {
        list_item.clear();
        list_item.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_item;
        private TextView tvDanhMuc, tvGhiChu, tvTongTien, tvDate_item, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.item_image);
            tvDanhMuc = itemView.findViewById(R.id.item_ten_Danhmuc);
            tvGhiChu = itemView.findViewById(R.id.item_ghiChu);
            tvTongTien = itemView.findViewById(R.id.item_TienThuChi);
            delete = itemView.findViewById(R.id.deleteCalender);
            tvDate_item = itemView.findViewById(R.id.dateReport);

        }
    }
}
