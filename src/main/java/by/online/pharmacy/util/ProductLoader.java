package by.online.pharmacy.util;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import java.util.List;

public class ProductLoader {

    private final static  ProductLoader INSTANCE = new ProductLoader();

    private final static ServiceFactory FACTORY = ServiceFactory.getInstance();

    private final static ProductService PRODUCT_SERVICE = FACTORY.getProductService();

    private static List<Product> productList = null;

    private ProductLoader(){};

    static {
        try {
            productList = PRODUCT_SERVICE.getAllProduct();
        } catch (ServiceException e) {
            System.out.println(e);
        }
    }


    public static ProductLoader getInstance(){
        return INSTANCE;
    }

    public List<Product> getProductList(){
        return productList;
    }


}
