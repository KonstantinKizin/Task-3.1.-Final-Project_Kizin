package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final DAOFactory factory = DAOFactory.getInstance();

    private final ProductDAO productDAO  = factory.getProductDAO();


    @Override
    public void saveProduct(Product product) throws ServiceException {

        try {
            productDAO.save(product);
        } catch (DAOException e) {
            throw new ServiceException("Exception in service save product method",e);
        }

    }

    @Override
    public Product findProduct(int id) throws ServiceException {

        try {
            return productDAO.get(id);
        } catch (DAOException e) {
            throw new ServiceException("Find product by id method",e);
        }
    }


    @Override
    public List<Product> findProductsByName(String productName) {
        return null;
    }

    @Override
    public List<Product> findProductsByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Product> findProductsByManufacture(String manufactureName) {
        return null;
    }

    @Override
    public List<Product> findProductsCheaperThan(long price) {
        return null;
    }

    @Override
    public List<Product> findProductInCategoryCheaperThan(long price, String categoryName) {
        return null;
    }

    @Override
    public List<Product> findProductInCategory(long lowerPrice, long topPrice) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(String language) throws ServiceException {
        try {
            return productDAO.getAll(language);
        } catch (DAOException e) {
            throw new ServiceException("Get All products by language method",e);
        }
    }

    @Override
    public List<Product> getAllProduct() throws ServiceException {
        try {
            return productDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("getAllProduct method ",e);
        }
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(int id) throws ServiceException {

        try {
            productDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Delete product in service ",e);
        }

    }


}
