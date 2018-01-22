package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GoToAccountCommand implements Command {

    private final Map<String,String> pages = new HashMap<>();

    public GoToAccountCommand(){

        pages.put(ControllerConstant.WebProperty.ADMIN_ROLE, ControllerConstant.WebProperty.PAGE_ADMIN);
        pages.put(ControllerConstant.WebProperty.CUSTOMER_ROLE, ControllerConstant.WebProperty.PAGE_CUSTOMER);

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_NAME);
        if(customer != null){
            String account_page = pages.get(customer.getRole());
            response.sendRedirect(account_page);
        }else {
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_LOGIN);
        }


    }
}
