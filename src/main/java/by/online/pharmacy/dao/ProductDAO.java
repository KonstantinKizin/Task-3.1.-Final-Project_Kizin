package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {

    void delete(int id) throws DAOException;

    Product get(int id) throws DAOException;

    List<Product> getAll(String language) throws DAOException;

    List<Product> getAll() throws DAOException;

    void update(Product product , String language) throws DAOException;



}
