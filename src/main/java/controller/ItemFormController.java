package controller;

import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.impl.ItemImpl;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private TableView<ItemDto> itemTable;

    @FXML
    private TextField quantityField;


    private final Item item = new ItemImpl();

    private final ObservableList<ItemDto> itemList = FXCollections.observableArrayList();

    @FXML
    private void addItem(ActionEvent event) {

        ItemDto itemDto =  getValues();
        try {
           if( item.saveItem(itemDto)){
               loadItem();
               new Alert(Alert.AlertType.INFORMATION,"Item saved Successfully").show();
           }else{
               new Alert(Alert.AlertType.ERROR,"Item saved Failed").show();


           }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Item saved Failed").show();
        }

    }

    private ItemDto getValues() {

        return new ItemDto(
                itemCodeField.getText(),
                itemNameField.getText(),
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(itemPriceField.getText())
        );
    }

    @FXML
    private void deleteItem(ActionEvent event) {

        ItemDto dto = getValues();

        try {
            if(item.deleteItem(dto.getCode())){
                loadItem();
                new Alert(Alert.AlertType.INFORMATION,"Item delete Successfully").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Item delete Failed").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Item delete Failed").show();
        }

    }

    @FXML
    private void updateItem(ActionEvent event) {

        ItemDto dto = getValues();

        try {
            if(item.updateItem(dto)){
                loadItem();
                new Alert(Alert.AlertType.INFORMATION,"Item updated Successfully").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Item updated Failed").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Item updated Failed").show();
        }

    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadItem();

        viewData();



    }

    private void viewData() {

        itemTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !itemTable.getSelectionModel().isEmpty()) {
                // Get selected item
                ItemDto itemDto = itemTable.getSelectionModel().getSelectedItem();

                // Access data
                itemNameField.setText(itemDto.getName());
                itemCodeField.setText(itemDto.getCode());
                itemPriceField.setText(itemDto.getPrice()+"");
                quantityField.setText(itemDto.getQty()+"");
            }
        });
    }

    private void loadItem() {
        itemList.clear();
        try {

         itemList.addAll(item.getAllItems());
         itemTable.setItems(itemList);

         }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
