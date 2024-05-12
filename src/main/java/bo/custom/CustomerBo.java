package bo.custom;

import bo.SuperBo;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {

    boolean saveCustomer(CustomerDto dto) throws SQLException;

    boolean deleteCustomer(String CustomerId) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;

    List<CustomerDto> getAllCustomers() throws SQLException;
}
