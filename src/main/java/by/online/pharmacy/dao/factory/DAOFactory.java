package by.online.pharmacy.dao.factory;
import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.CommandXMLDAO;

import by.online.pharmacy.dao.impl.CommandXmlDAOImpl;
import by.online.pharmacy.dao.impl.CustomerDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final CustomerDAO customerDao = new CustomerDAOImpl();
    private final CommandXMLDAO commandXMLDAO = new CommandXmlDAOImpl();


    public CustomerDAO getCustomerDao(){
        return customerDao;
    }

    public CommandXMLDAO getCommandXMLDao(){
        return commandXMLDAO;
    }

    private DAOFactory() {}



    public static DAOFactory getInstance() {
        return instance;
    }
}
