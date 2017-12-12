package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final DAOFactory factory = DAOFactory.getInstance();
    private final CustomerDAO customerDAO = factory.getCustomerDao();
    private final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
    private final String EMAIL_REQUEST_PARAMETER = "email";
    private final String PW_REQUEST_PARAMETER = "password";

    @Override
    public boolean saveCustomer(Customer customer) throws ServiceException {// куда дел валидацию в сервисах
        // мне уже про нее надоело и говорить и писать
        try {
            return customerDAO.save(customer);
        } catch (DAOException e) {
            logger.debug("Exception from Service , saveCustomer method",e);// лог уровня debug в catch-ах не бывает
            // плюс зачем ты одно и тоже сообщение по нескольку раз логгируешь
            // у тебя же уже на слое дао логгирование есть
            // сообщение в лог записывается один раз
            // либо при возникновении исключения
            // либо при его полном гашении
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer findCustomerByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            return customerDAO.findCustomerByEmailAndPw(email,password);
        } catch (DAOException e) {
            logger.error("Exception from Service , findCustomerByEmailAndPassword method",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws ServiceException {
        try {
            return customerDAO.getAll();
        } catch (DAOException e) {
            logger.error("Exception From Service in getAllCustomers method ",e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean LoginValidate(ServletRequest request){

        String email = request.getParameter(EMAIL_REQUEST_PARAMETER);
        String pw = request.getParameter(PW_REQUEST_PARAMETER);

        if(email != null && pw != null) {
            if (email.isEmpty() || pw.isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
