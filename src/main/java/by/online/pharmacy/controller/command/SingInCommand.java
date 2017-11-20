package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final String CUSTOMER_PAGE = "/WEB-INF/jsp/customer.jsp";
    private final String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";
    private final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private final String EMAIL_PARAMETER_NAME = "email";
    private final String PASSWORD_PARAMETER_NAME = "password";
    private final String ADMIN_ROLE = "admin";
    private final String CUSTOMER_ROLE = "customer";
    private final String USER_ATTRIBUTE_NAME = "user";

    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            if(customerService.LoginValidate(request)){
                String email = request.getParameter(EMAIL_PARAMETER_NAME);
                String password = request.getParameter(PASSWORD_PARAMETER_NAME);
                Customer customer = customerService.findCustomerByEmailAndPassword(email,password);
                if(customer != null){
                    request.setAttribute(USER_ATTRIBUTE_NAME,customer);
                    if(customer.getRole().equalsIgnoreCase(ADMIN_ROLE)){
                        buildCommandReturnObject(ADMIN_PAGE,request,response);;
                    }

                    if (customer.getRole().equalsIgnoreCase(CUSTOMER_ROLE)) {
                        buildCommandReturnObject(CUSTOMER_PAGE,request,response);;
                    }
                }

            }else {
                buildCommandReturnObject(null,request,response);
            }

        } catch (ServiceException e ) {
            logger.debug("Exception from singIn",e);
            buildCommandReturnObject(ERROR_PAGE,request,response);
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
