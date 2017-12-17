package by.online.pharmacy.service.factory;

import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.impl.CustomerServiceImpl;
import by.online.pharmacy.service.impl.ProductServiceImpl;
import by.online.pharmacy.service.validator.CustomerValidator;
import by.online.pharmacy.service.validator.impl.CustomerValidatorImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();    private final CustomerService customerService = new CustomerServiceImpl();

    private final CustomerValidator validator = new CustomerValidatorImpl();

    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory() {}

    public CustomerService getCustomerService(){
        return customerService;
    }

    public CustomerValidator getValidator(){return validator; }

    public ProductService getProductService(){return productService; }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
