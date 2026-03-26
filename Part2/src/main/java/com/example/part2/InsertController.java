package com.example.part2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertController {
    private LinkedList<Contact> contacts = LinkedList.getInstance();
    @FXML
    private VBox insertBox;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField posField;

    @FXML
    private RadioButton beginningBtn;

    @FXML
    private RadioButton endBtn;

    @FXML
    private RadioButton positionBtn;

    @FXML
    private Label warningMsg;

    @FXML
    private Label successMsg;

    @FXML
    private Button backBtn;

    @FXML
    private Button OkBtn;

    private boolean beginning;
    private boolean end;
    private boolean position;
    private int positionNum;

    @FXML
    public void insertAtBeginning() {
        beginning = true;
        end = false;
        position = false;
        posField.visibleProperty().set(false);

    }

    @FXML
    public void insertAtEnd(){
        end = true;
        beginning = false;
        posField.visibleProperty().set(false);
        position = false;
    }

    @FXML
    public void position(){
        end = false;
        beginning = false;
        position = true;
        posField.visibleProperty().set(true);
    }

    @FXML
    public void insert(){
        warningMsg.setVisible(false);
        successMsg.setVisible(false);
        String contactName = name.getText();
        String contactSurname = surname.getText();
        String contactPhone = phone.getText();
        String contactEmail = email.getText();
        if(contactName.isEmpty() || contactSurname.isEmpty() || contactPhone.isEmpty() || contactEmail.isEmpty() || (!beginning && !end && !position)){
            warningMsg.setVisible(true);
        }else{
            Contact contact = new Contact(contactName, contactSurname, Integer.parseInt(contactPhone), contactEmail);
            if(contacts.exists(contact)){
                warningMsg.setText("This contact already exists");
                warningMsg.setVisible(true);
            }else{
                if(beginning){
                    contacts.addFirst(contact);
                    successMsg.setVisible(true);
                } else if(end) {
                    contacts.addLast(contact);
                    successMsg.setVisible(true);
                } else if (position) {
                    positionNum = Integer.parseInt(posField.getText());
                    if(positionNum < 0 || positionNum > contacts.size() ){
                        warningMsg.setText("Please enter a valid position!");
                        warningMsg.setVisible(true);
                    }else{
                        contacts.add(positionNum, contact);
                        successMsg.setVisible(true);
                    }

                }
            }
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
