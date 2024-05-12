package controller;

import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXButton;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;
import dto.tm.CartTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceFormController implements Initializable {

    @FXML
    public Label lblCustomerName;
    public TextField txtItemName;
    public Label lblOrderId;

    @FXML
    private Button addToCartButton;

    @FXML
    private TableView<CartTm> cartTable;

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

    private double total = 0.0;


    private String orderId ="";


    private final List<CartTm> cartList = new ArrayList<>();

    private final CustomerBo customer = new CustomerBoImpl();

    private final OrderBo orderBo = new OrderBoImpl();
    private final ItemBo itemBo = new ItemBoImpl();

    private List<CustomerDto> customerList = new ArrayList<>();
    private List<ItemDto> itemList = new ArrayList<>();
    private String customerId;


    @FXML
    void addToCart(ActionEvent event) {
        double amount = Double.parseDouble(unitPrice.getText()) * Integer.parseInt(quantity.getText());
        JFXButton deleteButton = new JFXButton();
        deleteButton.setText("Delete");
        deleteButton.setMaxHeight(0.0);
        deleteButton.setTextFill(Color.RED);
        deleteButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));


        CartTm cartItem = new CartTm(
                    cmbItem.getValue().getCode(),
                    txtItemName.getText(),
                    Double.parseDouble(unitPrice.getText()),
                    Integer.parseInt(quantity.getText()),
                    amount,
                    deleteButton
        );


        deleteButton.setOnAction(actionEvent -> {
            cartList.remove(cartItem);
            total -= cartItem.getAmount();
            totalLabel.setText(String.format("%.2f", total));
            cartTable.setItems(FXCollections.observableArrayList(cartList));

        });

        boolean isExsists = false;

        for (CartTm item : cartList) {
            if (item.getItemId().equals(cartItem.getItemId()) && (item.getUnitPrice() == cartItem.getUnitPrice())) {
                item.setQty(item.getQty() + cartItem.getQty());
                isExsists = true;
            }
        }

        if (isExsists) {

            total += cartItem.getAmount();
            totalLabel.setText(String.format("%.2f", total));
            cartTable.refresh();
            return;

        }

        cartList.add(cartItem);
        total += cartItem.getAmount();
        totalLabel.setText(String.format("%.2f", total));
        cartTable.setItems(FXCollections.observableArrayList(cartList));


    }




    @FXML
    void placeOrder(ActionEvent event) {

        List<OrderDetailsDto> orderDetailsList = new ArrayList<>();

        for (CartTm cartTm : cartList) {
            orderDetailsList.add(
                    new OrderDetailsDto(
                            orderId,
                            cartTm.getItemId(),
                            cartTm.getQty(),
                            cartTm.getUnitPrice()
                    )
            );
        }

        try {
         boolean isOrderSaved =  orderBo.saveOrder(
            new OrderDto(
                 orderId,
                 DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()),
                 customerId,
                 orderDetailsList
                    ));

         if(isOrderSaved){
             new Alert(Alert.AlertType.INFORMATION, "Order placed").show();
         }else{
             new Alert(Alert.AlertType.ERROR, "Order placed Failed").show();
         }



        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Order placed Failed").show();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colOptions.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        getAllCustomersName();
        getAllItemsCode();
        generateOrderId();

        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
                {
                    lblCustomerName.setText(newValue.getName());
                    customerId = newValue.getId();
                }


        );


        cmbItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
                {

                    txtItemName.setText(newValue.getName());
                    unitPrice.setText(String.format("%.2f", newValue.getPrice()));
                }


        );

    }

    private void generateOrderId() {

        try {
            String orderId =  orderBo.generateOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getAllItemsCode() {

        try {
            itemList = itemBo.getAllItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<ItemDto> itemCodeList = FXCollections.observableArrayList(itemList);


        cmbItem.setItems(itemCodeList);

    }

    private void getAllCustomersName() {

        try {
            customerList = customer.getAllCustomers();


        ObservableList<CustomerDto> customerIdList = FXCollections.observableArrayList(customerList);


        cmbCustomer.setItems(customerIdList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
