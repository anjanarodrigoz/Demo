package controller;

import db.Database;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.tm.CustomerTm;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

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
    private TableColumn colSalary;

    @FXML
    private TableColumn colOption;

    @FXML
    private TableView<CustomerTm> customerTable;


    private Connection connection;

    @FXML
    public void reloadOnAction(ActionEvent event) {

        loadCustomerTable();
        customerTable.refresh();

    }

    @FXML
    public void saveOnAction(ActionEvent event) {

        Customer customer = getInputCustomer();

        if(customer==null){
            return;
        }

        String query = "INSERT INTO customer (name,address,salary) VALUES("
                +"'"+customer.getName()+"','"+
                            customer.getAddress()+"',"+
                            customer.getSalary()+");";


        try {

            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(query);
            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved").show();
            }
            connection.close();
        } catch (SQLIntegrityConstraintViolationException e){
          new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();


    }



    public void updateOnAction(ActionEvent event) {

       Customer customer =  getInputCustomer();

        if(customer == null){
            return;
        }

        String query = "UPDATE customer SET " +
                "name = '"+customer.getName()+"',"+
                "address = '"+customer.getAddress()+"',"+
                "salary = "+customer.getSalary()+" "+
                "WHERE id = '"+customer.getId()+"';";


        try {

            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(query);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
            }
            connection.close();
        } catch ( SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();

    }

    private Customer getInputCustomer() {



        try {
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Double.parseDouble(txtSalary.getText())
            );
            return customer;
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"add Correct salary amount").show();

            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = Database.getInstance().getConnection();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        loadCustomerTable();

        customerTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
                setData(newValue));

    }

    private void setData(CustomerTm selectedCustomer) {
        txtId.setText(selectedCustomer.getId()== null ? "" : selectedCustomer.getId());
        txtName.setText(selectedCustomer.getName());
        txtAddress.setText(selectedCustomer.getAddress());
        txtSalary.setText(selectedCustomer.getSalary()+"");
    }

    private void loadCustomerTable() {

        String query = "SELECT * FROM customer";

        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(query);

            while (result.next()){
                Button btn = new Button("Delete");
                CustomerTm customerTm = new CustomerTm(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        btn

                );


                btn.setOnAction(actionEvent -> {
                    deleteCustomer(customerTm.getId());
                });
                tmList.add(customerTm);
            }

            customerTable.setItems(tmList);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(String customerId) {

        String query = "DELETE FROM customer WHERE id ='"+customerId+"'";


        try {

            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(query);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }


        } catch (  SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();

    }
}
