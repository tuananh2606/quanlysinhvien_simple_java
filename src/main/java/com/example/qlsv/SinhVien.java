package com.example.qlsv;

import javafx.scene.control.Button;

import java.io.Serializable;
public class SinhVien implements Serializable {
    private String MSV;
    private String hoTen;
    private String diaChi;
    private String email;
    private String SDT;
    private String nganh;
    private Double GPA;


    public SinhVien() {
    }

    public SinhVien(String MSV, String hoTen, String diaChi, String email , String SDT, String nganh, Double GPA) {
        this.MSV = MSV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
        this.SDT = SDT;
        this.nganh = nganh;
        this.GPA = GPA;
    }

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }
}
