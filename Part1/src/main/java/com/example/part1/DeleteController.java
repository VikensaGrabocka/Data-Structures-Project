package com.example.part1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;

public class DeleteController {
private final BinarySearchTree<Doctor> doctors   = BinarySearchTree.getInstance();

    @FXML
    private VBox deleteBox;


    @FXML
    private ComboBox<Doctor> doctorId;

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
    private Button deleteDocBtn;

    @FXML
    private Button backBtn;

    @FXML
     private Label notfoundMsg;

    @FXML
    private Label foundMsg;

    @FXML
    public void deleteRecord(){
        foundMsg.setVisible(false);
        notfoundMsg.setVisible(false);

        Doctor doctor  = doctorId.getValue();
        if(doctor != null){
            doctors.delete(doctor);
            foundMsg.setVisible(true);
        }else{
            notfoundMsg.setText("PLEASE SELECT AN ID!");
            notfoundMsg.setVisible(true);
        }


        doctorName.clear();
        doctorSurname.clear();
        doctorPosition.clear();
        doctorDepartment.clear();
        doctorEmail.clear();

        }


    public void initialize() {
        ObservableList<Doctor> doctorList = FXCollections.observableArrayList();
        if (doctors != null) {
            doctors.printPostOrder(doctorList);
        }

        doctorId.setItems(doctorList);


        doctorId.setConverter(new StringConverter<Doctor>() {
            @Override
            public String toString(Doctor doctor) {
                return doctor != null ? String.valueOf(doctor.getDoctor_id()) : "";
            }

            @Override
            public Doctor fromString(String s) {
                return null;
            }

        });
    }


    @FXML
    private void onDoctorSelected() {
        Doctor selectedDoctor = doctorId.getValue();
        if (selectedDoctor != null) {
            doctorName.setText(selectedDoctor.getName());
            doctorSurname.setText(selectedDoctor.getSurname());
            doctorDepartment.setText(selectedDoctor.getDepartment());
            doctorEmail.setText(selectedDoctor.getEmail());
            doctorPosition.setText(selectedDoctor.getPosition());
        }
    }


    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/menu-view.fxml"));
        Parent menuView = loader.load();
        AnchorPane parent = (AnchorPane) deleteBox.getParent();
        parent.getChildren().setAll(menuView);

    }

}
