package com.example.appquanlichitieu_android.Apdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapterChi extends BaseAdapter {
    private int selectedItemPosition = -1;
    public GridAdapterChi(Context context, List<catalogSpendModel> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<catalogSpendModel> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vieww = view;

        if (vieww == null) {
            vieww = LayoutInflater.from(context).inflate(R.layout.item_revenue_chi, viewGroup, false);
        }

        catalogSpendModel revenueModel = list.get(i);
        TextView txtChi = vieww.findViewById(R.id.txtrevenue);
        ImageView imgChi = vieww.findViewById(R.id.imgRevenue);

        txtChi.setText(revenueModel.getCatalog());
       /* if (selectedItemPosition == i) {
            // Item được chọn, đặt màu sắc khác
            vieww.setBackgroundColor(Color.RED); // Thay đổi màu sắc tùy ý
        } else {
            // Item không được chọn, đặt màu sắc mặc định
            vieww.setBackgroundColor(Color.WHITE); // Thay đổi màu sắc tùy ý
        }*/

         Picasso.get().load(revenueModel.getImageSpend()).into(imgChi);

        return vieww;
    }
    private static class ViewHolder {
        ImageView imgChi;
        TextView txtChi;
    }
}
