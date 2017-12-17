package by.online.pharmacy.dao;

import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Product;

public interface ProductDAO extends DAO<Product> {

    void delete(int id) throws DAOException;

    Product get(int id, String locale) throws DAOException;

}
