package by.online.pharmacy.service;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product) throws ServiceException;

    Product findProduct(int id)throws ServiceException;

    List<Product> findProductsByName(String productName)throws ServiceException;

    List<Product> findProductsByCategory(String categoryName)throws ServiceException;

    List<Product> findProductsByManufacture(String manufactureName)throws ServiceException;

    List<Product> findProductsCheaperThan(long price)throws ServiceException;

    List<Product> findProductInCategoryCheaperThan(long price, String categoryName)throws ServiceException;

    List<Product> findProductInCategory(long lowerPrice , long topPrice)throws ServiceException;

    List<Product> getAllProducts(String language) throws ServiceException;

    List<Product> getAllProduct() throws ServiceException;

    void updateProduct(Product product)throws ServiceException;

    void deleteProduct(int id)throws ServiceException;













}
