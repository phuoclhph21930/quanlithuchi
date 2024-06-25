package com.example.appquanlichitieu_android.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appquanlichitieu_android.SQLite.DbQuanli;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.RevenueModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ExpensesDAO {
    private SQLiteDatabase db;

    public ExpensesDAO(Context context) {
        DbQuanli dbQuanli =new DbQuanli(context);
        db =dbQuanli.getWritableDatabase();
    }
    public List<ExpensesModel> getAll(){
        String sql = "SELECT * FROM NhapKhoan";
        return getData(sql);
    }
    public int getTotalRevenueInMonth(int month) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7) = strftime('%Y', 'now') AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%02d", month) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }
        cursor.close();
        return totalRevenue;
    }
//    public HashMap<String, Integer> ExpensesByCategoryy(int year, int month) {
//        HashMap<String, Integer> result = new HashMap<>();
//
//        String sql = "SELECT danhMuc, SUM(tienThu) AS total_revenue "
//                + "FROM NhapKhoan "
//                + "WHERE loai = 'KhoanChi' "
//                + "AND strftime('%Y', ngay) = ? AND strftime('%m', ngay) = ? "
//                + "GROUP BY danhMuc";
//        Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(year), String.valueOf(month) });
//
//        while (cursor.moveToNext()) {
//            String category = cursor.getString(0);
//            int totalRevenue = cursor.getInt(1);
//            result.put(category, totalRevenue);
//        }
//
//        cursor.close();
//        return result;
//    }



    // get doanh thu trong tháng

    public int getTotalRevenueForMonth(int month,int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }

        cursor.close();
        return totalRevenue;
    }

    //get khoan thu trong nam
    public int getTotalRevenueYeah(int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE  substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }

        cursor.close();
        return totalRevenue;
    }
    //get tien chi trong nam
    public int getTotalExpensesYeah(int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE  substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }

        cursor.close();
        return totalRevenue;
    }

    //get khoản chi trong tháng
    public int getTotalExpensesForMonth(int month, int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        int totalExpenses = 0;
        if (cursor.moveToFirst()) {
            totalExpenses = cursor.getInt(0);
        }

        cursor.close();
        return totalExpenses;
    }
    public HashMap<String, Integer> getTotalExpensesByCategoryy(int year, int month) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) " +
                "FROM NhapKhoan " +
                "WHERE loai = 'KhoanChi' " +
                "AND substr(ngay, 7, 4) = ? " +
                "AND substr(ngay, 4, 2) = ? " +
                "GROUP BY danhMuc";

        String[] args = {String.valueOf(year), String.valueOf(month)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalRevenue = cursor.getInt(1);
            result.put(category, totalRevenue);
        }

        cursor.close();
        return result;
    }

    // get tien chi danh muc trong tháng

    public HashMap<String, Integer> getTotalExpensesByCategory(int month, int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanChi' AND substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ?" +
                "GROUP BY danhMuc";
        String[] args = {String.format("%02d", month), String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    // barchart report expenses
    public HashMap<String, Integer> getTotalExpensesMonth( int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT ngay, SUM(tienThu) AS totalExpense FROM NhapKhoan " +
                "WHERE loai = 'KhoanChi'  AND substr(ngay, 7, 4) = ? " +
                "GROUP BY ngay";
        String[] args = { String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String date = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(date, totalExpense);
        }

        cursor.close();
        return result;
    }
    //barchart report revenue
    public HashMap<String, Integer> getTotalRevenueMonth( int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT ngay, SUM(tienThu) AS totalExpense FROM NhapKhoan " +
                "WHERE loai = 'KhoanThu'  AND substr(ngay, 7, 4) = ? " +
                "GROUP BY ngay";
        String[] args = { String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String date = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(date, totalExpense);
        }

        cursor.close();
        return result;
    }
    //get tien thu danh muc trong thang
    public HashMap<String, Integer> getTotalRevenueByCategory(int month, int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanThu' AND substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ?" +
                "GROUP BY danhMuc";
        String[] args = {String.format("%02d", month), String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    //get tiền chi danh muc trong năm
    public HashMap<String, Integer> getTotalExpensesByCategoryYeah( int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanChi' AND substr(ngay, 7, 4) = ?" +
                "GROUP BY danhMuc";
        String[] args = { String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    //get tien thu cua tung danh muc trong nam
    public HashMap<String, Integer> getTotalRevenueByCtgYeah( int year) {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanThu' AND substr(ngay, 7, 4) = ?" +
                "GROUP BY danhMuc";
        String[] args = { String.format("%04d", year)};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    //get tiền chi danh muc ca du lieu
    public HashMap<String, Integer> getTotalExpensess() {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanChi' " +
                "GROUP BY danhMuc";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    //get tiền Thu danh muc ca du lieu
    public HashMap<String, Integer> getTotalRevenueDM_ki() {
        HashMap<String, Integer> result = new HashMap<>();

        String sql = "SELECT danhMuc, SUM(tienThu) FROM NhapKhoan " +
                "WHERE loai = 'KhoanThu' " +
                "GROUP BY danhMuc";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()) {
            String category = cursor.getString(0);
            int totalExpense = cursor.getInt(1);
            result.put(category, totalExpense);
        }

        cursor.close();
        return result;
    }
    public int getTotalExpensesInMonth(int Year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7) = strftime('%Y', 'now') AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%02d", Year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }
        cursor.close();
        return totalRevenue;
    }
//tổng thu trong dữ liệu
public int getTotalRevenue() {
    String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE loai = 'KhoanThu'";
    Cursor cursor = db.rawQuery(sql, null);
    int totalRevenue = 0;
    if (cursor.moveToFirst()) {
        totalRevenue = cursor.getInt(0);
    }
    cursor.close();
    return totalRevenue;
}
    public int getTotalExpenses() {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE loai = 'KhoanChi'";
        Cursor cursor = db.rawQuery(sql, null);
        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }
        cursor.close();
        return totalRevenue;
    }
    //lấy tổng doanh thu trong một năm
    public int getTotalRevenueInYear(int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 7) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }
        cursor.close();
        return totalRevenue;
    }

    public int getTotalExpensesInYear(int year) {
        String sql = "SELECT SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 7) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%04d", year) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        int totalRevenue = 0;
        if (cursor.moveToFirst()) {
            totalRevenue = cursor.getInt(0);
        }
        cursor.close();
        return totalRevenue;
    }
    //get data tháng của khoản chi
    public List<ExpensesModel> getDataByMonthAndChi(int month,int yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", yeah)};
        return getData(sql, selectionArgs);
    }
    public List<ExpensesModel> getDataByMonthAndRevuene(int month,int yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", yeah)};
        return getData(sql, selectionArgs);
    }
    //get data năm của khoản chi
    public List<ExpensesModel> DataByYeahAndChi(int yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE  substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%04d", yeah)};
        return getData(sql, selectionArgs);
    }
    //getDat nam cua khoan thu
    public List<ExpensesModel> getDataByRevenueyYeah(int yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE  substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%04d", yeah)};
        return getData(sql, selectionArgs);
    }
    //get data  của ca du lieu
    public List<ExpensesModel> getDataChi() {
        String sql = "SELECT * FROM NhapKhoan WHERE loai = 'KhoanChi'";
        return getData(sql);
    }
    //getdata khoan thu
    public List<ExpensesModel> getDataThu() {
        String sql = "SELECT * FROM NhapKhoan WHERE loai = 'KhoanThu'";
        return getData(sql);
    }
    public List<ExpensesModel> getDataByMonthAndChi(int yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%04d", yeah)};
        return getData(sql, selectionArgs);
    }
    public List<ExpensesModel> getDataByMonthAndThu(int month) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%02d", month)};
        return getData(sql, selectionArgs);
    }

    //test get data theo năm và tháng
    public List<ExpensesModel> getDataByMonthAndThuForYear(int month, int year) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 4,  2) = ? AND substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", year)  };
        return getData(sql, selectionArgs);
    }

    //get data theo năm của khoản thu
    public List<ExpensesModel> getDataByYeahAndThu(int Yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
        String[] selectionArgs = { String.format("%04d", Yeah)};
        return getData(sql, selectionArgs);
    }
    //get data theo năm của khoản chi
    public List<ExpensesModel> getDataByYeahAndChi(int Yeah) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 7, 4) = ? AND loai = 'KhoanChi'";
        String[] selectionArgs = { String.format("%04d", Yeah)};
        return getData(sql, selectionArgs);
    }
    //get data của lịch theo tháng và năm
    public List<ExpensesModel> getDataByMonth(int month,int year) {
        String sql = "SELECT * FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND substr(ngay, 7, 4) = ?";
        String[] selectionArgs = { String.format("%02d", month),String.format("%04d", year)};
        return getData(sql, selectionArgs);
    }
// lay du liêu tổng thu ngày trong tháng
public HashMap<String, Integer> getTotalRevenueByDayInMonth(int month) {
    HashMap<String, Integer> result = new HashMap<>();

    // Lấy dữ liệu tổng thu từ ExpensesDAO
    String sql = "SELECT ngay, SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND loai = 'KhoanThu' GROUP BY ngay ORDER BY substr(ngay, 1, 2), substr(ngay, 4, 2), substr(ngay, 7, 2)";
    String[] selectionArgs = { String.format("%02d", month) };
    Cursor cursor = db.rawQuery(sql, selectionArgs);

    // Tính tổng thu của từng ngày
    while (cursor.moveToNext()) {
        String ngay = cursor.getString(0);
        int totalRevenue = cursor.getInt(1);
        result.put(ngay, totalRevenue);
    }

    cursor.close();
    return result;
}


    public HashMap<String, Integer> getTotalExpensesByDayInMonth(int month) {
        HashMap<String, Integer> result = new HashMap<>();

        // Lấy dữ liệu tổng thu từ ExpensesDAO
        String sql = "SELECT ngay, SUM(tienThu) FROM NhapKhoan WHERE substr(ngay, 4, 2) = ? AND loai = 'KhoanChi' GROUP BY ngay ORDER BY substr(ngay, 1, 2), substr(ngay, 4, 2), substr(ngay, 7, 2)";
        String[] selectionArgs = { String.format("%02d", month) };
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        // Tính tổng thu của từng ngày
        while (cursor.moveToNext()) {
            String ngay = cursor.getString(0);
            int totalRevenue = cursor.getInt(1);
            result.put(ngay, totalRevenue);
        }
        cursor.close();
        return result;
    }

// tổng thu tháng trong năm
public Map<String, Integer> getTotalExpensesBymonthAndYeah(int year) {
    Map<String, Integer> result = new HashMap<>();

    String sql = "SELECT strftime('%Y-%m', ngay) AS month, SUM(tienThu) AS total_revenue " +
            "FROM NhapKhoan " +
            "WHERE strftime('%Y', ngay) = ? AND loai = 'KhoanThu' " +
            "GROUP BY month " +
            "ORDER BY month";
    String[] selectionArgs = { String.format("%04d", year) };
    Cursor cursor = db.rawQuery(sql, selectionArgs);

    while (cursor.moveToNext()) {
        String month = cursor.getString(0);
        int totalRevenue = cursor.getInt(1);
        result.put(month, totalRevenue);
    }

    cursor.close();
    return result;
}
    public int update(ExpensesModel ex) {
        ContentValues values = new ContentValues();
        values.put("imgThu", ex.imgThu);
        values.put("ngay", ex.ngay);
        values.put("ghiChu", ex.ghiChu);
        values.put("tienThu", ex.tienThu);
        values.put("danhMuc", ex.danhMuc);
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(ex.id) };

        return db.update("NhapKhoan", values, whereClause, whereArgs);
    }
// tổng khoản thu trong cả dữ liệu
public int getTotalIncomeByType() {
    int totalIncome = 0;
    String sql = "SELECT SUM(tienThu) AS total_income FROM NhapKhoan WHERE loai = 'KhoanThu'";
    List<ExpensesModel> incomeList = getData(sql);
    if (!incomeList.isEmpty()) {
        totalIncome = incomeList.get(0).getTienThu();
    }
    return totalIncome;
}
//tổng khoản thu theo năm
public double getTotalByYear(int year) {
    String sql = "SELECT SUM(tienThu) AS totalByYear " +
            "FROM NhapKhoan " +
            "WHERE substr(ngay, 7, 4) = ? AND loai = 'KhoanThu'";
    String[] selectionArgs = { String.valueOf(year) };
    List<ExpensesModel> result = getData(sql, selectionArgs);

    if (result.size() > 0) {
        return result.get(0).tienThu;
    } else {
        return 0.0;
    }
}

// Tổng doanh thu từng tháng trong năm
public Map<String, Integer> getTotalRevenueByMonthInYearReport(int year) {
    Map<String, Integer> result = new LinkedHashMap<>();

    String sql = "SELECT strftime('%m',ngay) AS month, SUM(tienThu) AS total_revenue " +
            "FROM NhapKhoan " +
            "WHERE strftime('%Y',ngay) = ? AND loai = 'KhoanThu' " +
            "GROUP BY month " +
            "ORDER BY month ASC";

    try (Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(year) })) {
        while (cursor.moveToNext()) {
            String month = cursor.getString(0);
            int totalRevenue = cursor.getInt(1);
            result.put(month, totalRevenue);
        }
    }

    // Thêm các tháng còn thiếu với giá trị 0
    for (int i = 1; i <= 12; i++) {
        String monthStr = String.format("%02d", i);
        if (!result.containsKey(monthStr)) {
            result.put(monthStr, 0);
        }
    }

    return result;
}
//tìm kiếm danh mục
@SuppressLint("Range")
public List<ExpensesModel> searchExpenses(String keyword) {
    List<ExpensesModel> results = new ArrayList<>();
    String sql = "SELECT * FROM NhapKhoan WHERE ghiChu LIKE ? OR danhMuc LIKE ?";
    String wildcardKeyword = "%" + keyword + "%";

    try (Cursor cursor = db.rawQuery(sql, new String[] { wildcardKeyword, wildcardKeyword })) {
        while (cursor.moveToNext()) {
            ExpensesModel expense = new ExpensesModel();
            expense.setImgThu(cursor.getString(cursor.getColumnIndex("imgThu")));
            expense.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
            expense.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            expense.setTienThu(cursor.getInt(cursor.getColumnIndex("tienThu")));
            expense.setDanhMuc(cursor.getString(cursor.getColumnIndex("danhMuc")));
            expense.setLoai(cursor.getString(cursor.getColumnIndex("loai")));
            results.add(expense);
        }
    }

    return results;
}
    @SuppressLint("Range")
    public List<ExpensesModel> searchExpensesByDateAndMonth(String keyword, String monthYear) {
        List<ExpensesModel> results = new ArrayList<>();
        String sql = "SELECT * FROM NhapKhoan WHERE (ghiChu LIKE ? OR danhMuc LIKE ?) AND DATE(ngay) BETWEEN DATE(?) AND DATE(?)";
        String wildcardKeyword = "%" + keyword + "%";
        String[] dateParams = monthYear.split("-");
        String startDate = dateParams[0] + "-" + dateParams[1] + "-01";
        String endDate = dateParams[0] + "-" + dateParams[1] + "-31";

        try (Cursor cursor = db.rawQuery(sql, new String[] { wildcardKeyword, wildcardKeyword, startDate, endDate })) {
            while (cursor.moveToNext()) {
                ExpensesModel expense = new ExpensesModel();
                expense.setImgThu(cursor.getString(cursor.getColumnIndex("imgThu")));
                expense.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
                expense.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
                expense.setTienThu(cursor.getInt(cursor.getColumnIndex("tienThu")));
                expense.setDanhMuc(cursor.getString(cursor.getColumnIndex("danhMuc")));
                expense.setLoai(cursor.getString(cursor.getColumnIndex("loai")));
                results.add(expense);
            }
        }

        return results;
    }
    public int getTotalExpensesByType() {
        int totalIncome = 0;
        String sql = "SELECT SUM(tienThu) AS total_income FROM NhapKhoan WHERE loai = 'KhoanChi'";
        List<ExpensesModel> incomeList = getData(sql);
        if (!incomeList.isEmpty()) {
            totalIncome = incomeList.get(0).getTienThu();
        }
        return totalIncome;
    }
    public long insert(ExpensesModel ex){
        ContentValues values = new ContentValues();
        values.put("imgThu",ex.imgThu);
        values.put("ngay",ex.ngay);
        values.put("ghiChu",ex.ghiChu);
        values.put("tienThu",ex.tienThu);
        values.put("danhMuc",ex.danhMuc);
        values.put("loai",ex.loai);
        return db.insert("NhapKhoan",null,values);
    }

    public int delete(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };
        return db.delete("NhapKhoan", whereClause, whereArgs);
    }
    private List<ExpensesModel> getData(String sql, String...selectionArgs){
        List<ExpensesModel> listQl = new ArrayList<ExpensesModel>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ExpensesModel obj = new ExpensesModel();
            obj.id = Integer.parseInt(c.getString(0));
            obj.imgThu = c.getString(1);
            obj.ngay = c.getString(2);
            obj.ghiChu = c.getString(3);
            obj.tienThu =Integer.parseInt(c.getString(4));
            obj.danhMuc = c.getString(5);
            obj.loai = c.getString(6);
            listQl.add(obj);
        }
        return listQl;
    }
}
