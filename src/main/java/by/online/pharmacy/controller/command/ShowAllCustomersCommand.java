package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class ShowAllCustomersCommand implements Command {
    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(ShowAllCustomersCommand.class);
    private final CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException {

        try {
            List<Customer> customers = service.getAllCustomers();
            request.setAttribute(WebProperty.CUSTOMER_LIST_ATTR_NAME, customers);
            commandReturn.setPage(WebProperty.CUSTOMERS_LIST_PAGE);
            commandReturn.setRequest(request);

        } catch (ServiceException e) {
            logger.debug("Exception in Controller ", e
            );
            commandReturn.setPage(WebProperty.ERROR_PAGE);
            commandReturn.setRequest(request);
        }

        return commandReturn;

    }
}
