package by.online.pharmacy.filter;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import by.online.pharmacy.entity.Customer;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminUrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();

        Customer customer = (Customer) session.getAttribute(WebProperty.USER_ATTRIBUTE_NAME);

        if(customer == null || !customer.getRole().equalsIgnoreCase(WebProperty.ADMIN_ROLE)){
            servletRequest.getRequestDispatcher(WebProperty.PAGE_NOT_FOUND).forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
