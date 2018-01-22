package by.online.pharmacy.filter;

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

    private final static String ERROR_MESSAGE = "You don't have access for the page";

    private Customer customer = null;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean flag = true;

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();

        if(((Customer)session.getAttribute("user")) == null){
            flag = false;
        }else {
            customer = (Customer)session.getAttribute("user");
            if(!customer.getRole().equalsIgnoreCase("admin")){
                flag = false;
            }
        }

        if(!flag){
            ((HttpServletRequest) servletRequest).getRequestDispatcher("/WEB-INF/jsp/error.jsp?reason="+ERROR_MESSAGE).forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        customer = null;

    }
}
