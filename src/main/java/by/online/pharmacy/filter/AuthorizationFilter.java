package by.online.pharmacy.filter;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import by.online.pharmacy.entity.Customer;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class AuthorizationFilter implements javax.servlet.Filter {

    private Map<String,String> pages = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.pages.put(WebProperty.ADMIN_ROLE, WebProperty.PAGE_ADMIN);
        this.pages.put(WebProperty.CUSTOMER_ROLE, WebProperty.PAGE_CUSTOMER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Customer customer = (Customer) session.getAttribute(WebProperty.USER_ATTRIBUTE_NAME);
        if(customer == null){
            ((HttpServletResponse)servletResponse).sendRedirect(WebProperty.PAGE_LOGIN);
        }else {
            String account_page = pages.get(customer.getRole());
            ((HttpServletResponse)servletResponse).sendRedirect(account_page);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
