package by.online.pharmacy.listener;

import by.online.pharmacy.controller.command.CommandMapCreator;
import by.online.pharmacy.dao.exception.CloseConnectionException;
import by.online.pharmacy.dao.exception.ConnectionPoolInitializationException;
import by.online.pharmacy.dao.impl.connectionPool.ConnectionPoolImpl;
import by.online.pharmacy.service.storage.ProductStorage;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;


public class ServletContextListener implements javax.servlet.ServletContextListener {

    private final static Logger logger = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{
            ConnectionPoolImpl.getInstance().init();
            logger.info("Connections initialized");
            ProductStorage.getInstance().init();
            logger.info("Products initialized");

        }catch (ConnectionPoolInitializationException e){
            logger.error("Connection pool init error",e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPoolImpl.getInstance().closeAll();
            logger.info("Connections closed");
        } catch (CloseConnectionException e) {
            logger.error("Connection pool connection close error",e);
        }
    }
}
