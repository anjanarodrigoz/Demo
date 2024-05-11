package bo.custom;

import bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo<T> extends SuperBo {

    boolean saveCustomer(T dto) throws SQLException;

    boolean deleteCustomer(String value) throws SQLException;

    boolean updateCustomer(T dto) throws SQLException;

    List<T> getAllCustomers() throws SQLException;
}
