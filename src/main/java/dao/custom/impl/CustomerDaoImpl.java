package dao.custom.impl;

import dao.util.CrudUtil;
import db.Database;
import dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return  true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
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

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, value);
        session.delete(customer);
        transaction.commit();
        session.close();
        return true;



    }

    @Override
    public List<Customer> getAll() throws SQLException {

        List<Customer> customerList = new ArrayList<>();

//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Customer.class);
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();


        String query = "SELECT * FROM customer";

            ResultSet resultSet =  CrudUtil.execute(query);

            while (resultSet.next()){

                customerList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));

            }





        return  customerList;
    }
}
