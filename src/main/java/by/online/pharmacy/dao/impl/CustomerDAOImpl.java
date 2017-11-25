package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ConnectionPool;
import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.model.Customer;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private final String TABLE_NAME = "customer";
    private final static Logger lOGGER = Logger.getLogger(CustomerDAOImpl.class);
    private final String SAVE_SQL_PREPARED_STATEMENT = "insert into "+TABLE_NAME+" values(?,?,?,?,?,?,?,?,?,?)";
    private final String SELECT_ALL_SQL = "select * from customer";
    private  String FIND_BY_EMAIL_AND_PW_SQL_STATEMENT = "SELECT * FROM "+TABLE_NAME+" WHERE email=? AND password=?";
    private final int NAME_COLUMN_INDEX = 1;
    private final int SURE_NAME_COLUMN_INDEX = 2;
    private final int PHONE_COLUMN_INDEX = 3;
    private final int ROLE_COLUMN_INDEX = 4;
    private final int EMAIL_COLUMN_INDEX = 5;
    private final int LOGIN_COLUMN_INDEX = 7;
    private final int PW_COLUMN_INDEX = 6;
    private final int DATE_COLUMN_INDEX = 8;
    private final int BIRTH_DAY_COLUMN_INDEX = 9;
    private final int GENDER_COLUMN_INDEX = 10;
    private final int EMAIL_PARAMETER_INDEX = 1;
    private final int PASSWORD_PARAMETER_INDEX = 2;



    @Override
    public boolean save(Customer customer) throws DAOException {
        Connection roolBacked = null;

        boolean result = false;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL_PREPARED_STATEMENT)
        ){
            statement.setString(NAME_COLUMN_INDEX,customer.getName());
            statement.setString(SURE_NAME_COLUMN_INDEX,customer.getSureName());
            statement.setString(PHONE_COLUMN_INDEX,customer.getPhoneNumber());
            statement.setString(ROLE_COLUMN_INDEX,customer.getRole());
            statement.setString(EMAIL_COLUMN_INDEX,customer.getEmail());
            statement.setString(PW_COLUMN_INDEX,customer.getPassword());
            statement.setString(LOGIN_COLUMN_INDEX, customer.getLogin());
            statement.setString(DATE_COLUMN_INDEX, customer.getRegistrationDate());
            statement.setString(BIRTH_DAY_COLUMN_INDEX,customer.getDateOfBirth());
            statement.setString(GENDER_COLUMN_INDEX,customer.getGender());
            statement.executeUpdate();
            roolBacked = connection;
            result = true;
        } catch (SQLException e) {
            lOGGER.debug("Exception from DAO, save method ",e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
            return result;
        }
    }


    @Override
    public List<Customer> getAll() throws DAOException {

        List<Customer> customers = new ArrayList<>();
        Connection roolBacked = null;
        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement() ) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_SQL);

            while (rs.next()){
                Customer customer = null;
                String name = rs.getString(NAME_COLUMN_INDEX);
                String sureName = rs.getString(SURE_NAME_COLUMN_INDEX);
                String phone = rs.getString(PHONE_COLUMN_INDEX);
                String role =  rs.getString(ROLE_COLUMN_INDEX);
                String email = rs.getString(EMAIL_COLUMN_INDEX);
                String password = rs.getString(PW_COLUMN_INDEX);
                String login = rs.getString(LOGIN_COLUMN_INDEX);
                String date = rs.getString(DATE_COLUMN_INDEX);
                String birthDay = rs.getString(BIRTH_DAY_COLUMN_INDEX);
                String gender = rs.getString(GENDER_COLUMN_INDEX);
                customer = new Customer(
                        name,sureName,date,login,password,
                        email,phone,role,birthDay,gender
                );
                customers.add(customer);
            }

            roolBacked = connection;
            return customers;
        } catch (SQLException e) {
            lOGGER.debug("Exception from DAO , getAll method ",e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
        }

    }



    @Override
    public Customer findCustomerByEmailAndPw(String emil, String pw) throws DAOException {
        Connection roolBacked = null;
        Customer customer = null;

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_AND_PW_SQL_STATEMENT)
        ){

            statement.setString(EMAIL_PARAMETER_INDEX,emil);
            statement.setString(PASSWORD_PARAMETER_INDEX,pw);
            ResultSet rs = statement.executeQuery();
            if(rs != null){
              customer = buildCustomer(rs);
            }
            roolBacked = connection;
            return customer;

        } catch (SQLException e) {
            lOGGER.debug("Exception from findByEmailAndPassword method" ,e);
            throw new DAOException(e);
        }finally {
            connectionPool.roleBack(roolBacked);
        }
    }


    private Customer buildCustomer(ResultSet rs) throws DAOException {
        Customer customer = null;
        try {
            while (rs.next()){

                String name = rs.getString(NAME_COLUMN_INDEX);
                String sureName = rs.getString(SURE_NAME_COLUMN_INDEX);
                String phone = rs.getString(PHONE_COLUMN_INDEX);
                String role =  rs.getString(ROLE_COLUMN_INDEX);
                String email = rs.getString(EMAIL_COLUMN_INDEX);
                String password = rs.getString(PW_COLUMN_INDEX);
                String login = rs.getString(LOGIN_COLUMN_INDEX);
                String date = rs.getString(DATE_COLUMN_INDEX);
                String birthDay = rs.getString(BIRTH_DAY_COLUMN_INDEX);
                String gender = rs.getString(GENDER_COLUMN_INDEX);
                customer = new Customer(
                name,sureName,date,login,password,
                email,phone,role,birthDay,gender
                );
            }
            return customer;
        } catch (SQLException e) {
            lOGGER.debug("Exception when Build Customer from buildCustomer method ",e);
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
