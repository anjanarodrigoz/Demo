package dao.custom.impl;

import dao.util.CrudUtil;
import db.Database;
import dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;

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
        String query = "INSERT INTO customer(id,name,mobileNumber,email) VALUES (?,?,?,?)";
       return CrudUtil.execute(query,
               entity.getId(),
               entity.getName(),
               entity.getMobileNumber(),
               entity.getEmail());
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        String query = "UPDATE customer SET name=?, mobileNumber=?, email=? WHERE id =?";

        return CrudUtil.execute(query,
                entity.getName(),
                entity.getMobileNumber(),
                entity.getEmail(),
                entity.getId());

    }

    @Override
    public boolean delete(String value) throws SQLException {


        String query = "DELETE FROM customer WHERE id = ?";


        return  CrudUtil.execute(query,value);

    }

    @Override
    public List<Customer> getAll() throws SQLException {

        List<Customer> customerList = new ArrayList<>();

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
