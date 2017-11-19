package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SingInCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final String CUSTOMER_PAGE = "/WEB-INF/jsp/customer.jsp";
    private final String MAIN_PAGE = "/index.jsp";
    private final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private final String EMAIL_PARAMETER_NAME = "email";
    private final String PASSWORD_PARAMETER_NAME = "password";
    private final String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";
    private final String ADMIN_ROLE = "admin";
    private final static Logger logger = Logger.getLogger(SingInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        try {
            Customer customer = customerService.findCustomerByEmailAndPassword(email,password);
            if(customer != null){
                request.setAttribute("customer",customer);
                RequestDispatcher rd = null;
                if(customer.getRole().equalsIgnoreCase(ADMIN_ROLE)){
                    request.getRequestDispatcher(ADMIN_PAGE).forward(request,response);
                }else {
                    request.getRequestDispatcher(CUSTOMER_PAGE).forward(request,response);
                }

            }else {
                response.sendRedirect(MAIN_PAGE);
            }
        } catch (ServiceException | ServletException | IOException e ) {
            logger.debug("Exception from singIn",e);
            response.sendRedirect(ERROR_PAGE);
        }


    }
}
