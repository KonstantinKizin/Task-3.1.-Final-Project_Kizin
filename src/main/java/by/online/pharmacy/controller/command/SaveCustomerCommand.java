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
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.RegistrationProperty;



public class SaveCustomerCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final CustomerService service = factory.getCustomerService();
    private final static Logger logger = Logger.getLogger(SaveCustomerCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        try {

            Enumeration pp = request.getParameterNames();
            while (pp.hasMoreElements()){
                System.out.println(pp.nextElement());
            }


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

            request.getSession().setAttribute(WebProperty.USER_ATTRIBUTE_NAME,customer);
            request.getSession().setAttribute(WebProperty.PAGE,WebProperty.CUSTOMER_PAGE);
            response.sendRedirect(WebProperty.REDIRECT_URL);

        } catch (ServiceException  | IOException e) {
            System.out.println(e);
            logger.error("Exception from SaveCustomer",e);
            request.getSession().setAttribute(WebProperty.PAGE,WebProperty.REGISTRATION_PAGE);
            response.sendRedirect(WebProperty.REDIRECT_URL);
        }catch (ValidatorException e){
            try {
                response.sendRedirect(WebProperty.REGISTRATION_PAGE);
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        }
    }
}
