package by.online.pharmacy.dao.impl;
import java.io.Closeable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WrappedConnection implements Closeable {

    private java.sql.Connection connection;

    public WrappedConnection(java.sql.Connection connection){
        this.connection = connection;
    }

    @Override
    public void close(){
        ConnectionPoolImpl.getInstance().returnConnection(connection);
    }

    public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }




}
