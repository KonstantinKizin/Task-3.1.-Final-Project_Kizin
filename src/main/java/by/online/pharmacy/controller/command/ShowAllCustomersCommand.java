package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllCustomersCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private CustomerService service = factory.getCustomerService();
    private final String CUSTOMER_PAGE = "/WEB-INF/jsp/admin/customers.jsp";
    private final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private final static Logger logger = Logger.getLogger(ShowAllCustomersCommand.class);
    private final String CUSTOMER_LIST_ATTR_NAME = "customerList";
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            List<Customer> customers = service.getAllCustomers();
            request.setAttribute(CUSTOMER_LIST_ATTR_NAME, customers);
            commandReturn.setPage(CUSTOMER_PAGE);
            commandReturn.setRequest(request);
            commandReturn.setResponse(response);

        } catch (ServiceException e) {
            logger.debug("Exception in Controller ", e
            );
            commandReturn.setPage(ERROR_PAGE);
            commandReturn.setRequest(request);
            commandReturn.setResponse(response);

        }

        return commandReturn;

    }
}
