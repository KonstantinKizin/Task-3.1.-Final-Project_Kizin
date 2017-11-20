package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final String CUSTOMER_PAGE = "/WEB-INF/jsp/customer.jsp";
    private final String EMAIL_PARAMETER_NAME = "email";
    private final String PASSWORD_PARAMETER_NAME = "password";
    private final String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";
    private final String ADMIN_ROLE = "admin";
    private final String CUSTOMER_ROLE = "customer";
    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private CommandReturn commandReturn = new CommandReturn();

    @Override
    public CommandReturn execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {









        try {
            if(customerService.Loginvalidate(request)){
                String email = request.getParameter(EMAIL_PARAMETER_NAME);
                String password = request.getParameter(PASSWORD_PARAMETER_NAME);
                Customer customer = customerService.findCustomerByEmailAndPassword(email,password);
                if(customer != null){
                    request.setAttribute("customer",customer);
                    if(customer.getRole().equalsIgnoreCase(ADMIN_ROLE)){
                        buildCommandReturn(ADMIN_PAGE,request,response);;

                    }else if (customer.getRole().equalsIgnoreCase(CUSTOMER_ROLE)) {
                        buildCommandReturn(CUSTOMER_PAGE,request,response);;
                    }else {
                        buildCommandReturn(null,request,response);
                    }
                }

            }else {
                buildCommandReturn(null,request,response);
            }

        } catch (ServiceException e ) {
            logger.debug("Exception from singIn",e);
        }

        return commandReturn;

    }



    private CommandReturn buildCommandReturn(String page,
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
