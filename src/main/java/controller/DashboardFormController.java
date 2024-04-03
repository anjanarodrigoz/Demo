package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {


    public Button customerButton;

    @FXML
    private AnchorPane dashBoardForm;

    public void customerOnAction(ActionEvent actionEvent) {

        Stage stage = (Stage) dashBoardForm.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
            stage.setTitle("My Customers");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }








}
