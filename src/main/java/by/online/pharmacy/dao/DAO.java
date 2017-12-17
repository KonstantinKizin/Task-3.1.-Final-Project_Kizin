package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface DAO<T> {

    void save(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

    List<T> getAll() throws DAOException;



}
