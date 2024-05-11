package bo.custom;

import bo.SuperBo;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo<T> extends SuperBo{

    boolean saveItem(T dto) throws SQLException;

    boolean updateItem(T dto) throws SQLException;

    boolean deleteItem(String itemCode) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;
}
