package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SaveCustomerCommand implements Command {

    private  ServiceFactory factory = ServiceFactory.getInstance();
    private CustomerService service = factory.getCustomerService();
    private final String CUSTOMER_PAGE = "/WEB-INF/jsp/customer.jsp";
    private final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private final static Logger logger = Logger.getLogger(SaveCustomerCommand.class);
    private CommandReturn commandReturn = new CommandReturn();

    @Override
    public CommandReturn execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException{

        try {
            String name = request.getParameter("name");
            String sureName = request.getParameter("sureName");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String date = new Date().toString();
            String role = "customer";
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone");
            String birthDay = request.getParameter("birthDate");
            String gender = request.getParameter("gender");

            Customer customer = new Customer(name,sureName,date,login,password,
            email,phoneNumber, role,birthDay,gender);
            service.saveCustomer(customer);
            request.setAttribute("customer",customer);
            commandReturn.setPage(CUSTOMER_PAGE);
            commandReturn.setRequest(request);
            commandReturn.setResponse(response);
        } catch (ServiceException  e) {
            logger.debug("Exception from SaveCustomer",e);
            commandReturn.setPage(null);
            commandReturn.setRequest(request);
            commandReturn.setResponse(response);
        }
        return commandReturn;
    }
}
