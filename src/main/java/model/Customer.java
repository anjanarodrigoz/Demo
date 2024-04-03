package model;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface Customer {

    CustomerDto getCustomer(String customerId) throws SQLException;

    boolean saveCustomer(CustomerDto customerDto) throws SQLException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(String customerId) throws SQLException;

    List<CustomerDto> getAllCustomers();
}
