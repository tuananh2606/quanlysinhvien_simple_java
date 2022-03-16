package com.example.qlsv;

import java.io.Serializable;

public class MonHoc implements Serializable  {
    private String maMonHoc;
    private String tenMonHoc;
    private String nguoiday;

    public MonHoc() {
    }

    public MonHoc(String maMonHoc, String tenMonHoc, String nguoiday) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.nguoiday = nguoiday;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getNguoiday() {
        return nguoiday;
    }

    public void setNguoiday(String nguoiday) {
        this.nguoiday = nguoiday;
    }
}


