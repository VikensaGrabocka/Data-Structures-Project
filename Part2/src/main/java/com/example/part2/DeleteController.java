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

public class DeleteController {
    private LinkedList<Contact> contacts = LinkedList.getInstance();
    @FXML
    private VBox deleteBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button okBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label successMsg;

    @FXML
    private Label errorMsg;

    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/contactInformation.fxml"));
        Parent menuView = loader.load();

        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(menuView));

    }

    @FXML
    public void delete(){
        errorMsg.setVisible(false);
        successMsg.setVisible(false);
        String name = nameField.getText();
        String surname = surnameField.getText();
        if(name.isEmpty()){  // name is empty
            if(!surname.isEmpty()) { //surname is not empty
                if (!contacts.existsSurname(surname)) {
                    errorMsg.setText("Contact with this surname does not exist");
                    errorMsg.setVisible(true);
                } else {
                    contacts.removeSurname(surname);
                    successMsg.setVisible(true);
                }
            }else { //surname is also empty
                errorMsg.setText("Please fill at least one field!");
                errorMsg.setVisible(true);
            }

        }else{ //name is not empty
            if(surname.isEmpty()){
                if(!contacts.existsName(name)){
                    errorMsg.setText("Contact with this name does not exist");
                    errorMsg.setVisible(true);
                }else{
                    contacts.removeName(name);
                    successMsg.setVisible(true);
                }
            }
            else{ //surname is not empty
                if(contacts.exists(name, surname)){
                    contacts.removeNameSurname(name, surname);
                    successMsg.setVisible(true);
                }else{
                    errorMsg.setText("This contact does not exist");
                    errorMsg.setVisible(true);
                }
                }
            }


    }



}
