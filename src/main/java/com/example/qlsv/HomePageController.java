package com.example.qlsv;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.*;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Label adminName;

    @FXML
    private Button diemsv;

    @FXML
    private Button dashboard;

    @FXML
    private Button dangkyhoc;

    @FXML
    private Button monhoc;
    @FXML
    private Button sinhvien;

    @FXML
    private AnchorPane holdPane;

    private AnchorPane Pane;

    public static String name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void setNode(Node node) {
        holdPane.getChildren().clear();
        holdPane.getChildren().add((Node) node);
    }
    public void createSinhVien(ActionEvent actionEvent) {
        try {
            Pane = FXMLLoader.load(getClass().getResource("sinhvien.fxml"));
            setNode(Pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createDiemSinhVien(ActionEvent actionEvent) {
        try {
            Pane = FXMLLoader.load(getClass().getResource("diemsv.fxml"));
            setNode(Pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createMonHoc(ActionEvent actionEvent) {
        try {
            Pane = FXMLLoader.load(getClass().getResource("monhoc.fxml"));
            setNode(Pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createDangKyHoc(ActionEvent actionEvent) {
        try {
            Pane = FXMLLoader.load(getClass().getResource("dangkyhoc.fxml"));
            setNode(Pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
