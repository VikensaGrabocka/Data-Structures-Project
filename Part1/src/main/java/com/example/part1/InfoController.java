package com.example.part1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoController {


    private final BinarySearchTree<Doctor> doctors   = BinarySearchTree.getInstance();

    @FXML
    private VBox info;

    @FXML
    private Button backBtn;


    @FXML
    private TextField doctorId;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField doctorSurname;

    @FXML
    private TextField doctorPosition;

    @FXML
    private TextField doctorDepartment;

    @FXML
    private TextField doctorEmail;

    @FXML
    private Label successMsg;

    public InfoController() throws IOException, ClassNotFoundException {
    }


    @FXML
    public void insertDoctor(){
        String idField = doctorId.getText().trim();
        String name = doctorName.getText().trim();
        String surname = doctorSurname.getText().trim();
        String position = doctorPosition.getText().trim();
        String department = doctorDepartment.getText().trim();
        String email = doctorEmail.getText().trim();

        if (!idField.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !position.isEmpty() && !department.isEmpty() && !email.isEmpty()) {
            int id = Integer.parseInt(idField);
            if(doctors.find(id)!=null){
                successMsg.setText("DOCTOR ALREADY EXISTS");
                successMsg.setStyle("-fx-text-fill: red;");
                return;
            }

            Doctor doctor = new Doctor(id, name, surname, position,department, email);
            successMsg.visibleProperty().setValue(true);
            doctors.insert(doctor);
            doctorId.clear();
            doctorName.clear();
            doctorSurname.clear();
            doctorPosition.clear();
            doctorDepartment.clear();
            doctorEmail.clear();
            successMsg.setText("RECORD ENTERED SUCCESSFULLY");
            successMsg.setStyle("-fx-text-fill: #2fff00;");

        } else {
            successMsg.setText("PLEASE FILL IN ALL FIELDS");
            successMsg.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/menu-view.fxml"));
        Parent menuView = loader.load();
        AnchorPane parent = (AnchorPane) info.getParent();
        parent.getChildren().setAll(menuView);

    }






}
