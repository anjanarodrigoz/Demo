package model;

import dto.OrderDto;

import java.sql.SQLException;

public interface OrderModel {

    boolean saveOrder(OrderDto orderDto) throws SQLException;

    OrderDto lastOrder() throws SQLException;
}
