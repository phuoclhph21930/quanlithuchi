package com.example.appquanlichitieu_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;

import java.util.ArrayList;
import java.util.List;

public class RevenueDAO {
    private SQLiteDatabase db;

    public RevenueDAO(Context context) {
        DbQuanli dbQuanli =new DbQuanli(context);
        db =dbQuanli.getWritableDatabase();
    }
    public List<RevenueModel> getAll(){
        String sql = "SELECT * FROM TienChi";
        return getData(sql);
    }
    public long insert(RevenueModel ex){
        ContentValues values = new ContentValues();
        values.put("imgChi",ex.imgChi);
        values.put("ngayChi",ex.ngayChi);
        values.put("ghiChuChi",ex.ghiChuChi);
        values.put("tienChi",ex.tienChi);
        values.put("danhMucChi",ex.danhMucChi);
        return db.insert("TienChi",null,values);
    }
    private List<RevenueModel> getData(String sql, String...selectionArgs){
        List<RevenueModel> listQl = new ArrayList<RevenueModel>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            RevenueModel obj = new RevenueModel();
            obj.idChi = Integer.parseInt(c.getString(0));
            obj.imgChi = c.getString(1);
            obj.ngayChi = c.getString(2);
            obj.ghiChuChi = c.getString(3);
            obj.tienChi =Integer.parseInt(c.getString(4));
            obj.danhMucChi = c.getString(5);
            listQl.add(obj);
        }
        return listQl;
    }

}
