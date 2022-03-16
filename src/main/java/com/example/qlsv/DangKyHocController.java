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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DangKyHocController implements Initializable {
    @FXML
    private TableColumn<DangKyHoc, String> msvTb;

    @FXML
    private TableColumn<DangKyHoc, String> hoTenTb;

    @FXML
    private TableColumn<DangKyHoc, String> maMHTb;

    @FXML
    private TableColumn<DangKyHoc, String> monHocTb;

    @FXML
    private TableView<DangKyHoc> dangkyhocTb;

    @FXML
    private TextField search;

    @FXML
    private Button addMH;

    public static  ObservableList<DangKyHoc> dangKyHocs = FXCollections.observableArrayList(
//                        new DangKyHoc("1","Long","MH1","PHP"),
//                        new DangKyHoc("2","Khương","MH2","Web"),
//                        new DangKyHoc("3","Đình","MH3","SQL")
    );



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDangKyHoc();
    }

    public void handleAddAction(ActionEvent actionEvent) throws IOException {
        try {
            Stage add = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("adddangkyhoc.fxml"));
            Scene scene = new Scene(root);
            add.setScene(scene);
            add.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDangKyHoc() {
        msvTb.setCellValueFactory(new PropertyValueFactory<>("msv"));
        hoTenTb.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        maMHTb.setCellValueFactory(new PropertyValueFactory<>("maMH"));
        monHocTb.setCellValueFactory(new PropertyValueFactory<>("monHoc"));
        dangkyhocTb.setItems(dangKyHocs);
        FilteredList<DangKyHoc> filteredData = new FilteredList<>(dangKyHocs, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dangKyHoc -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (dangKyHoc.getMsv().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (dangKyHoc.getHoTen().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (dangKyHoc.getMaMH().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (dangKyHoc.getMonHoc().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<DangKyHoc> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(dangkyhocTb.comparatorProperty());
        dangkyhocTb.setItems(sortedData);
        Collections.sort(dangKyHocs, new Comparator<DangKyHoc>() {
            @Override
            public int compare(DangKyHoc o1, DangKyHoc o2) {
                return o1.getMsv().compareTo(o2.getMsv());
            }
        });
    }

    public void Update(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updatedangkyhoc.fxml"));
        Parent SubjectViewParent = loader.load();
        Scene scene = new Scene(SubjectViewParent);
        EditDangKyHoc editDangKyHoc = loader.getController();
        DangKyHoc selected = dangkyhocTb.getSelectionModel().getSelectedItem();
        editDangKyHoc.setDangKyHoc(selected);
        stage.setScene(scene);
        stage.show();
    }

    public void exportFile(ActionEvent e) throws IOException {
        if(!dangKyHocs.isEmpty()){
            FileOutputStream f = null;
            ObjectOutputStream oStream = null;
            try {
                f = new FileOutputStream("DangKyHoc.dat");
                oStream = new ObjectOutputStream(f);
                for (int i = 0; i < dangKyHocs.size(); i++) {
                    oStream.writeObject(dangKyHocs.get(i));
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
        dangKyHocs.clear();
        try {
            f = new FileInputStream("DangKyHoc.dat");
            iStream = new ObjectInputStream(f);

            Object obj = null;
            while ((obj = iStream.readObject()) != null) {
                if (obj instanceof DangKyHoc) {
                    DangKyHoc dangKyHoc = (DangKyHoc) obj;
                    dangKyHocs.add(dangKyHoc);
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
            loadDangKyHoc();
        }
    }

    public void Delete(ActionEvent e) {
        DangKyHoc selected = dangkyhocTb.getSelectionModel().getSelectedItem();
        dangKyHocs.remove(selected);
    }
}