package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ConnectionPool;
import by.online.pharmacy.dao.exception.ConnectionPoolException;
import by.online.pharmacy.dao.exception.ConnectionPoolInitializationException;
import by.online.pharmacy.dao.exception.DAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import static by.online.pharmacy.dao.constant.DaoConstant.JDBCProperty;


public final class ConnectionPoolImpl implements ConnectionPool {

    private final int CONNECTION_POOL_SIZE = JDBCProperty.CONNECTION_POOL_SIZE;

    private final Vector<Connection> availableConnections= new Vector<>(CONNECTION_POOL_SIZE);

    private final Vector<Connection> usedConnections = new Vector<>(CONNECTION_POOL_SIZE);

    private final static Logger logger = Logger.getLogger(ConnectionPoolImpl.class);

    private static final ConnectionPool instance = new ConnectionPoolImpl();



    private ConnectionPoolImpl()  {
        try {
            Class.forName(JDBCProperty.DB_DRIVER_NAME);
            makeConnectionsQueue(CONNECTION_POOL_SIZE);
        } catch (ClassNotFoundException | ConnectionPoolException e) {
            logger.error("Error in initialize Connection Pool",new DAOException(e));
            throw new ConnectionPoolInitializationException("Error in initialize Connection Pool",e);
        }

    }


    public static ConnectionPool getInstance(){
        return instance;
    }


    @Override
    public Connection getConnection() throws ConnectionPoolException {

        Connection connection = null;
        if(availableConnections.isEmpty()){
            connection = createConnection();
        }else {
            connection = availableConnections.firstElement();
            usedConnections.add(connection);
            availableConnections.remove(connection);
        }
        return connection;
    }



    @Override
    public boolean rollBack(Connection connection) {
        usedConnections.remove(connection);
        return availableConnections.add(connection);
    }



    private void makeConnectionsQueue(int count) throws ConnectionPoolException {
        for(int i = 0; i < count;i++){
            Connection connection = null;
            try {
                connection = createConnection();
                availableConnections.add(connection);
            } catch (ConnectionPoolException e) {
                logger.error("Exception in make connections method",e);
                throw new ConnectionPoolException(e);
            }

        }
    }


    private Connection  createConnection() throws ConnectionPoolException {
        try {
            Connection connection = DriverManager.getConnection(
                    JDBCProperty.DB_URL,
                    JDBCProperty.DB_LOGIN,
                    JDBCProperty.DB_PASSWORD
            );
            return connection;
        } catch (SQLException  e) {
            logger.error("create Connection exception ",e);
            throw new ConnectionPoolException(e);
        }
    }
}
