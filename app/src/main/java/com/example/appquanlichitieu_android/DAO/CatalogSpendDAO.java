package com.example.appquanlichitieu_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;

import java.util.ArrayList;
import java.util.List;

public class CatalogSpendDAO {
    private SQLiteDatabase db;

    public CatalogSpendDAO(Context context) {
        DbQuanli dbQuanli = new DbQuanli(context);
        db = dbQuanli.getWritableDatabase();
    }

    public List<catalogSpendModel> getAll() {
        String sql = "SELECT * FROM Catalog";
        return getData(sql);
    }
    public long insert(catalogSpendModel model) {
        ContentValues values = new ContentValues();
        values.put("imageChi", model.getImageSpend());
        values.put("danhMucChi", model.getCatalog());

        return db.insert("Catalog", null, values);
    }
    public int delete(int id) {
        String whereClause = "idCatalog = ?";
        String[] whereArgs = { String.valueOf(id) };
        return db.delete("Catalog", whereClause, whereArgs);
    }
    public int update(catalogSpendModel model) {
        ContentValues values = new ContentValues();
        values.put("imageChi", model.getImageSpend());
        values.put("danhMucChi", model.getCatalog());

        String whereClause = "idCatalog = ?";
        String[] whereArgs = {String.valueOf(model.getIdCatalog())};

        return db.update("Catalog", values, whereClause, whereArgs);
    }

    private List<catalogSpendModel> getData(String sql, String... selectionArgs) {
        List<catalogSpendModel> listQl = new ArrayList<catalogSpendModel>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            catalogSpendModel obj = new catalogSpendModel();
            obj.idCatalog = Integer.parseInt(c.getString(0));
            obj.imageSpend = c.getString(1);
            obj.catalog = c.getString(2);
            listQl.add(obj);
        }
        return listQl;
    }
}
