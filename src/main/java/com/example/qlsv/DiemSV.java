package com.example.qlsv;

import java.io.Serializable;

public class DiemSV implements Serializable {
    private String msv;
    private String hoTen;
    private String monHoc;
    private Double diemMH;


    public DiemSV() {
    }

    public DiemSV(String msv, String hoTen, String monHoc, Double diemMH) {
        this.msv = msv;
        this.hoTen = hoTen;
        this.monHoc = monHoc;
        this.diemMH = diemMH;
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

    public Double getDiemMH() {
        return diemMH;
    }

    public void setDiemMH(Double diemMH) {
        this.diemMH = diemMH;
    }
}