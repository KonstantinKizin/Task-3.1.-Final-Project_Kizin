package by.online.pharmacy.service.factory;

import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.impl.CustomerServiceImpl;
import by.online.pharmacy.service.validator.Validator;
import by.online.pharmacy.service.validator.impl.ValidatorImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final Validator validator = new ValidatorImpl();

    private ServiceFactory() {}


    public CustomerService getCustomerService(){
        return customerService;
    }


    public Validator getValidator(){return validator; }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
