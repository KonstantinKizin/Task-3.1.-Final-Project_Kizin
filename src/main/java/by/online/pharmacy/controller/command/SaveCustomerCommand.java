package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.model.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;



public class SaveCustomerCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(SaveCustomerCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        try {
            String name = request.getParameter(RegistrationProperty.NAME_PARAMETER);
            String sureName = request.getParameter(RegistrationProperty.SURE_NAME_PARAMETER);
            String login = request.getParameter(RegistrationProperty.LOGIN_PARAMETER);
            String password = service.generateHashPassword(
                    request.getParameter(RegistrationProperty.PW_PARAMETER)
            );
            String date = new Date().toString();
            String role = WebProperty.CUSTOMER_ROLE;
            String email = request.getParameter(RegistrationProperty.EMAIL_PARAMETER);
            String phoneNumber = request.getParameter(RegistrationProperty.PHONE_PARAMETER);
            String birthDay = request.getParameter(RegistrationProperty.BIRTH_DATE_PARAMETER);
            String gender = request.getParameter(RegistrationProperty.GENDER_PARAMETER);

            Customer customer = new Customer(name,sureName,date,login,password,
                    email,phoneNumber, role,birthDay,gender);
            service.saveCustomer(customer);
            response.sendRedirect(WebProperty.MAIN_PAGE);

        } catch (ServiceException  | IOException e) {
            logger.error("Exception from SaveCustomer",e);
            request.setAttribute("errorMessage","Customer with that login or email already exist");
            throw new ControllerException(e);
        }
    }
}
