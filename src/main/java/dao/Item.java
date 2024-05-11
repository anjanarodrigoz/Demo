package dao;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface Item {

    boolean saveItem(ItemDto dto) throws SQLException;
    boolean updateItem(ItemDto dto) throws SQLException;
    boolean deleteItem(String itemCode) throws SQLException;

    ItemDto getItem(String ItemCode) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;





}
