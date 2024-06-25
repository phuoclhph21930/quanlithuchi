package com.example.appquanlichitieu_android.Update_Icon_Chi_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.imageChi_model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_ImageChi extends BaseAdapter {
    private int selectedItemPosition = -1;
    private Context context;
    private List<imageChi_model> list;
    public Adapter_ImageChi(Context context, List<imageChi_model> list) {
        this.context = context;
        this.list = list;
    }



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
            vieww = LayoutInflater.from(context).inflate(R.layout.item_image_dm, viewGroup, false);
        }
        ImageView imgChi = vieww.findViewById(R.id.item_image_thu);
        imageChi_model revenue = list.get(i);
        Picasso.get().load(revenue.getImageDMChi()).into(imgChi);
        return vieww;
    }

}
