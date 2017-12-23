package by.online.pharmacy.dao.factory;
import by.online.pharmacy.dao.CustomerDAO;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.impl.CustomerDAOImpl;
import by.online.pharmacy.dao.impl.ProductDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final CustomerDAO customerDao = new CustomerDAOImpl();

    private final ProductDAO productDAO = new ProductDAOImpl();

    public CustomerDAO getCustomerDao(){
        return customerDao;
    }

    public ProductDAO getProductDAO(){return productDAO; }

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }
}
