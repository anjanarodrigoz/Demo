package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo<CustomerDto> {

  private final CustomerDaoImpl customerDao = new CustomerDaoImpl();

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDao.save(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getMobileNumber(),
                dto.getEmailAddress()
        ));
    }

    @Override
    public boolean deleteCustomer(String value) throws SQLException {
        return customerDao.delete(value);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDao.update(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getMobileNumber(),
                dto.getEmailAddress()
        ));
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        List<Customer> customerEntityList = customerDao.getAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(Customer customer : customerEntityList){
          customerDtoList.add(  new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getMobileNumber(),
                customer.getEmail()
            ));

        }

        return customerDtoList;
    }


}
