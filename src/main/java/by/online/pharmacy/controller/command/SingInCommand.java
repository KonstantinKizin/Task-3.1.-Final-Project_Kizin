package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.Validator;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;
import static by.online.pharmacy.entity.constant.PropertyEnum.RegistrationProperty;


public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final Validator customerValidator = factory.getValidator();
    private final static Logger lOGGER = Logger.getLogger(SinginCommand.class);
    private CommandReturnObject commandReturn = new CommandReturnObject();
    private String email;
    private String password;
    private Customer customer;

    @Override
    public CommandReturnObject execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            if(!customerValidator.loginValidate(request)){
                request.getSession().setAttribute(getConstant(WebProperty.SING_IN_ERROR_ATTR_NAME.name()),
                        getConstant(WebProperty.SING_IN_ERROR_MESSAGE.name()));

                buildCommandReturnObject(getConstant(WebProperty.MAIN_PAGE.name()),request,response);
            }else {
                email = request.getParameter(getConstant(RegistrationProperty.EMAIL_PARAMETER.name()));
                password = request.getParameter(getConstant(RegistrationProperty.PW_PARAMETER.name()));
                customer = customerService.findCustomerByEmailAndPassword(email,password);

                if(customer != null){
                    request.getSession().setAttribute(getConstant(WebProperty.USER_ATTRIBUTE_NAME.name()),customer);
                    if(customer.getRole().equalsIgnoreCase(getConstant(WebProperty.ADMIN_ROLE.name()))){
                        buildCommandReturnObject(getConstant(WebProperty.ADMIN_PAGE.name()),request,response);
                        lOGGER.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Admin");
                    }else {
                        buildCommandReturnObject(getConstant(WebProperty.CUSTOMER_PAGE.name()),request,response);
                        lOGGER.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Customer");
                    }
                }else {
                    buildCommandReturnObject(getConstant(WebProperty.MAIN_PAGE.name()),request,response);
                }
            }
        } catch (ServiceException e ) {
            lOGGER.error("Exception from singIn",e);
            throw new ControllerException(e);
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
