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
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class ShowAllCustomersCommand implements Command {
    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(ShowAllCustomersCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            List<Customer> customers = service.getAllCustomers();
            request.setAttribute(WebProperty.CUSTOMER_LIST_ATTR_NAME, customers);
            request.getRequestDispatcher(WebProperty.CUSTOMERS_LIST_PAGE).forward(request,response);

        } catch (ServiceException  | IOException | ServletException e) {
            logger.error("Exception in showAllCustomer class ", e);
            throw new ControllerException(e);
        }

    }
}
