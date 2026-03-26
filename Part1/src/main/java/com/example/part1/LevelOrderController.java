package com.example.part1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LevelOrderController {
    private final BinarySearchTree<Doctor> doctors = BinarySearchTree.getInstance();

    @FXML
    private VBox levelOrderBox;

    @FXML
    private TableView<Doctor> dataTable;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Doctor,Integer> idColumn;

    @FXML
    private TableColumn<Doctor,String> nameColumn;

    @FXML
    private TableColumn<Doctor,String> surnameColumn;

    @FXML
    private TableColumn<Doctor,String> positionColumn;

    @FXML
    private TableColumn<Doctor,String> departmentColumn;

    @FXML
    private TableColumn<Doctor,String> emailColumn;



    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/menu-view.fxml"));
        Parent menuView = loader.load();
        AnchorPane parent = (AnchorPane) levelOrderBox.getParent();
        parent.getChildren().setAll(menuView);

    }


    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        populateTable();
    }

    private void populateTable() {
        ObservableList<Doctor> doc = FXCollections.observableArrayList();
        doctors.printLevelOrder(doc);
        dataTable.setItems(doc);
    }


}
