package dao.custom.impl;

import dao.util.CrudUtil;
import dao.util.HibernateUtil;
import db.Database;
import dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public CustomerDto getCustomer(String customerId) throws SQLException {

        String query = "SELECT * FROM customer WHERE id = "+ customerId;
        PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet =  stm.executeQuery();

            return  new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
    }


    @Override
    public boolean save(Customer entity) throws SQLException {


        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return  true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {


        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.find(Customer.class, entity.getId());
        customer.setName(entity.getName());
        customer.setMobileNumber(entity.getMobileNumber());
        customer.setEmail(entity.getEmail());
        session.update(customer);
        transaction.commit();
        session.close();
        return true;



    }

    @Override
    public boolean delete(String value) throws SQLException {


        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, value);
        session.delete(customer);
        transaction.commit();
        session.close();
        return true;



    }

    @Override
    public List<Customer> getAll() throws SQLException {



        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("From Customer");
        List<Customer> customerList = query.list();


        return  customerList;
    }
}
