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

import javafx.event.ActionEvent;

public class DiemsvController implements Initializable {
    @FXML
    private TableColumn<DiemSV, String> msvTb;

    @FXML
    private TableColumn<DiemSV, String> hoTenTb;

    @FXML
    private TableColumn<DiemSV, String> monTb;

    @FXML
    private TableColumn<DiemSV, Double> diemTb;



    @FXML
    private TableView<DiemSV> diemTable;

    @FXML
    private TextField search;


    public static ObservableList<DiemSV> diemSVS = FXCollections.observableArrayList(
//            new DiemSV("1","Tuan Anh","9","PHP"),
//            new DiemSV("2","Tuan Anh","9","Web"),
//            new DiemSV("3","Tuan Anh","9","SQL")
    );

    @FXML
    public void handleAddAction(ActionEvent actionEvent) throws IOException {
        try {
            Stage add = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("adddiem.fxml"));
            Scene scene = new Scene(root);
            add.setScene(scene);
            add.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDiemSinhVien();

    }
    private void loadDiemSinhVien()
    {
        msvTb.setCellValueFactory(new PropertyValueFactory<>("msv"));
        hoTenTb.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        monTb.setCellValueFactory(new PropertyValueFactory<>("MonHoc"));
        diemTb.setCellValueFactory(new PropertyValueFactory<>("DiemMH"));
        diemTable.setItems(diemSVS);
        FilteredList<DiemSV> filteredData = new FilteredList<> (diemSVS, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate( sinhVien -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(sinhVien.getMsv().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getHoTen().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getMonHoc().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }
                else if(sinhVien.getDiemMH() >= Double.parseDouble(search.getText())){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<DiemSV> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(diemTable.comparatorProperty()) ;
        diemTable.setItems(sortedData);
        Collections.sort(diemSVS, new Comparator<DiemSV>() {
            @Override
            public int compare(DiemSV o1, DiemSV o2) {
                return o1.getMsv().compareTo(o2.getMsv());
            }
        });
    }

    public void Update(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updatediem.fxml"));
        Parent PointViewParent = loader.load();
        Scene scene = new Scene(PointViewParent);
        EditDiem editDiem = loader.getController();
        DiemSV selected = diemTable.getSelectionModel().getSelectedItem();
        editDiem.setDiem(selected);
        stage.setScene(scene);
        stage.show();
    }
    public void Delete(ActionEvent e) {
        DiemSV selected = diemTable.getSelectionModel().getSelectedItem();
        diemSVS.remove(selected);
    }

    public void exportFile(ActionEvent e) throws IOException {
        if(!diemSVS.isEmpty()){
            FileOutputStream f = null;
            ObjectOutputStream oStream = null;
            try{
                f = new FileOutputStream("Diem.dat");
                oStream = new ObjectOutputStream(f);
                for(int i = 0; i < diemSVS.size(); i++){
                    oStream.writeObject(diemSVS.get(i));
                }
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
        diemSVS.clear();
        try{
            f = new FileInputStream("Diem.dat");
            iStream = new ObjectInputStream(f);

            Object obj = null;
            while ((obj = iStream.readObject()) != null) {
                if (obj instanceof DiemSV) {
                    DiemSV diemSV = (DiemSV) obj;
                    diemSVS.add(diemSV);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (EOFException ex) {
            ex.printStackTrace();
        } finally {
            f.close();
            iStream.close();
            loadDiemSinhVien();
        }
    }
}