package by.online.pharmacy.service;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product) throws ServiceException;

    Product findProduct(int id);

    List<Product> findProductsByName(String productName);

    List<Product> findProductsByCategory(String categoryName);

    List<Product> findProductsByManufacture(String manufactureName);

    List<Product> findProductsCheaperThan(long price);

    List<Product> findProductInCategoryCheaperThan(long price, String categoryName);

    List<Product> findProductInCategory(long lowerPrice , long topPrice);

    void updateProduct(Product product);

    void deleteProduct(int id);

    void deleteProduct(Product product);











}
