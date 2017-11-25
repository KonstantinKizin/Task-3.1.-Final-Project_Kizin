package by.online.pharmacy.dao.factory;
import by.online.pharmacy.dao.AdminDAO;
import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.GoodsDAO;
import by.online.pharmacy.dao.CommandXMLDAO;

import by.online.pharmacy.dao.impl.AdminDAOImpl;
import by.online.pharmacy.dao.impl.CommandXmlDAOImpl;
import by.online.pharmacy.dao.impl.CustomerDAOImpl;
import by.online.pharmacy.dao.impl.GoodsDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final AdminDAO adminDao = new AdminDAOImpl();
    private final CustomerDAO customerDao = new CustomerDAOImpl();
    private final GoodsDAO goodsDao = new GoodsDAOImpl();
    private final CommandXMLDAO commandXMLDAO = new CommandXmlDAOImpl();

    public AdminDAO getAdminDao(){
        return adminDao;
    }

    public CustomerDAO getCustomerDao(){
        return customerDao;
    }

    public GoodsDAO getGoodsDao(){
        return goodsDao;
    }

    public CommandXMLDAO getCommandXMLDao(){
        return commandXMLDAO;
    }

    private DAOFactory() {}



    public static DAOFactory getInstance() {
        return instance;
    }
}
