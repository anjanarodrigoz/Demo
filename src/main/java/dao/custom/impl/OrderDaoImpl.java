package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import db.Database;
import dto.OrderDetailsDto;
import dto.OrderDto;
import dao.custom.OrderDao;
import entity.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderDto orderDto) throws SQLException {

//        Connection connection = Database.getInstance().getConnection();
//
//        try {
//
//            connection.setAutoCommit(false);
//
//            String sql = "INSERT INTO orders VALUES(?,?,?)";
//
//            boolean isExecute = CrudUtil.execute(sql,
//                    orderDto.getOrderId(),
//                    orderDto.getDate(),
//                    orderDto.getCustomerId());
//
//            if (isExecute) {
//                boolean isDetailsSaved = new OrderDetailsDaoImpl().save(orderDto.getOrderDetails());
//                if (isDetailsSaved) {
//                    connection.commit();
//                    return true;
//                }
//            }
//
//            return false;
//
//        } catch (SQLException ex) {
//            connection.rollback();
//
//            return false;
//
//        } finally {
//            connection.setAutoCommit(true);
//
//        }


        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Orders order = new Orders(
                orderDto.getOrderId(),
                orderDto.getDate()
        );

        order.setCustomer(session.find(Customer.class,orderDto.getCustomerId()));
        session.save(order);

        List<OrderDetailsDto> orderDetailsDtoList = orderDto.getOrderDetails();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for(OrderDetailsDto dto :orderDetailsDtoList){
            OrderDetails orderDetail = new OrderDetails(
                    new OrderDetailKey(
                            dto.getItemCode(),
                            dto.getOrderId(),
                            dto.getUnitPrice()),
                    dto.getQty(),
                    session.find(Item.class,dto.getItemCode()),
                    order

            );
            session.save(orderDetail);
            orderDetailsList.add(orderDetail);

        }

        order.setOrdersDetailList(orderDetailsList) ;


        transaction.commit();
        session.close();
        return true;
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

        Query<Orders> query = HibernateUtil.getSession().createQuery("FROM Orders o ORDER BY o.id DESC");
         // Limit the result to the first one
        Orders orders = query.setMaxResults(1).uniqueResult();

        if(orders==null){
            return null;
        }
        return new OrderDto(
                orders.getId(),
                orders.getDate(),
                orders.getCustomer().getId(),
                null
        );
    }
}
