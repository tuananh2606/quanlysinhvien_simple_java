package com.example.qlsv;

import java.io.Serializable;

public class DangKyHoc implements Serializable {
    private String msv;
    private String hoTen;
    private String maMH;
    private String monHoc;

    public DangKyHoc() {
    }

    public DangKyHoc(String msv, String hoTen, String maMH ,String monHoc) {
        this.msv = msv;
        this.hoTen = hoTen;
        this.maMH = maMH;
        this.monHoc = monHoc;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }
}
