package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn ColSalary;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableView customerTable;

    @FXML
    public void reloadOnAction(ActionEvent event) {

    }

    @FXML
    public void saveOnAction(ActionEvent event) {

    }



    public void updateOnAction(ActionEvent event) {
    }
}
