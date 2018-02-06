package by.online.pharmacy.service.factory;

import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.impl.CustomerServiceImpl;
import by.online.pharmacy.service.impl.ProductServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final CustomerService customerService = new CustomerServiceImpl();

    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory() {}

    public CustomerService getCustomerService(){
        return customerService;
    }


    public ProductService getProductService(){return productService; }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
