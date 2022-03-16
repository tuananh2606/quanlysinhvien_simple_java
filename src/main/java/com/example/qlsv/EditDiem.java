package com.example.qlsv;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

import java.net.URL;

import javafx.event.ActionEvent;

import static com.example.qlsv.DiemsvController.diemSVS;
import static com.example.qlsv.SinhVienController.sinhViens;


public class EditDiem implements Initializable {

    @FXML
    private TextField msv;

    @FXML
    private TextField hoTen;

    @FXML
    private TextField diemMH;

    @FXML
    private TextField monHoc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void handleAddAction(ActionEvent Ae) {
        try {
            int check = 0;
            if(msv != null && hoTen != null && monHoc != null && diemMH != null){
                if(Double.parseDouble(diemMH.getText())>=0.0){
                    for (int i = 0; i < diemSVS.size(); i++){
                        if(diemSVS.get(i).getMsv().equals(msv.getText())){
                            check = 1;
                            break;
                        }
                    }
                    if(check == 0){
                        diemSVS.add(new DiemSV(msv.getText(), hoTen.getText(), monHoc.getText(),
                                Double.parseDouble(diemMH.getText())));
                        Collections.sort(diemSVS, new Comparator<DiemSV>() {
                            @Override
                            public int compare(DiemSV o1, DiemSV o2) {
                                return o1.getMsv().compareTo(o2.getMsv());
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

    public void setDiem(DiemSV diemSV){
        msv.setText(diemSV.getMsv());
        hoTen.setText(diemSV.getHoTen());
        monHoc.setText(diemSV.getMonHoc());
        diemMH.setText(Double.toString(diemSV.getDiemMH()));
    }

    public void handleUpdateAction(ActionEvent Ae) {
        try {
            if(msv != null && hoTen != null && monHoc != null && diemMH != null) {
                if (Double.parseDouble(diemMH.getText()) >= 0.0) {
                    DiemSV diemSV = new DiemSV(msv.getText(), hoTen.getText(), monHoc.getText(), Double.parseDouble(diemMH.getText()));
                    for (int i = 0; i < diemSVS.size(); i++) {
                        if (diemSVS.get(i).getMsv().equals(msv.getText())) {
                            diemSVS.set(i, diemSV);
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