package com.example.qlsv;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MonHocController implements Initializable {

    @FXML
    private TableColumn<MonHoc, String> maMHTb;

    @FXML
    private TableColumn<MonHoc, String> tenMHTb;

    @FXML
    private TableColumn<MonHoc, String> nguoidayTb;

    @FXML
    private TableView<MonHoc> monhocTb;

    @FXML
    private TextField search;

    @FXML
    private Button addMH;

    @FXML
    private TextField hoTen;


        public static ObservableList<MonHoc> monHocs = FXCollections.observableArrayList(
        //            new MonHoc("1","PHP","Ha Noi"),
        //            new MonHoc("2","Web","Ha Noi"),
        //            new MonHoc("3","SQL","Ha Noi")
        );


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            loadMonHoc();
        }

        public void handleAddAction(ActionEvent actionEvent) throws IOException {
            try {
                Stage add = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("addmonhoc.fxml"));
                Scene scene = new Scene(root);
                add.setScene(scene);
                add.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void loadMonHoc()
        {
            monhocTb.setEditable(true);
            maMHTb.setCellValueFactory(new PropertyValueFactory<>("MaMonHoc"));
            tenMHTb.setCellValueFactory(new PropertyValueFactory<>("TenMonHoc"));
            nguoidayTb.setCellValueFactory(new PropertyValueFactory<>("nguoiday"));
            monhocTb.setItems(monHocs);
            FilteredList<MonHoc> filteredData = new FilteredList<>(monHocs, b -> true);
            search.textProperty().addListener((observable, oldValue, newValue) ->{
                filteredData.setPredicate( sinhVien -> {
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(sinhVien.getMaMonHoc().toLowerCase().indexOf(lowerCaseFilter) > -1){
                        return true;
                    }
                    else if(sinhVien.getTenMonHoc().toLowerCase().indexOf(lowerCaseFilter) > -1){
                        return true;
                    }
                    else if(sinhVien.getNguoiday().toLowerCase().indexOf(lowerCaseFilter) > -1){
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            });
            SortedList<MonHoc> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(monhocTb.comparatorProperty()) ;
            monhocTb.setItems(sortedData);
            Collections.sort(monHocs, new Comparator<MonHoc>() {
                @Override
                public int compare(MonHoc o1, MonHoc o2) {
                    return o1.getMaMonHoc().compareTo(o2.getMaMonHoc());
                }
            });
        }
        public void Update(ActionEvent e) throws IOException {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updatemonhoc.fxml"));
            Parent SubjectViewParent = loader.load();
            Scene scene = new Scene(SubjectViewParent);
            EditMonHoc editMonhoc = loader.getController();
            MonHoc selected = monhocTb.getSelectionModel().getSelectedItem();
            editMonhoc.setMonhoc(selected);
            stage.setScene(scene);
            stage.show();
        }
        public void exportFile(ActionEvent e) throws IOException {
            if(!monHocs.isEmpty()){
                FileOutputStream f = null;
                ObjectOutputStream oStream = null;
                try{
                    f = new FileOutputStream("Subject.dat");
                    oStream = new ObjectOutputStream(f);
                    for(int i = 0; i < monHocs.size(); i++){
                        oStream.writeObject(monHocs.get(i));
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
            monHocs.clear();
            try{
                f = new FileInputStream("Subject.dat");
                iStream = new ObjectInputStream(f);

                Object obj = null;
                while ((obj = iStream.readObject()) != null) {
                    if (obj instanceof MonHoc) {
                        MonHoc monHoc = (MonHoc) obj;
                        monHocs.add(monHoc);
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
                loadMonHoc();
            }
        }
        public void Delete(ActionEvent e) {
            MonHoc selected = monhocTb.getSelectionModel().getSelectedItem();
            monHocs.remove(selected);
        }
    }