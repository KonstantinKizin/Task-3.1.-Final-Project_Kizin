package by.online.pharmacy.filter;

import by.online.pharmacy.controller.constant.ControllerConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProductPageFilter implements Filter {

    private static final String PRODUCT_URL_REG_EXP = "/product[?]{0,1}(\\w){0,}";

    private static final Pattern PRODUCT_PATTERN = Pattern.compile(PRODUCT_URL_REG_EXP);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpSession session = ((HttpServletRequest)servletRequest).getSession(true);

        String uri = ((HttpServletRequest)servletRequest).getRequestURI();

        if(session.getAttribute("current_product") == null){
            System.out.println("Redirect from product filter");
            ((HttpServletRequest)servletRequest).getRequestDispatcher(ControllerConstant.WebProperty.PAGE_NOT_FOUND)
                 .forward(servletRequest,servletResponse);
        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
