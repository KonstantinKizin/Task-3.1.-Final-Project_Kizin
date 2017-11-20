package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.online.pharmacy.dao.impl.PropertyManager.getProperty;

public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();

    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            if(customerService.LoginValidate(request)){
                String email = request.getParameter(getProperty("EMAIL_PARAMETER_NAME"));
                String password = request.getParameter(getProperty("PASSWORD_PARAMETER_NAME"));
                Customer customer = customerService.findCustomerByEmailAndPassword(email,password);
                if(customer != null){
                    request.setAttribute(getProperty("USER_ATTRIBUTE_NAME"),customer);
                    if(customer.getRole().equalsIgnoreCase(getProperty("ADMIN_ROLE"))){
                        buildCommandReturnObject(getProperty("ADMIN_PAGE"),request,response);;
                    }

                    if (customer.getRole().equalsIgnoreCase(getProperty("CUSTOMER_ROLE"))) {
                        buildCommandReturnObject(getProperty("CUSTOMER_PAGE"),request,response);;
                    }
                }

            }else {
                buildCommandReturnObject(null,request,response);
            }

        } catch (ServiceException e ) {
            logger.debug("Exception from singIn",e);
            buildCommandReturnObject(getProperty("ERROR_PAGE"),request,response);
        }
        return commandReturn;
    }





    private CommandReturnObject buildCommandReturnObject(String page,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response
                                             )
    {
        this.commandReturn.setPage(page);
        this.commandReturn.setRequest(request);
        this.commandReturn.setResponse(response);
        return commandReturn;

    }


}
