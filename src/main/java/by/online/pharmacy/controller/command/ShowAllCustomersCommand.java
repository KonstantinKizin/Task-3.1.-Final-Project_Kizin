package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;

public class ShowAllCustomersCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    private CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(ShowAllCustomersCommand.class);
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException {

        try {
            List<Customer> customers = service.getAllCustomers();
            request.setAttribute(getConstant(WebProperty.CUSTOMER_LIST_ATTR_NAME.name()), customers);
            commandReturn.setPage(getConstant(WebProperty.CUSTOMERS_LIST_PAGE.name()));
            commandReturn.setRequest(request);

        } catch (ServiceException e) {
            logger.debug("Exception in Controller ", e
            );
            commandReturn.setPage(getConstant(WebProperty.ERROR_PAGE.name()));
            commandReturn.setRequest(request);


        }

        return commandReturn;

    }
}
