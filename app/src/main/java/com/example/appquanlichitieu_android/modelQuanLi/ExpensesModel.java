package com.example.appquanlichitieu_android.modelQuanLi;

public class ExpensesModel {
    public int id;
    public String imgThu;
    public String ngay;
    public String ghiChu;
    public int tienThu;
    public String danhMuc;
    public String loai;


    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getImgThu() {
        return imgThu;
    }

    public void setImgThu(String imgThu) {
        this.imgThu = imgThu;
    }

    public ExpensesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTienThu() {
        return tienThu;
    }

    public void setTienThu(int tienThu) {
        this.tienThu = tienThu;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }
}
