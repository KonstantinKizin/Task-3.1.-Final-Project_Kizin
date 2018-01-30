package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Customer;

import java.util.List;

public interface CustomerDAO  {

    boolean save(Customer customer) throws DAOException;

    Customer findCustomerByEmailAndPw(String email , String password) throws DAOException;

    Customer get(String email) throws DAOException;

    List<Customer> getAll() throws DAOException;

    void update(Customer customer) throws DAOException;
}
