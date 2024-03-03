package controller;

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

    @FXML
    public void reloadOnAction(ActionEvent event) {

        loadCustomerTable();

    }

    @FXML
    public void saveOnAction(ActionEvent event) {

        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        String query = "INSERT INTO customer (name,address,salary) VALUES("
                +"'"+customer.getName()+"','"+
                            customer.getAddress()+"',"+
                            customer.getSalary()+");";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345678");
           Statement stm = connection.createStatement();
           int result = stm.executeUpdate(query);
           if(result>0){
               new Alert(Alert.AlertType.INFORMATION,"Customer saved").show();
           }
           connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();


    }



    public void updateOnAction(ActionEvent event) {

        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        String query = "UPDATE customer SET " +
                "name = '"+customer.getName()+"',"+
                "address = '"+customer.getAddress()+"',"+
                "salary = "+customer.getSalary()+" "+
                "WHERE id = '"+customer.getId()+"';";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345678");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(query);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        txtId.setText(selectedCustomer.getId());
        txtName.setText(selectedCustomer.getName());
        txtAddress.setText(selectedCustomer.getAddress());
        txtSalary.setText(selectedCustomer.getSalary()+"");
    }

    private void loadCustomerTable() {

        String query = "SELECT * FROM customer";

        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345678");
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
            connection.close();
            customerTable.setItems(tmList);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(String customerId) {

        String query = "DELETE FROM customer WHERE id ='"+customerId+"'";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345678");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(query);
            if(result>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        loadCustomerTable();

    }
}
