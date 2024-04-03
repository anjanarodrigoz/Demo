package controller;
import com.gluonhq.charm.glisten.control.Icon;
import db.Database;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Customer;
import model.impl.CustomerImpl;

import javax.management.Query;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public BorderPane customerHomePage;
    @FXML
    private Icon backButton;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private AnchorPane customerForm;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<CustomerDto> customerTable;

    ObservableList<CustomerDto> tmList = FXCollections.observableArrayList();


    Customer customer = new CustomerImpl();


    @FXML
    void addCustomerOnAction(ActionEvent event) {



    }

    @FXML
    void backButtonClicked(MouseEvent event) {



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

        loadCustomer();


    }

    private void loadCustomer() {

       List<CustomerDto> customerList =  customer.getAllCustomers();

       for (CustomerDto customerDto : customerList){

           tmList.add(customerDto);
       }


        customerTable.setItems(tmList);
    }
}


