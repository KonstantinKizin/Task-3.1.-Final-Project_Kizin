package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;
import static by.online.pharmacy.entity.constant.PropertyEnum.RegistrationProperty;


public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            if(customerService.LoginValidate(request)){
                String email = request.getParameter(getConstant(RegistrationProperty.EMAIL_PARAMETER.name()));
                String password = request.getParameter(getConstant(RegistrationProperty.PW_PARAMETER.name()));
                Customer customer = customerService.findCustomerByEmailAndPassword(email,password);
                if(customer != null){
                    request.setAttribute(getConstant(WebProperty.USER_ATTRIBUTE_NAME.name()),customer);
                    if(customer.getRole().equalsIgnoreCase(getConstant(WebProperty.ADMIN_ROLE.name()))){
                        buildCommandReturnObject(getConstant(WebProperty.ADMIN_PAGE.name()),request,response);;
                    }

                    if (customer.getRole().equalsIgnoreCase(getConstant(WebProperty.CUSTOMER_ROLE.name()))) {
                        buildCommandReturnObject(getConstant(WebProperty.CUSTOMER_PAGE.name()),request,response);;
                    }
                }
            }else {
                buildCommandReturnObject(getConstant(WebProperty.MAIN_PAGE.name()),request,response);
            }

        } catch (ServiceException e ) {
            logger.debug("Exception from singIn",e);
            buildCommandReturnObject(getConstant(WebProperty.ERROR_PAGE.name()),request,response);
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
