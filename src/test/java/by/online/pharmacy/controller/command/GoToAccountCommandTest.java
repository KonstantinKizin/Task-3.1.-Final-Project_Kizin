package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GoToAccountCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Customer customer;

    private GoToAccountCommand command;


    @Before
    public void setUp() throws IOException, ServletException {
        MockitoAnnotations.initMocks(this);
        command = new GoToAccountCommand();
        when(request.getSession()).thenReturn(session);

    }


    @Test
    public void when_user_is_admin_then_should_redirect_to_adminPage() throws IOException, ServletException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_ROLE))
                .thenReturn(ControllerConstant.WebProperty.ADMIN_ROLE);
        //when
        command.execute(request,response);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_ADMIN);
    }

    @Test
    public void when_user_is_customer_then_should_redirect_to_customerPage() throws ServletException, IOException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_ROLE))
                .thenReturn(ControllerConstant.WebProperty.CUSTOMER_ROLE);
        //when
        command.execute(request,response);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_CUSTOMER);
    }


    @Test
    public void when_user_is_not_registered_then_should_redirect_to_loginPage() throws IOException, ServletException {

        //given
        when(session.getAttribute(ControllerConstant.WebProperty.USER_ATTRIBUTE_ROLE))
                .thenReturn(ControllerConstant.WebProperty.GUEST_ROLE);

        //when
        command.execute(request,response);

        //then
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_LOGIN);


    }





}
