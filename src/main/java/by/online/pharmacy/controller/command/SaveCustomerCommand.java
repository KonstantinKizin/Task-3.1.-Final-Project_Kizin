package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import static by.online.pharmacy.service.impl.PropertyLoader.getConstant;
import static by.online.pharmacy.entity.constant.PropertyEnum.WebProperty;
import static by.online.pharmacy.entity.constant.PropertyEnum.RegistrationProperty;


public class SaveCustomerCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger lOGGER = Logger.getLogger(SaveCustomerCommand.class);
    private final CommandReturnObject commandReturn = new CommandReturnObject();



    @Override
    public CommandReturnObject execute(HttpServletRequest request) throws ControllerException{


        try {

            String name = request.getParameter(getConstant(RegistrationProperty.NAME_PARAMETER.name()));
            String sureName = request.getParameter(getConstant(RegistrationProperty.SURE_NAME_PARAMETER.name()));
            String login = request.getParameter(getConstant(RegistrationProperty.LOGIN_PARAMETER.name()));
            String password = request.getParameter(getConstant(RegistrationProperty.PW_PARAMETER.name()));
            String date = new Date().toString();
            String role = getConstant(WebProperty.CUSTOMER_ROLE.name());
            String email = request.getParameter(getConstant(RegistrationProperty.EMAIL_PARAMETER.name()));
            String phoneNumber = request.getParameter(getConstant(RegistrationProperty.PHONE_PARAMETER.name()));
            String birthDay = request.getParameter(getConstant(RegistrationProperty.BIRTH_DATE_PARAMETER.name()));
            String gender = request.getParameter(getConstant(RegistrationProperty.GENDER_PARAMETER.name()));

            Customer customer = new Customer(name,sureName,date,login,password,
            email,phoneNumber, role,birthDay,gender);
            service.saveCustomer(customer);
            request.setAttribute(getConstant(WebProperty.USER_ATTRIBUTE_NAME.name()),customer);
            commandReturn.setPage(getConstant(WebProperty.CUSTOMER_PAGE.name()));
            commandReturn.setRequest(request);

        } catch (ServiceException  e) {
            lOGGER.debug("Exception from SaveCustomer",e);
            commandReturn.setPage(getConstant(WebProperty.ERROR_PAGE.name()));
            commandReturn.setRequest(request);

        }
        return commandReturn;
    }
}
