package dao.custom;

import dao.CrudDao;
import dto.OrderDto;
import entity.Orders;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;

public interface OrderDao  extends CrudDao<OrderDto> {
    OrderDto lastOrder() throws SQLException;
}
