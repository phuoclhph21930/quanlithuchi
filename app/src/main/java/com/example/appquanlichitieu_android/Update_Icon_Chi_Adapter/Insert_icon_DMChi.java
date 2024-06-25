package com.example.appquanlichitieu_android.Update_Icon_Chi_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.imageChi_model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Insert_icon_DMChi extends BaseAdapter {
    private Context context;
    private List<imageChi_model> list;
    private int selectedItemPosition = -1;
    public Insert_icon_DMChi(Context context, List<imageChi_model> list) {
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

        imageChi_model revenueModel = list.get(i);
        ImageView imgChi = vieww.findViewById(R.id.item_image_thu);
        if(revenueModel.getImageDMChi()!=null){
            Picasso.get().load(revenueModel.getImageDMChi()).into(imgChi);
        }



        return vieww;
    }
    private static class ViewHolder {
        ImageView imgChi;
    }
}
