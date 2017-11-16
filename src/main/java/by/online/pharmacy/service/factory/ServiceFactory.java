package by.online.pharmacy.service.factory;

import by.online.pharmacy.service.AdminService;
import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.GoodsService;
import by.online.pharmacy.service.impl.AdminServiceImpl;
import by.online.pharmacy.service.impl.CommandServiceImpl;
import by.online.pharmacy.service.impl.CustomerServiceImpl;
import by.online.pharmacy.service.impl.GoodsServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final AdminService adminService = new AdminServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final GoodsService goodsService = new GoodsServiceImpl();
    private final CommandService commandService = new CommandServiceImpl();

    private ServiceFactory() {}


    public AdminService getAdminService(){
        return adminService;
    }

    public CustomerService getCustomerService(){
        return customerService;
    }

    public GoodsService getGoodsService(){
        return goodsService;
    }

    public CommandService getCommandService(){
        return commandService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
