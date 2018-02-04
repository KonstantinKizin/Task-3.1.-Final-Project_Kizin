package by.online.pharmacy.filter;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorizationFilterTest  {

    @Mock
    private  HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Mock
    private FilterConfig config;

    @Mock
    private Customer customer;

    private AuthorizationFilter filter;

    @Before
    public void setUp() throws ServletException {
        MockitoAnnotations.initMocks(this);
        filter = new AuthorizationFilter();
        filter.init(config);
        when(request.getSession()).thenReturn(session);

    }

    @Test
    public void when_user_is_admin_then_should_redirect_to_adminPage() throws IOException, ServletException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_NAME)).thenReturn(customer);
        when(customer.getRole()).thenReturn(ControllerConstant.WebProperty.ADMIN_ROLE);

        //when
        filter.doFilter(request,response,filterChain);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_ADMIN);
        verify(filterChain).doFilter(request,response);
    }

    @Test
    public void when_user_is_customer_then_should_redirect_to_customerPage() throws ServletException, IOException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_NAME)).thenReturn(customer);
        when(customer.getRole()).thenReturn(ControllerConstant.WebProperty.CUSTOMER_ROLE);

        //when
        filter.doFilter(request,response,filterChain);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_CUSTOMER);
        verify(filterChain).doFilter(request,response);
    }

    @Test
    public void when_user_is_not_registered_then_should_redirect_to_loginPage() throws IOException, ServletException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_NAME)).thenReturn(null);

        //when
        filter.doFilter(request,response,filterChain);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_LOGIN);
        verify(filterChain).doFilter(request,response);

    }


}
