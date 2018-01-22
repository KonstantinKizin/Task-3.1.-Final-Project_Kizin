package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.entity.ValidationError;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.validator.CustomerValidator;
import by.online.pharmacy.service.validator.impl.CustomerValidatorImpl;
import org.apache.log4j.Logger;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final DAOFactory factory = DAOFactory.getInstance();
    private final CustomerDAO customerDAO = factory.getCustomerDao();
    private final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    private final CustomerValidator validator = new CustomerValidatorImpl();
    private final HashGenerator hashGenerator = new HashGenerator();


    @Override
    public List<ValidationError> saveCustomer(Customer customer) throws ServiceException {


        try {

            List<ValidationError> errors = validator.validate(customer);

//            if(errors.isEmpty()){
                customerDAO.save(customer);
//            }else {
//                throw new ValidatorException("Save customer");
//            }
            return errors;
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
        return this.hashGenerator.generateHashPassword(pw);
    }



}
