package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;


public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final CustomerService customerService = factory.getCustomerService();

    private final static Logger logger = Logger.getLogger(SinginCommand.class);

    private final String SING_IN_ERROR_PARAMETER = "sing_in_error";

    private final String SING_IN_ERROR_VALUE = "error_value";

    private final Map<String,String> pages = new HashMap<>();


    public SinginCommand(){
        pages.put(WebProperty.ADMIN_ROLE,WebProperty.ADMIN_PAGE);
        pages.put(WebProperty.CUSTOMER_ROLE,WebProperty.CUSTOMER_PAGE);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {


        String email = null;
        String password = null;
        Customer customer = null;

        if(request.getSession().getAttribute(SING_IN_ERROR_PARAMETER) != null){
            request.getSession().removeAttribute(SING_IN_ERROR_PARAMETER);
        }

        try {
            email = request.getParameter(RegistrationProperty.EMAIL_PARAMETER);
            password = customerService.generateHashPassword(
                    request.getParameter(RegistrationProperty.PW_PARAMETER)
            );
            customer = customerService.findCustomerByEmailAndPassword(email,password);
            if(customer != null){
                request.getSession().setAttribute(WebProperty.USER_ATTRIBUTE_NAME,customer);
                String page = pages.get(customer.getRole());
                response.sendRedirect(page);

            }else {
                request.getSession().setAttribute(SING_IN_ERROR_PARAMETER,
                        SING_IN_ERROR_VALUE);

                response.sendRedirect(WebProperty.MAIN_PAGE);
            }


        } catch (ServiceException | IOException e ) {
            logger.error("Exception from singIn Command",e);
            throw new ControllerException(e);
        }catch (ValidatorException e){
            request.getSession().setAttribute(SING_IN_ERROR_PARAMETER,
                    SING_IN_ERROR_VALUE);
            try {
                response.sendRedirect(WebProperty.MAIN_PAGE);
            } catch (IOException ex) {
                throw new ControllerException("Exception in singinCommand while send redirect to main page",e);
            }
        }

    }




}