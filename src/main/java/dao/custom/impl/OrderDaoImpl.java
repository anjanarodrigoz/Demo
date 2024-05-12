package dao.custom.impl;

import dao.util.CrudUtil;
import db.Database;
import dto.OrderDto;
import dao.custom.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderDto orderDto) throws SQLException {

        Connection connection = Database.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            String sql = "INSERT INTO orders VALUES(?,?,?)";

            boolean isExecute = CrudUtil.execute(sql,
                    orderDto.getOrderId(),
                    orderDto.getDate(),
                    orderDto.getCustomerId());

            if (isExecute) {
                boolean isDetailsSaved = new OrderDetailsDaoImpl().save(orderDto.getOrderDetails());
                if (isDetailsSaved) {
                    connection.commit();
                    return true;
                }
            }

            return false;

        } catch (SQLException ex) {
            connection.rollback();

            return false;

        } finally {
            connection.setAutoCommit(true);

        }
    }

    @Override
    public boolean update(OrderDto entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException {
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException {
        return null;
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
