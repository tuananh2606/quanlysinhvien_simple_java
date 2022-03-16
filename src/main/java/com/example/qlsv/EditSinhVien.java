package com.example.qlsv;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

import java.net.URL;

import javafx.event.ActionEvent;

import static com.example.qlsv.SinhVienController.sinhViens;


public class EditSinhVien implements Initializable {

    @FXML
    private TextField msv;

    @FXML
    private TextField hoTen;

    @FXML
    private TextField diaChi;

    @FXML
    private TextField email;

    @FXML
    private TextField nganh;

    @FXML
    private TextField GPA;

    @FXML
    private TextField sdt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void handleAddAction(ActionEvent Ae){
        try {
            int check = 0;
            if(msv != null && hoTen != null && diaChi != null && email != null && sdt != null && nganh != null && GPA != null){
                if(Double.parseDouble(GPA.getText())>=0.0){
                    for (int i = 0; i < sinhViens.size(); i++){
                        if(sinhViens.get(i).getMSV().equals(msv.getText())){
                            check = 1;
                            break;
                        }
                    }
                    if(check == 0){
                        sinhViens.add(new SinhVien(msv.getText(), hoTen.getText(), diaChi.getText(),
                                email.getText() ,sdt.getText(),nganh.getText(),Double.parseDouble(GPA.getText())));
                        Collections.sort(sinhViens, new Comparator<SinhVien>() {
                            @Override
                            public int compare(SinhVien o1, SinhVien o2) {
                                return o1.getMSV().compareTo(o2.getMSV());
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((Stage)(((Button)Ae.getSource()).getScene().getWindow())).close();
        }
    }

    public void setSinhVien(SinhVien sinhvien){
        msv.setText(sinhvien.getMSV());
        hoTen.setText(sinhvien.getHoTen());
        diaChi.setText(sinhvien.getDiaChi());
        email.setText(sinhvien.getEmail());
        sdt.setText(sinhvien.getSDT());
        nganh.setText(sinhvien.getNganh());
        GPA.setText(Double.toString(sinhvien.getGPA()));
    }

    public void handleUpdateAction(ActionEvent Ae) {
        try {
            if(msv != null && hoTen != null && diaChi != null && email != null && sdt != null && nganh != null && GPA != null){
                if(Double.parseDouble(GPA.getText())>=0.0){
                    SinhVien sinhVien = new SinhVien(msv.getText(), hoTen.getText(), diaChi.getText(),
                            email.getText() ,sdt.getText(),nganh.getText(),Double.parseDouble(GPA.getText()));
                    for (int i = 0; i < sinhViens.size(); i++) {
                        if(sinhViens.get(i).getMSV().equals(msv.getText())){
                            sinhViens.set(i,sinhVien);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((Stage)(((Button)Ae.getSource()).getScene().getWindow())).close();
        }
    }
}