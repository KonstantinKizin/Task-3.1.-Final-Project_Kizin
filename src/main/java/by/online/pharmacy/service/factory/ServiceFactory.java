package by.online.pharmacy.service.factory;

import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.impl.CommandServiceImpl;
import by.online.pharmacy.service.impl.CustomerServiceImpl;
import by.online.pharmacy.service.validator.Validator;
import by.online.pharmacy.service.validator.impl.ValidatorImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final CommandService commandService = new CommandServiceImpl();
    private final Validator validator = new ValidatorImpl();

    private ServiceFactory() {}


    public CustomerService getCustomerService(){
        return customerService;
    }

    public CommandService getCommandService(){
        return commandService;
    }

    public Validator getValidator(){return validator; }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
