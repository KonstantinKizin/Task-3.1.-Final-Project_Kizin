package by.online.pharmacy.dao;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();

    boolean roleBack(Connection connection);

}
