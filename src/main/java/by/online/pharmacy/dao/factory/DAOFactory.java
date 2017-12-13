package by.online.pharmacy.dao.factory;
import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.XmlDAO;

import by.online.pharmacy.dao.impl.XmlDAOImpl;
import by.online.pharmacy.dao.impl.CustomerDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final CustomerDAO customerDao = new CustomerDAOImpl();
    private final XmlDAO commandXMLDAO = new XmlDAOImpl();


    public CustomerDAO getCustomerDao(){
        return customerDao;
    }

    public XmlDAO getCommandXMLDao(){
        return commandXMLDAO;
    }

    private DAOFactory() {}



    public static DAOFactory getInstance() {
        return instance;
    }
}
