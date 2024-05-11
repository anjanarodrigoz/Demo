package dao.custom;

import dto.OrderDto;

import java.sql.SQLException;

public interface OrderDao {

    boolean saveOrder(OrderDto orderDto) throws SQLException;

    OrderDto lastOrder() throws SQLException;
}
