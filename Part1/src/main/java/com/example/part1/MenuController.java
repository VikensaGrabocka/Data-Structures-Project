package com.example.part1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {


    @FXML
    private AnchorPane menu;

    @FXML
    private Label welcomeText;

    @FXML
    private ToggleButton addBtn;

    @FXML
    private ToggleButton sortBtn;

    @FXML
    private ToggleButton saveBtn;

    @FXML
    private ToggleButton deleteBtn;

    @FXML
    private ToggleButton searchBtn;


    @FXML
    private MenuButton traversalBtn;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Part 1 of the project!");
    }


    @FXML
    private MenuItem levelOrder;

    @FXML
    private MenuItem preOrder;

    @FXML
    private MenuItem postOrder;


    private final BinarySearchTree<Doctor> doctors   = BinarySearchTree.getInstance();

    @FXML
    public void gatherDoctorInformation() throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/info-view.fxml"));
        loader.setController(new InfoController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);

    }

    @FXML
    public void openDeleteWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/delete-view.fxml"));
        Parent doctorView = loader.load();
        DeleteController deleteController = loader.getController();
        deleteController.initialize();
        menu.getChildren().setAll(doctorView);
    }


    @FXML
    public void openSortView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/sorted-view.fxml"));
        loader.setController(new SortedController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);
    }

    @FXML
    public void openSearchWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/searchDoctor-view.fxml"));
        loader.setController(new SearchDoctorController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);
    }

    @FXML
    public void printPreOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/preOrder-view.fxml"));
        loader.setController(new PreOrderController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);
    }

    @FXML
    public void printPostOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/postOrder-view.fxml"));
        loader.setController(new PostOrderController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);
    }

    @FXML
    public void printLevelOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part1/levelOrder-view.fxml"));
        loader.setController(new LevelOrderController());
        Parent doctorView = loader.load();
        menu.getChildren().setAll(doctorView);
    }


    @FXML
    public void saveInFile() throws IOException {
        doctors.writeIntoFile();
    }


}

