package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.GoodsDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Goods;

public class GoodsDAOImpl implements GoodsDAO {
    @Override
    public boolean save(Goods goods) throws DAOException {
        return false;
    }

    @Override
    public Goods get(Object key) throws DAOException {
        return null;
    }


    @Override
    public boolean update(Goods goods) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Object key) throws DAOException {
        return false;
    }
}
