package com.example.qlsv;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import java.io.*;
import java.util.*;

import java.net.URL;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;

public class SinhVienController implements Initializable {
    @FXML
    private TableColumn<SinhVien, Integer> msvTb;

    @FXML
    private TableColumn<SinhVien, String> hoTenTb;

    @FXML
    private TableColumn<SinhVien, String> diaChiTb;

    @FXML
    private TableColumn<SinhVien, String> emailTb;

    @FXML
    private TableColumn<SinhVien, String> sdtTb;

    @FXML
    private TableColumn<SinhVien, String> nganhTb;

    @FXML
    private TableColumn<SinhVien, String> diemTb;

    @FXML
    private TableView<SinhVien> sinhvienTb;


    @FXML
    private TextField search;

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
    private TextField sdt;


    public static ObservableList<SinhVien> sinhViens = FXCollections.observableArrayList(
    );

    @FXML
    public void handleAddAction(ActionEvent actionEvent) throws IOException {
        try {
            Stage add = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addsinhvien.fxml"));
            Scene scene = new Scene(root);
            add.setScene(scene);
            add.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSinhVien();
    }
    private void loadSinhVien()
    {
        msvTb.setCellValueFactory(new PropertyValueFactory<>("MSV"));
        hoTenTb.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        diaChiTb.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        emailTb.setCellValueFactory(new PropertyValueFactory<>("Email"));
        sdtTb.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        nganhTb.setCellValueFactory(new PropertyValueFactory<>("Nganh"));
        diemTb.setCellValueFactory(new PropertyValueFactory<>("GPA"));
        sinhvienTb.setItems(sinhViens);
        FilteredList<SinhVien> filteredData = new FilteredList<> (sinhViens, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate( sinhVien -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(sinhVien.getMSV().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getHoTen().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getDiaChi().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getEmail().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getNganh().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getSDT().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getGPA() > Double.parseDouble(search.getText())){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<SinhVien> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(sinhvienTb.comparatorProperty()) ;
        sinhvienTb.setItems(sortedData);

        Collections.sort(sinhViens, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                return o1.getMSV().compareTo(o2.getMSV());
            }
        });
    }
    public void Update(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updatesinhvien.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        EditSinhVien editSinhVien = loader.getController();
        SinhVien selected = sinhvienTb.getSelectionModel().getSelectedItem();
        editSinhVien.setSinhVien(selected);
        stage.setScene(scene);
        stage.show();
    }
    public void Delete(ActionEvent e) {
        SinhVien selected = sinhvienTb.getSelectionModel().getSelectedItem();
        sinhViens.remove(selected);
    }

    public void exportFile(ActionEvent e) throws IOException {
        if(!(sinhViens.isEmpty())){
            FileOutputStream f = null;
            ObjectOutputStream oStream = null;
            try{
                f = new FileOutputStream("Student.dat");
                oStream = new ObjectOutputStream(f);
                for(int i = 0; i < sinhViens.size(); i++){
                    oStream.writeObject(sinhViens.get(i));
                }
                //oStream.writeObject(monHocs);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                f.close();
                oStream.close();
            }
        }
    }

    public void readFile(ActionEvent e) throws IOException, ClassNotFoundException {
        FileInputStream f = null;
        ObjectInputStream iStream = null;
        sinhViens.clear();
        try{
            f = new FileInputStream("Student.dat");
            iStream = new ObjectInputStream(f);

            Object obj = null;
            while ((obj = iStream.readObject()) != null) {
                if (obj instanceof SinhVien) {
                    SinhVien sinhVien = (SinhVien) obj;
                    sinhViens.add(sinhVien);
                }
            }

//                while(true){
//                    monHocs.add((MonHoc)iStream.readObject());
//                }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (EOFException ex) {
            ex.printStackTrace();
        } finally {
            f.close();
            iStream.close();
            loadSinhVien();
        }
    }
}