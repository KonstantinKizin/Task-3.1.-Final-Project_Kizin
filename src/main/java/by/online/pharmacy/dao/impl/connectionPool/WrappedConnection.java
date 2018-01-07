package by.online.pharmacy.dao.impl.connectionPool;

import by.online.pharmacy.dao.exception.CloseConnectionException;
import by.online.pharmacy.dao.exception.ConnectionPoolException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WrappedConnection implements Closeable {

    private java.sql.Connection connection;

    public WrappedConnection(java.sql.Connection connection){
        this.connection = connection;
    }

    @Override
    public void close() throws CloseConnectionException{
        try{
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }catch (ConnectionPoolException e){
            throw new CloseConnectionException(e);

        }

    }

    public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public void setAutoCommit(boolean flag) throws SQLException {
        connection.setAutoCommit(flag);
    }


    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }






}
