package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface DAO<T> {

    boolean save(T t) throws DAOException;

    T get(Object key) throws DAOException;

    boolean update(T t) throws DAOException;

    boolean delete(Object key) throws DAOException;

    List<T> getAll() throws DAOException;



}
