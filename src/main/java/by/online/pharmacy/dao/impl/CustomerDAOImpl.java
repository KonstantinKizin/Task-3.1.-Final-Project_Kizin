package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.CustomerDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.impl.connectionPool.ConnectionPool;
import by.online.pharmacy.dao.impl.connectionPool.ConnectionPoolImpl;
import by.online.pharmacy.dao.impl.connectionPool.WrappedConnection;
import by.online.pharmacy.entity.Customer;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final static Logger logger = Logger.getLogger(CustomerDAOImpl.class);

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private final static String TABLE_NAME = "customer";


    private final static String SQL_SAVE_PREPARED_STATEMENT = "insert into "+TABLE_NAME+" values(?,?,?,?,?,?,?,?,?,?)";

    private final static String SQL_GET_PREPARED_STATEMENT = "SELECT * from "+TABLE_NAME+" WHERE email=?";

    private final static String SQL_UPDATE_PREPARED_STATEMENT = "UPDATE "+TABLE_NAME+" " +
            "SET name=? , sure_name=? , phone=? , role=? , email=? ,  login=? , password=? " +
            "WHERE email=?";

    private final static String SQL_DELETE_PREPARED_STATEMENT = "DELETE FROM "+TABLE_NAME+"WHERE email=?";

    private final static String SELECT_ALL_SQL = "select * from customer";

    private final static String FIND_BY_EMAIL_AND_PW_SQL_STATEMENT = "SELECT * FROM "+TABLE_NAME+" WHERE email=? AND password=?";

    private final static int NAME_COLUMN_INDEX = 1;
    private final static int SURE_NAME_COLUMN_INDEX = 2;
    private final static int PHONE_COLUMN_INDEX = 3;
    private final static int ROLE_COLUMN_INDEX = 4;
    private final static int EMAIL_COLUMN_INDEX = 5;
    private final static int LOGIN_COLUMN_INDEX = 6;
    private final static int PW_COLUMN_INDEX = 7;
    private final static int DATE_COLUMN_INDEX = 8;
    private final static int BIRTH_DAY_COLUMN_INDEX = 9;
    private final static int GENDER_COLUMN_INDEX = 10;
    private final static int EMAIL_PARAMETER_INDEX = 1;
    private final static int PASSWORD_PARAMETER_INDEX = 2;



    @Override
    public void save(Customer customer) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(SQL_SAVE_PREPARED_STATEMENT)
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
        } catch (SQLException e) {
            logger.error("Exception from DAO, save Customer method ",e);
            throw new DAOException("Save Customer method",e);
        }
    }


    @Override
    public List<Customer> getAll() throws DAOException {

        List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(SELECT_ALL_SQL);
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
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            logger.error("Exception from DAO , getAll customers  method",e);
            throw new DAOException("getAll customers  method",e);
        }

    }

    @Override
    public Customer findCustomerByEmailAndPw(String emil, String pw) throws DAOException {
        Customer customer = null;
        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(FIND_BY_EMAIL_AND_PW_SQL_STATEMENT))
        {
            statement.setString(EMAIL_PARAMETER_INDEX,emil);
            statement.setString(PASSWORD_PARAMETER_INDEX,pw);
            ResultSet rs = statement.executeQuery();
            if(rs != null){
              customer = buildCustomer(rs);
            }
            return customer;
        } catch (SQLException e) {
            logger.error("Exception from findByEmailAndPassword method" ,e);
            throw new DAOException("From DAO, findByEmailAndPassword method ",e);
        }
    }

    @Override
    public Customer get(String email) throws DAOException {

        Customer customer = null;
        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(SQL_GET_PREPARED_STATEMENT)
        ){
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            customer = buildCustomer(resultSet);
            return customer;
        }catch (SQLException e){
            logger.error("Exception from get customer  method" ,e);
            throw new DAOException("From DAO, get customer method",e);
        }

    }


    private Customer buildCustomer(ResultSet rs) throws DAOException {
        Customer customer = null;
        try {
            while (rs.next()) {
                String name = rs.getString(NAME_COLUMN_INDEX);
                String sureName = rs.getString(SURE_NAME_COLUMN_INDEX);
                String phone = rs.getString(PHONE_COLUMN_INDEX);
                String role = rs.getString(ROLE_COLUMN_INDEX);
                String email = rs.getString(EMAIL_COLUMN_INDEX);
                String password = rs.getString(PW_COLUMN_INDEX);
                String login = rs.getString(LOGIN_COLUMN_INDEX);
                String date = rs.getString(DATE_COLUMN_INDEX);
                String birthDay = rs.getString(BIRTH_DAY_COLUMN_INDEX);
                String gender = rs.getString(GENDER_COLUMN_INDEX);
                customer = new Customer(
                        name, sureName, date, login, password,
                        email, phone, role, birthDay, gender
                );
            }
            return customer;
        } catch (SQLException e) {
            logger.error("Exception when Build Customer from buildCustomer method ",e);
            throw new DAOException("From DAO, buildCustomer method",e);
        }
    }



    @Override
    public void update(Customer customer) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(SQL_UPDATE_PREPARED_STATEMENT)
        ){
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getSureName());
            statement.setString(3,customer.getPhoneNumber());
            statement.setString(4,customer.getRole());
            statement.setString(5,customer.getEmail());
            statement.setString(6,customer.getLogin());
            statement.setString(7,customer.getPassword());
            statement.setString(8,customer.getEmail());
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error("Exception in update customer  method ",e);
            throw new DAOException("Update customer method error",e);
        }
    }

    @Override
    public void delete(Customer customer) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(SQL_DELETE_PREPARED_STATEMENT)
        ){
            statement.setString(1,customer.getEmail());
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error("Exception in delete customer  method ",e);
            throw new DAOException("Delete customer method",e);
        }

    }



}
