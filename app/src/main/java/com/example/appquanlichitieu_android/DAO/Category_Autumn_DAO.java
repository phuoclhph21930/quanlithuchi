package com.example.appquanlichitieu_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;

import java.util.ArrayList;
import java.util.List;

public class Category_Autumn_DAO {
    private SQLiteDatabase db;
    public Category_Autumn_DAO(Context context) {
        DbQuanli dbQuanli =new DbQuanli(context);
        db =dbQuanli.getWritableDatabase();
    }
    public List<Category_Autumn_Model> getAll(){
        String sql = "SELECT * FROM CatalogThu";
        return getData(sql);
    }
    public int update(Category_Autumn_Model model) {
        ContentValues values = new ContentValues();
        values.put("imageThu", model.getImageThu());
        values.put("DanhmucThu", model.getDanhmucThu());

        String whereClause = "idCatalogThu = ?";
        String[] whereArgs = {String.valueOf(model.getIdCatalogThu())};

        return db.update("CatalogThu", values, whereClause, whereArgs);
    }
    public long insert(Category_Autumn_Model model) {
        ContentValues values = new ContentValues();
        values.put("imageThu", model.getImageThu());
        values.put("DanhmucThu", model.getDanhmucThu());

        return db.insert("CatalogThu", null, values);
    }
    public int delete(int id) {
        String whereClause = "idCatalogThu = ?";
        String[] whereArgs = { String.valueOf(id) };
        return db.delete("CatalogThu", whereClause, whereArgs);
    }
    private List<Category_Autumn_Model> getData(String sql, String...selectionArgs){
        List<Category_Autumn_Model> listQl = new ArrayList<Category_Autumn_Model>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Category_Autumn_Model obj = new Category_Autumn_Model();
            obj.idCatalogThu = Integer.parseInt(c.getString(0));
            obj.imageThu = c.getString(1);
            obj.DanhmucThu = c.getString(2);
            listQl.add(obj);
        }
        return listQl;
    }
}
