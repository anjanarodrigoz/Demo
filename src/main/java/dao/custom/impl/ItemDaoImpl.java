package dao.custom.impl;

import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import db.Database;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {



    @Override
    public ItemDto getItem(String ItemCode) throws SQLException {

        String sql = "SELECT FROM item WHERE id =?";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        return new ItemDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getDouble(4)
        );

    }



    @Override
    public boolean save(Item entity) throws SQLException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return  true;


    }

    @Override
    public boolean update(Item entity) throws SQLException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.find(Item.class,entity.getId());
        item.setName(entity.getName());
        item.setQty(entity.getQty());
        item.setPrice(entity.getPrice());
        session.update(item);
        transaction.commit();
        session.close();
        return  true;

    }

    @Override
    public boolean delete(String value) throws SQLException {

return false;
    }

    @Override
    public List<Item> getAll() throws SQLException {

        Session session = HibernateUtil.getSession();
        Query fromItem = session.createQuery("From Item");
        List<Item> itemList = fromItem.list();

        return itemList;
    }
}
