package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.Validator;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;


public class SinginCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService customerService = factory.getCustomerService();
    private final Validator customerValidator = factory.getValidator();
    private final static Logger logger = Logger.getLogger(SinginCommand.class);
    private final CommandReturnObject commandReturn = new CommandReturnObject();

    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException {
        String email = null;
        String password = null;
        Customer customer = null;
        try {
            if(!customerValidator.loginValidate(request)){
                buildLoginErrorMsg(request);
                buildCommandReturnObject(WebProperty.MAIN_PAGE,request);
            }else {
                email = request.getParameter(RegistrationProperty.EMAIL_PARAMETER);
                password = customerService.generateHashPassword(
                        request.getParameter(RegistrationProperty.PW_PARAMETER)
                );

                customer = customerService.findCustomerByEmailAndPassword(email,password);
                if(customer != null){
                    request.getSession().setAttribute(WebProperty.USER_ATTRIBUTE_NAME,customer);
                    if(customer.getRole().equalsIgnoreCase(WebProperty.ADMIN_ROLE)){
                        buildCommandReturnObject(WebProperty.ADMIN_PAGE,request);
                        logger.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Admin");
                    }else if(customer.getRole().equalsIgnoreCase(WebProperty.CUSTOMER_ROLE)){
                        buildCommandReturnObject(WebProperty.CUSTOMER_PAGE,request);
                        logger.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Customer");
                    }
                }else {
                    buildLoginErrorMsg(request);
                    buildCommandReturnObject(WebProperty.MAIN_PAGE,request);
                }
            }

        } catch (ServiceException e ) {
            logger.error("Exception from singIn",e);
            throw new ControllerException(e);
        }

        return commandReturn;
    }



    private void buildLoginErrorMsg(HttpServletRequest request){
        request.getSession().setAttribute(WebProperty.SING_IN_ERROR_ATTR_NAME,
                WebProperty.SING_IN_ERROR_MESSAGE);
    }




    private CommandReturnObject buildCommandReturnObject(String page,HttpServletRequest request){
        this.commandReturn.setPage(page);
        this.commandReturn.setRequest(request);
        return commandReturn;
    }


}