package com.example.part2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactInformationController {

    private LinkedList<Contact> contacts = LinkedList.getInstance();

   @FXML
    private VBox menu;

   @FXML
    private Button callAgain;

    @FXML
    private Button callNext;

    @FXML
    private Button previousBtn;

    @FXML
    private Button shuffleBtn;

    @FXML
    private Button insertBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private MenuButton traverseBtn;

    @FXML
    private MenuItem forwardsOption;

    @FXML
    private MenuItem backwardsOption;

   @FXML
   public void callAgain() throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/callAgain.fxml"));
       Parent root = loader.load();
       callAgainController controller = loader.getController();
       controller.callAgain();

       Stage stage = new Stage();
       stage.setScene(new Scene(root));
       stage.setTitle("Call View");
       stage.show();

   }

    @FXML
    public void callNext() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/callNext.fxml"));
        Parent root = loader.load();
        callNextController controller = loader.getController();
        controller.callNext();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void callPrevious() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/callPrevious.fxml"));
        Parent root = loader.load();
        callPreviousController controller = loader.getController();
        controller.callPrevious();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void setShuffle() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/Shuffle.fxml"));
        Parent root = loader.load();
       ShuffleController controller = loader.getController();
        controller.initialize();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void insertContact() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/Insert.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void openForwards() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/traversalF.fxml"));
        Parent root = loader.load();
        TraversalFController controller = loader.getController();
        controller.initialize();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void openBackwards() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/traversalB.fxml"));
        Parent root = loader.load();
        TraversalBController controller = loader.getController();
        controller.initialize();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void displayDelete() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/part2/Delete.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void save() throws IOException {
       contacts.writeIntoFile();

    }

}
