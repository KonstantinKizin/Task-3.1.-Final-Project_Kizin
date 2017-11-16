package by.online.pharmacy.entity;


import by.online.pharmacy.service.CommandService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

public class test {

    public static void main(String[] args) throws ServiceException {


        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        CommandService commandService = serviceFactory.getCommandService();

        System.out.println(commandService.getCommandMap());







    }
}
