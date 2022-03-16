package com.example.qlsv;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.*;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import static com.example.qlsv.DiemsvController.diemSVS;
import static com.example.qlsv.MonHocController.monHocs;
import static com.example.qlsv.SinhVienController.sinhViens;


public class EditMonHoc implements Initializable {

    @FXML
    private TextField maMonhoc;

    @FXML
    private TextField tenMonhoc;

    @FXML
    private TextField nguoiday;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void handleAddAction(ActionEvent Ae) {
        try {
            int check = 0;
            if(maMonhoc != null && tenMonhoc != null && nguoiday != null){
                for (int i = 0; i < monHocs.size(); i++){
                    if(monHocs.get(i).getMaMonHoc().equals(maMonhoc.getText())){
                        check = 1;
                        break;
                    }
                }
                if(check == 0){
                    monHocs.add(new MonHoc(maMonhoc.getText(), tenMonhoc.getText(), nguoiday.getText()));
                    Collections.sort(monHocs, new Comparator<MonHoc>() {
                        @Override
                        public int compare(MonHoc o1, MonHoc o2) {
                            return o1.getMaMonHoc().compareTo(o2.getMaMonHoc());
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((Stage)(((Button)Ae.getSource()).getScene().getWindow())).close();
        }
    }

    public void setMonhoc(MonHoc monhoc){
        maMonhoc.setText(monhoc.getMaMonHoc());
        tenMonhoc.setText(monhoc.getTenMonHoc());
        nguoiday.setText(monhoc.getNguoiday());
    }

    public void handleUpdateAction(ActionEvent Ae) {
        try {
            if(maMonhoc != null && tenMonhoc != null && nguoiday != null){
                MonHoc monHoc = new MonHoc(maMonhoc.getText(), tenMonhoc.getText(), nguoiday.getText());
                for (int i = 0; i < monHocs.size(); i++){
                    if(monHocs.get(i).getMaMonHoc().equals(maMonhoc.getText())){
                        monHocs.set(i,monHoc);
                        break;
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
