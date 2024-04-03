package model.impl;

import db.Database;
import dto.CustomerDto;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements Customer {

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
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException {

        String query = "INSERT INTO customer(id,name,mobileNumber,email) VALUES (?,?,?,?)";
        PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(query);

        stm.setString(1,customerDto.getId());
        stm.setString(2,customerDto.getName());
        stm.setString(3,customerDto.getMobileNumber());
        stm.setString(4,customerDto.getEmailAddress());

        return  stm.executeUpdate()>0;

    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {

        String query = "UPDATE customer SET name=?, mobileNumber=?, email=? WHERE id =?";

        PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(query);

        stm.setString(1,customerDto.getName());
        stm.setString(2,customerDto.getMobileNumber());
        stm.setString(3,customerDto.getEmailAddress());
        stm.setString(4,customerDto.getId());

        return  stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException {

        String query = "DELETE FROM customer WHERE id = ?";
        PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(query);
        stm.setString(1,customerId);

        return  stm.executeUpdate()>0;
    }

    @Override
    public List<CustomerDto> getAllCustomers()  {
        List<CustomerDto> customerDtoList = new ArrayList<>();

        String query = "SELECT * FROM customer";

        try {
            PreparedStatement stm = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet =  stm.executeQuery();
            while (resultSet.next()){

                customerDtoList.add(new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  customerDtoList;

    }
}
