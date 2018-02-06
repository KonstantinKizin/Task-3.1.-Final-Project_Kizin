package by.online.pharmacy.dao.impl.connectionPool;

import by.online.pharmacy.dao.exception.CloseConnectionException;
import by.online.pharmacy.dao.exception.ConnectionPoolException;
import by.online.pharmacy.dao.exception.ConnectionPoolInitializationException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public final class ConnectionPoolImpl implements ConnectionPool {

    private final static Logger logger = Logger.getLogger(ConnectionPoolImpl.class);

    private final static String PROPERTY_NAME = "JDBCconfig";

    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(PROPERTY_NAME);

    private final static int CONNECTION_POOL_SIZE = Integer.parseInt(
            RESOURCE_BUNDLE.getString(JDBCProperty.CONNECTION_POOL_SIZE.name())
    );

    private static BlockingQueue<Connection> availableConnections;

    private static final ConnectionPool instance = new ConnectionPoolImpl();

    private ConnectionPoolImpl()  {

    }

    public static ConnectionPool getInstance(){
        return instance;
    }

    @Override
    public void init(){
        availableConnections = new ArrayBlockingQueue<Connection>( CONNECTION_POOL_SIZE);
        try {
            Class.forName(RESOURCE_BUNDLE.getString(JDBCProperty.DB_DRIVER_NAME.name()));
            makeConnectionsQueue(CONNECTION_POOL_SIZE);
        } catch (ClassNotFoundException | ConnectionPoolException | SQLException e ) {
            logger.error("Error in initialize ConnectionPool",e);
            throw new ConnectionPoolInitializationException("Error in initialize WrappedConnection Pool",e);
        }
    }


    @Override
    public Connection getConnection() throws ConnectionPoolException {

        Connection connection = null;
        try{
            if(availableConnections.isEmpty()){
                connection = createConnection();
            }else {
                connection = availableConnections.take();
            }
            return connection;
        }catch (SQLException  | InterruptedException e ){
            throw new ConnectionPoolException(e);
        }
    }



    @Override
    public boolean returnConnection(Connection connection) throws ConnectionPoolException {
        try{
            if(connection != null){
                if(availableConnections.size() < CONNECTION_POOL_SIZE){
                    availableConnections.put(connection);
                    return true;
                }else {
                    try{
                        connection.close();
                        return true;
                    }catch (SQLException e){
                        logger.error("Exception while close Connection in returnConnection method");
                        throw new ConnectionPoolException(e);
                    }
                }
            }
            return false;
        }catch (InterruptedException e){
            logger.error("Exception in return connection method",e);
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public void closeAll(){

        if(!availableConnections.isEmpty()){
            try {
                for(Connection connection : availableConnections){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new CloseConnectionException(e);
            }
        }
    }



    private  void makeConnectionsQueue(int count) throws SQLException, ConnectionPoolException {
        for(int i = 0; i < count;i++){
            Connection connection = null;
            try {
                connection = createConnection();
                availableConnections.put(connection);
            } catch (SQLException  e) {
                throw e;
            }catch (InterruptedException e){
                logger.error("Make connection queue method , while put in available connections ",e);
                throw new ConnectionPoolException(e);
            }
        }
    }


    private Connection createConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(
                    RESOURCE_BUNDLE.getString(JDBCProperty.DB_URL.name()),
                    RESOURCE_BUNDLE.getString(JDBCProperty.DB_LOGIN.name()),
                    RESOURCE_BUNDLE.getString(JDBCProperty.DB_PASSWORD.name())
            );
            return connection;
        } catch (SQLException  e) {
            logger.error("Create Connection method exception ",e);
            throw e;
        }
    }
}
