package dao.custom.impl;

import dao.util.CrudUtil;
import db.Database;
import dto.OrderDetailsDto;
import dao.custom.OrderDetailsDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    @Override
    public boolean save(List<OrderDetailsDto> entity) throws SQLException {

        boolean isDetailsUpdate = false;
        for(OrderDetailsDto dto : entity){
            String sql = "INSERT INTO order_details values(?,?,?,?)";
            isDetailsUpdate = CrudUtil.execute(sql,
                    dto.getOrderId(),
                    dto.getItemCode(),
                    dto.getQty(),
                    dto.getUnitPrice());
        }

        return isDetailsUpdate;
    }

    @Override
    public boolean update(List<OrderDetailsDto> entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException {
        return false;
    }

    @Override
    public List<List<OrderDetailsDto>> getAll() throws SQLException {
        return null;
    }
}
