package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;



public class SaveCustomerCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger lOGGER = Logger.getLogger(SaveCustomerCommand.class);
    private final CommandReturnObject commandReturn = new CommandReturnObject();



    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException{


        try {
            String name = request.getParameter(RegistrationProperty.NAME_PARAMETER);
            String sureName = request.getParameter(RegistrationProperty.SURE_NAME_PARAMETER);
            String login = request.getParameter(RegistrationProperty.LOGIN_PARAMETER);
            String password = request.getParameter(RegistrationProperty.PW_PARAMETER);
            String hashedPassword = service.generateHashPassword(password);
            String date = new Date().toString();
            String role = WebProperty.CUSTOMER_ROLE;
            String email = request.getParameter(RegistrationProperty.EMAIL_PARAMETER);
            String phoneNumber = request.getParameter(RegistrationProperty.PHONE_PARAMETER);
            String birthDay = request.getParameter(RegistrationProperty.BIRTH_DATE_PARAMETER);
            String gender = request.getParameter(RegistrationProperty.GENDER_PARAMETER);

            Customer customer = new Customer(name,sureName,date,login,hashedPassword,
            email,phoneNumber, role,birthDay,gender);
            service.saveCustomer(customer);
            request.setAttribute(WebProperty.USER_ATTRIBUTE_NAME,customer);
            commandReturn.setPage(WebProperty.CUSTOMER_PAGE);
            commandReturn.setRequest(request);

        } catch (ServiceException  e) {
            lOGGER.debug("Exception from SaveCustomer",e);
            commandReturn.setPage(WebProperty.ERROR_PAGE);
            commandReturn.setRequest(request);

        }
        return commandReturn;
    }
}
