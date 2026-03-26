package com.example.part2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class callPreviousController {
    private LinkedList<Contact> contacts = LinkedList.getInstance();
    @FXML
    private VBox callPrevious;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phone;

    @FXML
    private Button previousBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label notFoundMsg;

    @FXML
    public void callPrevious() {
        Contact contact = contacts.callPrevious();
        if (contact != null) {
            name.setText(contact.getFirstName());
            surname.setText(contact.getLastName());
            phone.setText(String.valueOf(contact.getPhone()));
        }else{
            notFoundMsg.setVisible(true);
        }
    }

    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/contactInformation.fxml"));
        Parent menuView = loader.load();

        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(menuView));

    }



}
