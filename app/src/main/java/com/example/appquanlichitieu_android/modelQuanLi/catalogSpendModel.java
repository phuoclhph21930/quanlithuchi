package com.example.appquanlichitieu_android.modelQuanLi;

public class catalogSpendModel {
    public int idCatalog;
    public String imageSpend;
    public String catalog;

    public catalogSpendModel(int idCatalog,String imageSpend, String catalog) {
        this.imageSpend = imageSpend;
        this.catalog = catalog;
        this.idCatalog = idCatalog;
    }
    public catalogSpendModel(String imageSpend, String catalog) {
        this.imageSpend = imageSpend;
        this.catalog = catalog;
    }
    public int getIdCatalog() {
        return idCatalog;
    }

    public void setIdCatalog(int idCatalog) {
        this.idCatalog = idCatalog;
    }

    public catalogSpendModel() {
    }

    public String getImageSpend() {
        return imageSpend;
    }

    public void setImageSpend(String imageSpend) {
        this.imageSpend = imageSpend;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
