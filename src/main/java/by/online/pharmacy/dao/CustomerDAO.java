package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Customer;

public interface CustomerDAO extends DAO<Customer> {

    Customer findCustomerByEmailAndPw(String email , String password) throws DAOException;

    Customer get(String email) throws DAOException;
}
