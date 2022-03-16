package com.example.qlsv;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import java.net.URL;

import javafx.event.ActionEvent;

import static com.example.qlsv.DangKyHocController.dangKyHocs;
import static com.example.qlsv.MonHocController.monHocs;
import static com.example.qlsv.SinhVienController.sinhViens;


public class EditDangKyHoc implements Initializable {

    @FXML
    private TextField maMonhoc;

    @FXML
    private TextField msv;

    @FXML
    private TextField hoTen;

    @FXML
    private TextField monHoc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void handleAddAction(ActionEvent Ae) {
        try {
            int check = 0;
            if(maMonhoc != null && monHoc != null && msv != null && hoTen != null){
                for (int i = 0; i < dangKyHocs.size(); i++){
                    if((dangKyHocs.get(i).getMsv().equals(maMonhoc.getText()))
                            && (dangKyHocs.get(i).getMaMH().equals(maMonhoc.getText()))){
                        check = 1;
                        break;
                    }
                }
                if(check == 0){
                    dangKyHocs.add(new DangKyHoc(msv.getText(), hoTen.getText(), maMonhoc.getText(),monHoc.getText()));
                    Collections.sort(dangKyHocs, new Comparator<DangKyHoc>() {
                        @Override
                        public int compare(DangKyHoc o1, DangKyHoc o2) {
                            return o1.getMsv().compareTo(o2.getMsv());
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

    public void setDangKyHoc(DangKyHoc dangKyHoc){
        msv.setText(dangKyHoc.getMsv());
        hoTen.setText(dangKyHoc.getHoTen());
        maMonhoc.setText(dangKyHoc.getMaMH());
        monHoc.setText(dangKyHoc.getMonHoc());
    }

    public void handleUpdateAction(ActionEvent Ae) {
        try {
            if(maMonhoc != null && monHoc != null && msv != null && hoTen != null) {
                DangKyHoc dangKyHoc = new DangKyHoc(msv.getText(), hoTen.getText(), maMonhoc.getText(), monHoc.getText());
                for (int i = 0; i < dangKyHocs.size(); i++) {
                    if (dangKyHocs.get(i).getMsv().equals(msv.getText())){
                        dangKyHocs.set(i, dangKyHoc);
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
