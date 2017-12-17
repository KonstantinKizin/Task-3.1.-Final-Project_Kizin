package by.online.pharmacy.service;

import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface CustomerService {

    void saveCustomer(Customer customer) throws ServiceException;
    Customer findCustomerByEmailAndPassword(String email , String password) throws ServiceException;
    List<Customer> getAllCustomers() throws ServiceException;
    String generateHashPassword(String pw) throws ServiceException;
}
