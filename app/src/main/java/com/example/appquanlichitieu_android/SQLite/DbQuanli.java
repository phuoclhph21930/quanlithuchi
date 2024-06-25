package com.example.appquanlichitieu_android.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbQuanli extends SQLiteOpenHelper {
    static final String dbName="QLChiTieu";
    static final int dbVersion=12;
    public DbQuanli(@Nullable Context context) {
        super(context,dbName,null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createTableNhapKhoan="create table NhapKhoan (" +
                "id Integer PRIMARY KEY AUTOINCREMENT, " +
                "imgThu text NOT NULL, " +
                "ngay text NOT NULL, " +
                "ghiChu text NOT NULL," +
                "tienThu Integer not null,"+
                "danhMuc text not null,"+
                "loai text NOT NULL)";
        sqLiteDatabase.execSQL(createTableNhapKhoan);
        String createTableCatalogSpend="create table Catalog (" +
                "idCatalog Integer PRIMARY KEY AUTOINCREMENT, " +
                "imageChi text NOT NULL, " +
                "danhMucChi text NOT NULL)" ;
        sqLiteDatabase.execSQL(createTableCatalogSpend);

        String insertQuery = "INSERT INTO Catalog (imageChi, danhMucChi) VALUES ('https://tse3.mm.bing.net/th?id=OIP.-EOuZbzcp9rGFmrGUbLb1wHaHa&pid=Api&P=0&h=180', 'Phone')";
        String insertQuery1 = "INSERT INTO Catalog (imageChi, danhMucChi) VALUES ('https://c1.klipartz.com/pngpicture/889/788/sticker-png-food-line-data-catering-industry-logo.png','Ăn uống')";
        String insertQuanAo = "INSERT INTO Catalog (imageChi, danhMucChi) VALUES ('https://png.pngtree.com/png-vector/20190929/ourlarge/pngtree-t-shirt-line-icon-vector-png-image_1770959.jpg','Quần áo')";
        String insertAnUong = "INSERT INTO Catalog (imageChi, danhMucChi) VALUES ('https://png.pngtree.com/png-vector/20220621/ourlarge/pngtree-flatware-fork-restaurant-icon-png-image_5165531.png', 'Ăn uống')";
        String insertGiaoduc = "INSERT INTO Catalog (imageChi, danhMucChi) VALUES ('https://symbols.vn/wp-content/uploads/2021/11/Icon-doc-sach-an-tuong.png', 'Giáo dục')";
        sqLiteDatabase.execSQL(insertQuery);
        sqLiteDatabase.execSQL(insertQuery1);
        sqLiteDatabase.execSQL(insertQuanAo);
        sqLiteDatabase.execSQL(insertAnUong);
        sqLiteDatabase.execSQL(insertGiaoduc);

        String createTableCatalogThu="create table CatalogThu (" +
                "idCatalogThu Integer PRIMARY KEY AUTOINCREMENT, " +
                "imageThu text NOT NULL, " +
                "DanhmucThu text NOT NULL)" ;
        sqLiteDatabase.execSQL(createTableCatalogThu);
        String insertQuanAoThu = "INSERT INTO CatalogThu (ImageThu, DanhmucThu) VALUES ('https://png.pngtree.com/png-vector/20190929/ourlarge/pngtree-t-shirt-line-icon-vector-png-image_1770959.jpg','Quần áo')";
        String insertThu = "INSERT INTO CatalogThu (imageThu, DanhmucThu) VALUES ('https://c1.klipartz.com/pngpicture/889/788/sticker-png-food-line-data-catering-industry-logo.png','Ăn uống')";
        sqLiteDatabase.execSQL(insertThu);
        sqLiteDatabase.execSQL(insertQuanAoThu);


        String createTableImageDanhmuc="create table ImageDMThu (" +
                "idImageDM Integer PRIMARY KEY AUTOINCREMENT, " +
                "imageDM text NOT NULL)";
        sqLiteDatabase.execSQL(createTableImageDanhmuc);
        //insert
        String ChupAnh = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://icon-library.com/images/picture-icon-png/picture-icon-png-25.jpg')";
        String icon2 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=100&id=o1ZYwaROqyNL&format=png&color=000000')";
        String icon3 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=100&id=48646&format=png&color=000000')";
        String icon4 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=24836&format=png')";
        String icon5 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=24&id=85122&format=png')";
        String icon6 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=9729&format=png')";
        String icon7 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=64&id=WCJ1D1cDlqqD&format=png')";
        String icon8 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=172&format=png')";
        String icon9 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=1M7FNqPBvgXS&format=png')";
        String icon10 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=42401&format=png')";
        String icon11 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=pgnkAal3-Ns3&format=png')";
        String icon12 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=QdQGF3zWydbO&format=png')";
        String icon13 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=100&id=2JGdk9ktxZyF&format=png&color=000000')";
        String icon14 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=YSd2MTRKLlRW&format=gif')";
        String icon15 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=J2AwyRUPwjyg&format=png')";
        String icon16 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=78946&format=png')";
        String icon18 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=13708&format=png')";
        String icon19 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=80&id=42382&format=png')";
        String icon20 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=24500&format=png')";
        String icon21 = "INSERT INTO ImageDMThu (imageDM) VALUES ('https://img.icons8.com/?size=50&id=172&format=png')";


        sqLiteDatabase.execSQL(ChupAnh);
        sqLiteDatabase.execSQL(icon2);
        sqLiteDatabase.execSQL(icon3);
        sqLiteDatabase.execSQL(icon4);
        sqLiteDatabase.execSQL(icon5);
        sqLiteDatabase.execSQL(icon6);
        sqLiteDatabase.execSQL(icon7);
        sqLiteDatabase.execSQL(icon8);
        sqLiteDatabase.execSQL(icon9);
        sqLiteDatabase.execSQL(icon10);
        sqLiteDatabase.execSQL(icon11);
        sqLiteDatabase.execSQL(icon12);
        sqLiteDatabase.execSQL(icon13);
        sqLiteDatabase.execSQL(icon14);
        sqLiteDatabase.execSQL(icon15);
        sqLiteDatabase.execSQL(icon16);
        sqLiteDatabase.execSQL(icon18);
        sqLiteDatabase.execSQL(icon19);
        sqLiteDatabase.execSQL(icon20);
        sqLiteDatabase.execSQL(icon21);


        //db hinh anh icon danh muc chi
        String createTableImageDanhmucChi="create table ImageDMChi (" +
                "idImageDMChi Integer PRIMARY KEY AUTOINCREMENT, " +
                "imageDMChi text NOT NULL)";
        sqLiteDatabase.execSQL(createTableImageDanhmucChi);
        //insert
        String ChupAnh1 = "INSERT INTO ImageDMChi (imageDMChi) VALUES ('https://icon-library.com/images/picture-icon-png/picture-icon-png-25.jpg')";
        sqLiteDatabase.execSQL(ChupAnh1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableKhoanThu = "drop table if exists NhapKhoan";
        sqLiteDatabase.execSQL(dropTableKhoanThu);
        String dropTableCatalogSpend = "drop table if exists Catalog";
        sqLiteDatabase.execSQL(dropTableCatalogSpend);
        String dropTableCatalogThu = "drop table if exists CatalogThu";
        sqLiteDatabase.execSQL(dropTableCatalogThu);
    }
}
