package by.online.pharmacy.controller.command;
import static  by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Customer;
import by.online.pharmacy.service.CustomerService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.ValidatorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberModifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SingInCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private CustomerService customerService;

    @Mock
    private Customer customer;

    private SinginCommand command;



    @Before
    public  void setUpClass() throws IllegalAccessException, ServiceException {
        MockitoAnnotations.initMocks(this);
        command = new SinginCommand();
        when(request.getSession()).thenReturn(session);
        MemberModifier.field(SinginCommand.class,"customerService").set(command,customerService);
        when(customerService.findCustomerByEmailAndPassword(anyString(),anyString())).thenReturn(customer);
    }

    @Test
    public void when_user_sing_in_as_customer_then_should_redirect_to_customerPage() throws ControllerException, IOException {
        when(customer.getRole()).thenReturn(WebProperty.CUSTOMER_ROLE);
        command.execute(request,response);
        verify(response).sendRedirect(WebProperty.PAGE_CUSTOMER);
    }

    @Test
    public void when_user_sing_in_as_admin_then_should_redirect_to_adminPage() throws ControllerException, IOException {
        when(customer.getRole()).thenReturn(WebProperty.ADMIN_ROLE);
        command.execute(request,response);
        verify(response).sendRedirect(WebProperty.PAGE_ADMIN);
    }

    @Test
    public void when_user_try_to_sing_in_as_not_existing_user_then_should_redirect_to_loginPage() throws ServiceException, ControllerException, IOException {
        when(customerService.findCustomerByEmailAndPassword(anyString(),anyString())).thenReturn(null);
        command.execute(request,response);
        verify(response).sendRedirect(WebProperty.PAGE_LOGIN);
    }

    @Test
    public void when_user_put_invalid_values_then_should_redirect_to_loginPage() throws ServiceException, ControllerException, IOException {
        when(customerService.findCustomerByEmailAndPassword(anyString(),anyString()))
                .thenThrow(ValidatorException.class);
        command.execute(request,response);
        verify(response).sendRedirect(WebProperty.PAGE_LOGIN);

    }



















}
