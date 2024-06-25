package com.example.appquanlichitieu_android.modelQuanLi;

public class imageChi_model {

    public int idImageDMChi ;
    public String imageDMChi;

    public imageChi_model(int idImageDMChi, String imageDMChi) {
        this.idImageDMChi = idImageDMChi;
        this.imageDMChi = imageDMChi;
    }

    public imageChi_model() {
    }

    public int getIdImageDMChi() {
        return idImageDMChi;
    }

    public void setIdImageDMChi(int idImageDMChi) {
        this.idImageDMChi = idImageDMChi;
    }

    public String getImageDMChi() {
        return imageDMChi;
    }

    public void setImageDMChi(String imageDMChi) {
        this.imageDMChi = imageDMChi;
    }
}
