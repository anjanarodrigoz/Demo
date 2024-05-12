package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException {

       return orderDao.save(orderDto);

    }

    @Override
    public String generateOrderId() throws SQLException {

        OrderDto orderDto = orderDao.lastOrder();
        if (orderDto != null) {
            int num = Integer.parseInt(orderDto.getOrderId().split("D")[1]);
            num++;
            return String.format("D%03d", num);


        } else {
            return  "D001";

        }


    }
}
