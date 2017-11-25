package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.ConnectionPoolException;
import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException;

    boolean roleBack(Connection connection);

}
