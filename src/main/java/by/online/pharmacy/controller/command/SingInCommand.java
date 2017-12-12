package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;


public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final Validator customerValidator = factory.getValidator();
    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private final CommandReturnObject commandReturn = new CommandReturnObject();
    private final String SING_IN_ERROR_PARAMETER = "sing_in_error";
    private final String SING_IN_ERROR_VALUE = "error_value";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        String email = null;
        String password = null;
        Customer customer = null;
        Locale locale = request.getLocale();
        try {

            email = request.getParameter(RegistrationProperty.EMAIL_PARAMETER);
            password = customerService.generateHashPassword(
                    request.getParameter(RegistrationProperty.PW_PARAMETER)
            );
            if(customerService.findCustomerByEmailAndPassword(email,password) != null){

                customer = customerService.findCustomerByEmailAndPassword(email,password);
                request.getSession().setAttribute(WebProperty.USER_ATTRIBUTE_NAME,customer);

                if(customer.getRole().equalsIgnoreCase(WebProperty.ADMIN_ROLE)){
                    request.getRequestDispatcher(WebProperty.ADMIN_PAGE).forward(request,response);
                }else if(customer.getRole().equalsIgnoreCase(WebProperty.CUSTOMER_ROLE)){
                    request.getRequestDispatcher(WebProperty.CUSTOMER_PAGE).forward(request,response);
                }
            }else {
                request.getSession().setAttribute(SING_IN_ERROR_PARAMETER,
                        SING_IN_ERROR_VALUE);
                response.sendRedirect(WebProperty.MAIN_PAGE);
            }

        } catch (ServiceException | ServletException | IOException e ) {
            logger.error("Exception from singIn Command",e);
            throw new ControllerException(e);
        }

    }


}