package controller;
import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.impl.CustomerBoImpl;
import com.gluonhq.charm.glisten.control.Icon;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.CustomerDto;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public BorderPane customerHomePage;
    public JFXTextField customerIdField;
    public JFXTextField mobileNumberField;
    public JFXTextField customerNameField;
    public JFXTextField emailField;
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


    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);


    @FXML
    void addCustomerOnAction(ActionEvent event) {

        CustomerDto customerDto = getInputCustomer();
        try {
            if(customerBo.saveCustomer(customerDto)) {
                loadCustomer();
                new Alert(Alert.AlertType.INFORMATION, "Customer added successfully").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Customer added Failed").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Customer added Failed").show();
        }

    }

    private CustomerDto getInputCustomer() {

        return new CustomerDto(customerIdField.getText(),
                customerNameField.getText(),
                mobileNumberField.getText(),
                emailField.getText());
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

        viewData();

    }

    private void loadCustomer() {

        tmList.clear();

        try {
            List<CustomerDto>  customerList = customerBo.getAllCustomers();

            tmList.addAll(customerList);


            customerTable.setItems(tmList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {

        try {
          if(customerBo.deleteCustomer(customerIdField.getText())){
              new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted Successfully").show();
              loadCustomer();
          }else {
              new Alert(Alert.AlertType.ERROR,"Customer deleted Failed").show();
          }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Customer deleted Failed").show();
        }
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {

        CustomerDto customerDto = getInputCustomer();

        try {
            if(customerBo.updateCustomer(customerDto)) {
                loadCustomer();
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Customer updated Failed").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Customer updated Failed").show();
        }

    }


    private void viewData() {

        customerTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !customerTable.getSelectionModel().isEmpty()) {
                // Get selected item
                CustomerDto customerDto = customerTable.getSelectionModel().getSelectedItem();

                // Access data
                customerNameField.setText(customerDto.getName());
                customerIdField.setText(customerDto.getId());
                emailField.setText(customerDto.getEmailAddress());
                mobileNumberField.setText(customerDto.getMobileNumber());
            }
        });
    }
}


