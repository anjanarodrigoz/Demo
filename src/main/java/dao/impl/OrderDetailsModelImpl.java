package dao.impl;

import db.Database;
import dto.OrderDetailsDto;
import dao.OrderDetailsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModelImpl implements OrderDetailsModel {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> orderDetailsDtoList) throws SQLException {

        boolean isDetailsUpdate = false;
        for(OrderDetailsDto dto : orderDetailsDtoList){
            String sql = "INSERT INTO order_details values(?,?,?,?)";

            PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,dto.getOrderId());
            pstm.setString(2,dto.getItemCode());
            pstm.setInt(3,dto.getQty());
            pstm.setDouble(4,dto.getUnitPrice());
            isDetailsUpdate =   pstm.executeUpdate()>0;
        }

        return isDetailsUpdate;
    }
}
