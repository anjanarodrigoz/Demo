package controller;
import dto.CustomerDto;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;
import model.impl.CustomerImpl;
import model.impl.ItemImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceFormController implements Initializable {

    @FXML
    public Label lblCustomerName;
    public TextField txtItemName;

    @FXML
    private Button addToCartButton;

    @FXML
    private TableView<?> cartTable;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colOptions;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<ItemDto> cmbItem;

    @FXML
    private ComboBox<CustomerDto> cmbCustomer;

    @FXML
    private Button placeOrderButton;

    @FXML
    private TextField quantity;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField unitPrice;

    @FXML
    void addToCart(ActionEvent event) {



    }

    @FXML
    void placeOrder(ActionEvent event) {

    }

    private Customer customer = new CustomerImpl();
    private Item item  = new ItemImpl();



    private List<CustomerDto> customerList = new ArrayList<>();
    private List<ItemDto> itemList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getAllCustomersName();
        getAllItemsCode();

        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->
                lblCustomerName.setText(newValue.getName()) );

        cmbItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->
                {
                    txtItemName.setText(newValue.getName());
                    unitPrice.setText(String.format("%.2f",newValue.getPrice()));
                }


        );

    }

    private void getAllItemsCode() {

        try {
            itemList = item.getAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       ObservableList<ItemDto> itemCodeList = FXCollections.observableArrayList(itemList);


        cmbItem.setItems(itemCodeList);

    }

    private void getAllCustomersName() {

        customerList  =  customer.getAllCustomers();

        ObservableList<CustomerDto> customerIdList = FXCollections.observableArrayList(customerList);



        cmbCustomer.setItems(customerIdList);

    }


}
