package by.online.pharmacy.filter;

import by.online.pharmacy.controller.constant.ControllerConstant;

import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminProductGetFilter implements Filter {

    private static final  Pattern URL_PATTERN = Pattern.compile("admin/product/(\\w{1,})[?]id=(\\d{1,})|(admin/product/)(\\w{1,})[?]language=(\\w{1,})");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = ((HttpServletRequest)servletRequest);

        StringBuilder sb = new StringBuilder(request.getRequestURL());

        sb.append("?");
        sb.append(request.getQueryString());

        Matcher matcher = URL_PATTERN.matcher(sb);


        if(matcher.find()==false){

            System.out.println("Redirect from Admin pages");
            ((HttpServletRequest) servletRequest).getRequestDispatcher(ControllerConstant.WebProperty.PAGE_NOT_FOUND)
                    .forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
