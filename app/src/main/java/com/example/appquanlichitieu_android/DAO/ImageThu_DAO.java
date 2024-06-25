package com.example.appquanlichitieu_android.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.imageThu_Model;

import java.util.ArrayList;
import java.util.List;

public class ImageThu_DAO {
    private SQLiteDatabase db;
    public ImageThu_DAO(Context context) {
        DbQuanli dbQuanli =new DbQuanli(context);
        db =dbQuanli.getWritableDatabase();
    }
    public List<imageThu_Model> getAll(){
        String sql = "SELECT * FROM ImageDMThu";
        return getData(sql);
    }
    private List<imageThu_Model> getData(String sql, String...selectionArgs){
        List<imageThu_Model> listQl = new ArrayList<imageThu_Model>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            imageThu_Model obj = new imageThu_Model();
            obj.idImageDM = Integer.parseInt(c.getString(0));
            obj.imageDM = c.getString(1);
            listQl.add(obj);
        }
        return listQl;
    }
}
