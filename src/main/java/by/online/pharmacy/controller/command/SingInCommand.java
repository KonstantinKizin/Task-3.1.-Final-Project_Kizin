package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.Validator;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;
import static by.online.pharmacy.entity.constant.PropertyEnum.RegistrationProperty;


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
        System.out.println("Попали сюда 1");

        try {
            if(!customerValidator.loginValidate(request)){
                buildLoginErrorMsg(request);
                buildCommandReturnObject(getConstant(WebProperty.MAIN_PAGE.name()),request);
                System.out.println("Попали сюда 2");

            }else {
                email = request.getParameter(getConstant(RegistrationProperty.EMAIL_PARAMETER.name()));
                password = request.getParameter(getConstant(RegistrationProperty.PW_PARAMETER.name()));
                customer = customerService.findCustomerByEmailAndPassword(email,password);

                System.out.println("Попали сюда 3");
                if(customer != null){
                    System.out.println("Попали сюда 4");
                    request.getSession().setAttribute(getConstant(WebProperty.USER_ATTRIBUTE_NAME.name()),customer);
                    if(customer.getRole().equalsIgnoreCase(getConstant(WebProperty.ADMIN_ROLE.name()))){
                        System.out.println("Попали сюда 5");
                        buildCommandReturnObject(getConstant(WebProperty.ADMIN_PAGE.name()),request);
                        logger.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Admin");
                    }else if(customer.getRole().equalsIgnoreCase(getConstant(WebProperty.CUSTOMER_ROLE.name()))) {
                        System.out.println("Попали сюда 6");
                        buildCommandReturnObject(getConstant(WebProperty.CUSTOMER_PAGE.name()),request);
                        logger.info("User "+customer.getName()+" "+customer.getSureName()+" sing-in as Customer");
                    }
                }else {
                    System.out.println("Попали сюда 7");
                    buildLoginErrorMsg(request);
                    buildCommandReturnObject(getConstant(WebProperty.MAIN_PAGE.name()),request);
                }
            }

        } catch (ServiceException e ) {
            System.out.println("Попали сюда 8");
            System.out.println(e);
            logger.error("Exception from singIn",e);
            throw new ControllerException(e);
        }
        return commandReturn;
    }



    private void buildLoginErrorMsg(HttpServletRequest request){
        request.getSession().setAttribute(getConstant(WebProperty.SING_IN_ERROR_ATTR_NAME.name()),
                getConstant(WebProperty.SING_IN_ERROR_MESSAGE.name()));
    }




    private CommandReturnObject buildCommandReturnObject(String page,HttpServletRequest request){

        this.commandReturn.setPage(page);
        this.commandReturn.setRequest(request);
        return commandReturn;
    }


}
