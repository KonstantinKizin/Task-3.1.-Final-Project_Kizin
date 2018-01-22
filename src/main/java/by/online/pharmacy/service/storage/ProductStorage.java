package by.online.pharmacy.service.storage;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.exception.StorageException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ProductStorage {

    private final static DAOFactory factory = DAOFactory.getInstance();

    private final static ProductDAO productDAO  = factory.getProductDAO();

    private static Map<Integer,Product> productMap = new ConcurrentHashMap<>();

    private final static ProductStorage instance = new ProductStorage();

    private ProductStorage(){}

    static {
        try {
            List<Product> products =  productDAO.getAll();
            for(Product product: products){
                productMap.put(product.getId(),product);
            }



        } catch (DAOException e) {
            throw new StorageException("Exception while product list is loading",e);
        }
    }

    public  static ProductStorage getInstance(){
        return instance;
    }

    public Map<Integer,Product> getProductMap(){
        return productMap;
    }



}
