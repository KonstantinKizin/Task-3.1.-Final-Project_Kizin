package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCustomersCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(GoToCustomersCommand.class);

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

            try {
                List<Customer> customers = service.getAllCustomers();
                request.getSession().setAttribute(ControllerConstant.WebProperty.CUSTOMER_LIST_ATTR_NAME, customers);
                response.sendRedirect(ControllerConstant.WebProperty.PAGE_CUSTOMER_LIST);
            } catch (ServiceException | IOException e) {
                logger.error("Exception in GotToCustomers command ", e);
                throw new ControllerException(e);
            }
        }
    }

