package com.example.part2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TraversalBController {
    @FXML
    private VBox traversalB;

    private LinkedList<Contact> contacts = LinkedList.getInstance();

    @FXML
    private TableView<Contact> dataTable;

    @FXML
    private Button backBtn;


    @FXML
    private TableColumn<Contact,String> nameColumn;

    @FXML
    private TableColumn<Contact,String> surnameColumn;

    @FXML
    private TableColumn<Contact,String> phoneColumn;

    @FXML
    private TableColumn<Contact,String> emailColumn;



    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/contactInformation.fxml"));
        Parent menuView = loader.load();

        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(menuView));

    }


    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        populateTable();
    }

    private void populateTable() {
        ObservableList<Contact> contact = FXCollections.observableArrayList();
        contacts.traverseBackward(contact);
        dataTable.setItems(contact);
    }
}
