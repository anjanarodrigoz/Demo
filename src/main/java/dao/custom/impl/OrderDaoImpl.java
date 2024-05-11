package dao.custom.impl;

import db.Database;
import dto.OrderDto;
import dao.custom.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException {

        Connection connection = Database.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            String sql = "INSERT INTO orders VALUES(?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, orderDto.getOrderId());
            pstm.setString(2, orderDto.getDate());
            pstm.setString(3, orderDto.getCustomerId());
            if (pstm.executeUpdate() > 0) {
                boolean isDetailsSaved = new OrderDetailsDaoImpl().saveOrderDetails(orderDto.getOrderDetails());
                if (isDetailsSaved) {
                    connection.commit();
                    return true;
                }
            }

            return false;

        } catch (SQLException ex) {
            connection.rollback();
            System.out.println(ex);
            return false;

        } finally {
            connection.setAutoCommit(true);

        }
    }



    @Override
    public OrderDto lastOrder() throws SQLException {

        String sql = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";
        PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
           return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }

        return null;
    }
}
