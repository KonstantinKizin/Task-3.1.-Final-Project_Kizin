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
import java.util.List;

public class ShowAllCustomersCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private CustomerService service = factory.getCustomerService();
    private final String CUSTOMERS_PAGE = "/WEB-INF/jsp/admin/customers.jsp";
    private final static Logger logger = Logger.getLogger(ShowAllCustomersCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        try {
            List<Customer> customers = service.getAllCustomers();
            System.out.println(customers);
            request.setAttribute("customerList",customers);
            request.getRequestDispatcher(CUSTOMERS_PAGE).forward(request,response);
        } catch (ServiceException | ServletException e) {
            logger.debug("Exception in Controller ",e);
            e.printStackTrace();
        }


    }
}
