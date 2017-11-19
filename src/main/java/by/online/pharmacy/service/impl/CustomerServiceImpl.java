package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;

public class CustomerServiceImpl implements CustomerService {

    private final DAOFactory factory = DAOFactory.getInstance();
    private final CustomerDAO customerDAO = factory.getCustomerDao();

    @Override
    public boolean saveCustomer(Customer customer) throws ServiceException {
        try {
            return customerDAO.save(customer);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer findCustomerByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            return customerDAO.findCustomerByEmailAndPw(email,password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
