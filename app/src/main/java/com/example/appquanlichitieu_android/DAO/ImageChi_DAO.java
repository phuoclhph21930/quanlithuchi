package com.example.appquanlichitieu_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.imageChi_model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;

import java.util.ArrayList;
import java.util.List;

public class ImageChi_DAO {
    private SQLiteDatabase db;
    public ImageChi_DAO(Context context) {
        DbQuanli dbQuanli =new DbQuanli(context);
        db =dbQuanli.getWritableDatabase();
    }
    public List<imageChi_model> getAll(){
        String sql = "SELECT * FROM ImageDMChi";
        return getData(sql);
    }
    public boolean insert(imageChi_model image) {
        ContentValues values = new ContentValues();
        values.put("imageDMChi", image.getImageDMChi());

        long result = db.insert("ImageDMChi", null, values);
        return result != -1;
    }
    private List<imageChi_model> getData(String sql, String...selectionArgs){
        List<imageChi_model> listQl = new ArrayList<imageChi_model>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            imageChi_model obj = new imageChi_model();
            obj.idImageDMChi = Integer.parseInt(c.getString(0));
            obj.imageDMChi = c.getString(1);
            listQl.add(obj);
        }
        return listQl;
    }
}
