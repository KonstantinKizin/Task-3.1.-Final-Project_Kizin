package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.model.Customer;

public interface CustomerDAO extends DAO<Customer> {

    Customer findCustomerByEmailAndPw(String emil , String pw) throws DAOException;

}
