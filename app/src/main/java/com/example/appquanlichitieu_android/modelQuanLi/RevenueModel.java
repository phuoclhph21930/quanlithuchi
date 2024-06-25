package com.example.appquanlichitieu_android.modelQuanLi;

public class RevenueModel {
    public int idChi;
    public String imgChi;
    public String ngayChi;
    public String ghiChuChi;
    public int tienChi;
    public String danhMucChi;

    public RevenueModel(int idChi, String imgChi, String ngayChi, String ghiChuChi, int tienChi, String danhMucChi) {
        this.idChi = idChi;
        this.imgChi = imgChi;
        this.ngayChi = ngayChi;
        this.ghiChuChi = ghiChuChi;
        this.tienChi = tienChi;
        this.danhMucChi = danhMucChi;
    }

    public String getImgChi() {
        return imgChi;
    }

    public void setImgChi(String imgChi) {
        this.imgChi = imgChi;
    }

    public RevenueModel() {
    }

    public int getIdChi() {
        return idChi;
    }

    public void setIdChi(int idChi) {
        this.idChi = idChi;
    }

    public String getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(String ngayChi) {
        this.ngayChi = ngayChi;
    }

    public String getGhiChuChi() {
        return ghiChuChi;
    }

    public void setGhiChuChi(String ghiChuChi) {
        this.ghiChuChi = ghiChuChi;
    }

    public int getTienChi() {
        return tienChi;
    }

    public void setTienChi(int tienChi) {
        this.tienChi = tienChi;
    }

    public String getDanhMucChi() {
        return danhMucChi;
    }

    public void setDanhMucChi(String danhMuc) {
        this.danhMucChi = danhMuc;
    }
}
