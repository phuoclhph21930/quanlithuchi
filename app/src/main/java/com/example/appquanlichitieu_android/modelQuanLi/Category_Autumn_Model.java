package com.example.appquanlichitieu_android.modelQuanLi;

public class Category_Autumn_Model {
    public int idCatalogThu;
    public String imageThu;
    public String DanhmucThu;

    public Category_Autumn_Model( String imageThu, String danhmucThu) {

        this.imageThu = imageThu;
        DanhmucThu = danhmucThu;
    }

    public Category_Autumn_Model() {
    }

    public int getIdCatalogThu() {
        return idCatalogThu;
    }

    public void setIdCatalogThu(int idCatalogThu) {
        this.idCatalogThu = idCatalogThu;
    }

    public String getImageThu() {
        return imageThu;
    }

    public void setImageThu(String imageThu) {
        this.imageThu = imageThu;
    }

    public String getDanhmucThu() {
        return DanhmucThu;
    }

    public void setDanhmucThu(String danhmucThu) {
        DanhmucThu = danhmucThu;
    }
}
