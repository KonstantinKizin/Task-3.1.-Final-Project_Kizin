package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ConnectionPool;
import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Customer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private final String TABLE_NAME = "customer";
    private final static Logger logger = Logger.getLogger(CustomerDAOImpl.class);


    @Override
    public boolean save(Customer customer) throws DAOException {
        String sql = "insert into "+TABLE_NAME+" values(?,?,?,?,?,?,?,?,?,?)";
        Connection roolBacked = null;

        boolean result = false;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getSureName());
            statement.setString(3,customer.getPhoneNumber());
            statement.setString(4,customer.getRole());
            statement.setString(5,customer.getEmail());
            statement.setString(6,customer.getPassword());
            statement.setString(7, customer.getLogin());
            statement.setString(8, customer.getRegistrationDate());
            statement.setString(9,customer.getDateOfBirth());
            statement.setString(10,customer.getGender());
            statement.executeUpdate();
            roolBacked = connection;
            result = true;
        } catch (SQLException e) {
            logger.debug("Exception from DAO, save method ",e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
            return result;
        }
    }


    @Override
    public List<Customer> getAll() throws DAOException {

        List<Customer> customers = new ArrayList<>();
        final String sql = "select * from customer";
        Connection roolBacked = null;
        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement() ) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Customer customer = null;
                String name = rs.getString(1);
                String sureName = rs.getString(2);
                String phone = rs.getString(3);
                String role =  rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String login = rs.getString(7);
                String date = rs.getString(8);
                String birthDay = rs.getString(9);
                String gender = rs.getString(10);
                customer = new Customer(
                        name,sureName,date,login,password,
                        email,phone,role,birthDay,gender
                );
                customers.add(customer);
            }

            roolBacked = connection;
            return customers;
        } catch (SQLException e) {
            logger.debug("Exception from DAO , getAll method ",e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
        }

    }



    @Override
    public Customer findCustomerByEmailAndPw(String emil, String pw) throws DAOException {

        final String sqlQuery = "select * from "+TABLE_NAME+" where email = ? and password=?";
        Connection roolBacked = null;
        Customer customer = null;

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)
        ){
            statement.setString(1,emil);
            statement.setString(2,pw);
            ResultSet rs = statement.executeQuery();
            if(rs != null){
              customer = buildCustomer(rs);
            }
            roolBacked = connection;
            return customer;

        } catch (SQLException e) {
            logger.debug("Exception from DAO , findByEmailAndPassword method" ,e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
        }
    }


    private Customer buildCustomer(ResultSet rs) throws DAOException {
        Customer customer = null;
        try {
            while (rs.next()){

                String name = rs.getString(1);
                String sureName = rs.getString(2);
                String phone = rs.getString(3);
                String role =  rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String login = rs.getString(7);
                String date = rs.getString(8);
                String birthDay = rs.getString(9);
                String gender = rs.getString(10);
                customer = new Customer(
                name,sureName,date,login,password,
                email,phone,role,birthDay,gender
                );
            }
            return customer;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Customer get(Object key) throws DAOException {
        return null;
    }


    @Override
    public boolean update(Customer customer) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Object key) throws DAOException {
        return false;
    }

}
