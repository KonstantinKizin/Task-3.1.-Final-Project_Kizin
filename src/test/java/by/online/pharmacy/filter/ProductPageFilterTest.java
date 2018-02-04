package by.online.pharmacy.filter;
import by.online.pharmacy.controller.constant.ControllerConstant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;

public class ProductPageFilterTest {


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    private ProductPageFilter filter;

    @Before
    public void setUp() throws IOException, ServletException {
        MockitoAnnotations.initMocks(this);
        filter = new ProductPageFilter();
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestDispatcher(ControllerConstant.WebProperty.PAGE_NOT_FOUND)).thenReturn(dispatcher);
    }

    @Test
    public void when_session_does_not_has_customer_object_then_should_forwarding_to_pageNotFound() throws IOException, ServletException {

        //given
        when(session.getAttribute(anyString())).thenReturn(null);

        //when
        filter.doFilter(request,response,filterChain);

        //then
       verify(dispatcher).forward(request,response);
       verify(filterChain).doFilter(request,response);
    }


    @Test
    public void when_session_has_customer_object_then_should_forwarding_to_pageNotFound() throws IOException, ServletException {

        //given
        when(session.getAttribute(anyString())).thenReturn(new Object());

        //when
        filter.doFilter(request,response,filterChain);

        //then
       verify(request).getSession(true);
       verify(filterChain).doFilter(request,response);
       verifyNoMoreInteractions(request);


    }







}
