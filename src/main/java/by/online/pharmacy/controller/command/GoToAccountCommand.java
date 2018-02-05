package by.online.pharmacy.controller.command;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
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
        pages.put(WebProperty.ADMIN_ROLE, WebProperty.PAGE_ADMIN);
        pages.put(WebProperty.CUSTOMER_ROLE, WebProperty.PAGE_CUSTOMER);
        pages.put(WebProperty.GUEST_ROLE , WebProperty.PAGE_LOGIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String role = (String)session.getAttribute(WebProperty.USER_ATTRIBUTE_ROLE);
        String page = pages.get(role);
        response.sendRedirect(page);

    }
}
