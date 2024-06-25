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
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapterThu extends BaseAdapter {
    private int selectedItemPosition = -1;
    public GridAdapterThu(Context context, List<Category_Autumn_Model> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<Category_Autumn_Model> list;

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

        Category_Autumn_Model revenueModel = list.get(i);
        TextView txtChi = vieww.findViewById(R.id.txtrevenue);
        ImageView imgChi = vieww.findViewById(R.id.imgRevenue);

        txtChi.setText(revenueModel.getDanhmucThu());
         Picasso.get().load(revenueModel.getImageThu()).into(imgChi);

        return vieww;
    }

}
