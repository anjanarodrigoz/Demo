package bo.custom;

import bo.SuperBo;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo{

    boolean saveItem(ItemDto dto) throws SQLException;

    boolean updateItem(ItemDto dto) throws SQLException;

    boolean deleteItem(String itemCode) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;
}
