package by.online.pharmacy.dao.factory;
import by.online.pharmacy.dao.CustomerDAO;

import by.online.pharmacy.dao.impl.CustomerDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final CustomerDAO customerDao = new CustomerDAOImpl();


    public CustomerDAO getCustomerDao(){
        return customerDao;
    }

    private DAOFactory() {}



    public static DAOFactory getInstance() {
        return instance;
    }
}
