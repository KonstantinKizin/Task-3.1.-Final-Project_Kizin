package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.validator.CustomerValidator;
import by.online.pharmacy.service.validator.impl.CustomerValidatorImpl;
import org.apache.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final DAOFactory factory = DAOFactory.getInstance();
    private final CustomerDAO customerDAO = factory.getCustomerDao();
    private final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
    private final CustomerValidator validator = new CustomerValidatorImpl();
    private final  char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
    private final String ALGHORITHM = "SHA-1";

    @Override
    public void saveCustomer(Customer customer) throws ServiceException {
        try {
            if(!validator.registrationValidate(customer)){
                throw new ValidatorException("registration exception");
            }
            customerDAO.save(customer);
        } catch (DAOException e) {
            throw new ServiceException("exception from saveCustomer method",e);
        }
    }

    @Override
    public Customer findCustomerByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            Customer customer = null;
            if(!validator.loginValidate(email,password)){
                throw new ValidatorException("Invalid login date");
            }
            return customerDAO.findCustomerByEmailAndPw(email,password);
        } catch (DAOException e) {
            throw new ServiceException("exception from findCustomerByEmailAndPassword method",e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws ServiceException {
        try {
            return customerDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("exception from getAllCustomers method",e);
        }
    }


    @Override
    public String generateHashPassword(String pw) throws ServiceException {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance(ALGHORITHM);
            byte[] hashedBytes = sha.digest(pw.getBytes());
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(DIGITS[(b & 0xf0) >> 4]);
                hash.append(DIGITS[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Exception from creating generateHashPassword method",e);
        }
        return hash.toString();
    }


}
