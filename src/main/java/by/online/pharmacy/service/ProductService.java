package by.online.pharmacy.service;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.storage.ProductStorage;

import java.util.List;

public interface ProductService {

    int saveProduct(Product product) throws ServiceException;

    Product findProduct(int id)throws ServiceException;

    Product findProductsByName(String productName, String language)throws ServiceException;

    List<Product> findProductsByCategory(String categoryName , String language)throws ServiceException;

    List<Product> findProductsByManufacture(String manufactureName , String language )throws ServiceException;

    List<Product> findProductsCheaperThan(long price)throws ServiceException;

    List<Product> getAllProducts(String language) throws ServiceException;

    List<Product> getAllProduct() throws ServiceException;

    boolean updateProduct(Product product, String language)throws ServiceException;

    boolean deleteProduct(int id)throws ServiceException;

    ProductStorage getProductStorage();

    List<Product> getProducts(int start, int count);











}
