package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ConnectionPool;
import by.online.pharmacy.dao.exception.ConnectionPoolException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public final class ConnectionPoolImpl implements ConnectionPool {

    private final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final String POSTGRES_LOGIN = "postgres";
    private final String POSTGRES_PASSWORD = "481415";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/OnlinePharm";
    private final int CONNECTION_POOL_SIZE = 20;
    private Vector<Connection> availableConnections;
    private Vector<Connection> usedConnections;
    private static final ConnectionPool instance = new ConnectionPoolImpl();

    private ConnectionPoolImpl() {
        availableConnections = new Vector<>(CONNECTION_POOL_SIZE);
        usedConnections = new Vector<>(CONNECTION_POOL_SIZE);
        makeConnectionsQueu(CONNECTION_POOL_SIZE);
    }


    public static ConnectionPool getInstance(){
        return instance;
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {

        Connection connection = null;
        if(availableConnections.isEmpty()){
            connection = createConnection();
            return connection;
        }else {
            connection = availableConnections.firstElement();
            usedConnections.add(connection);
            availableConnections.remove(connection);
            return connection;
        }
    }



    @Override
    public boolean roleBack(Connection connection) {
        usedConnections.remove(connection);
        return availableConnections.add(connection);
    }



    private void makeConnectionsQueu(int count) throws ConnectionPoolException  {
        for(int i = 0; i < count;i++){
                Connection connection = createConnection();
                availableConnections.add(connection);
        }
    }


    private Connection  createConnection() throws ConnectionPoolException {
        try {
            Class.forName(DRIVER_CLASS_NAME);
            Connection connection = DriverManager.getConnection(DB_URL,POSTGRES_LOGIN,POSTGRES_PASSWORD);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e);
        }

    }
}
