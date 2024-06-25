package com.example.appquanlichitieu_android.Apdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class List_item_Income_ADapter extends RecyclerView.Adapter<List_item_Income_ADapter.ViewHolder> {
    private Context context;
    private List<ExpensesModel> list_item;

    public List_item_Income_ADapter(Context context, List<ExpensesModel> list_item) {
        this.context = context;
        this.list_item = list_item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_khoans,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpensesModel model = list_item.get(position);



        holder.tvTongTien.setText(String.valueOf(model.getTienThu())+" đ");
        holder.tvGhiChu.setText(model.getGhiChu());
        holder.tvDanhMuc.setText(model.getDanhMuc());
        Picasso.get().load(model.getImgThu()).into(holder.img_item);
        Log.e("adapater",model.getDanhMuc());
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView img_item;
    private TextView tvDanhMuc,tvGhiChu,tvTongTien,tvDate_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.item_image);
            tvDanhMuc = itemView.findViewById(R.id.item_ten_Danhmuc);
            tvGhiChu = itemView.findViewById(R.id.item_ghiChu);
            tvTongTien = itemView.findViewById(R.id.item_TienThuChi);

        }
    }
}
