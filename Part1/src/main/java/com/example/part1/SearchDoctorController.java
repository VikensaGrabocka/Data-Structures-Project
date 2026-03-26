package com.example.part1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchDoctorController {

    private final BinarySearchTree<Doctor> doctors   = BinarySearchTree.getInstance();

    @FXML
    private VBox searchBox;

    @FXML
    private Button backBtn;

    @FXML
    private Button okBtn;

    @FXML
    private TextField idField;

    @FXML
    private VBox searchResults;

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
    private Label notfoundMsg;

    @FXML
    public void goHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/menu-view.fxml"));
        Parent menuView = loader.load();
        AnchorPane parent = (AnchorPane) searchBox.getParent();
        parent.getChildren().setAll(menuView);

    }

    @FXML
    public void searchDoctor() throws IOException {
        notfoundMsg.setVisible(false);
        String idDoc = idField.getText();
        if (idDoc == null || idDoc.isEmpty()) {
            notfoundMsg.setText("Please enter a valid id");
            notfoundMsg.setVisible(true);
            return;
        }
        int id = Integer.parseInt(idField.getText());
        Doctor doctor = doctors.find(id);
        if (doctor != null) {
            searchResults.visibleProperty().setValue(true);
            doctorName.setText(doctor.getName());
            doctorSurname.setText(doctor.getSurname());
            doctorPosition.setText(doctor.getPosition());
            doctorDepartment.setText(doctor.getDepartment());
            doctorEmail.setText(doctor.getEmail());
            doctorId.setText(Integer.toString(id));

        }else{
            notfoundMsg.setVisible(true);
        }


    }

}
