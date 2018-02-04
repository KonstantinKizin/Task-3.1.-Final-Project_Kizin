package by.online.pharmacy.filter;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class ProductPageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession(true);
        Object ob = session.getAttribute(ProductProperty.CURRENT_PRODUCT_PARAMETER);
        if(ob == null){
            servletRequest.getRequestDispatcher(WebProperty.PAGE_NOT_FOUND)
                 .forward(servletRequest,servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
