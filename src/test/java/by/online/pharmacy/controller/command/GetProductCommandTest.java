package by.online.pharmacy.controller.command;
import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberModifier;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;


public class GetProductCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Product product;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ProductService service;

    private final String PRODUCT_SERVICE_FIELD_NAME = "service";

    private GetProductCommand command;

    @Before
    public  void setUpClass() throws IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        command = new GetProductCommand();
        MemberModifier.field(GetProductCommand.class,PRODUCT_SERVICE_FIELD_NAME).set(command,service);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter(ControllerConstant.ProductProperty.ID_PARAMETER)).thenReturn(String.valueOf(anyInt()));


    }


    @Test
    public void when_product_exist_then_should_redirect_to_productPage_with_found_product() throws ServiceException, IOException, ControllerException, ServletException {

        //given
        when(request.getParameter(ControllerConstant.ProductProperty.ID_PARAMETER)).thenReturn("1000");
        when(service.findProduct(anyInt())).thenReturn(product);

        //when
        command.execute(request,response);

        //then
        verify(session).setAttribute(ProductProperty.CURRENT_PRODUCT_PARAMETER,product);
        verify(response).sendRedirect(ControllerConstant.WebProperty.PAGE_PRODUCT);

    }

    @Test
    public void when_product_does_not_exist_then_should_redirect_to_notFountPage() throws ServiceException, IOException, ControllerException, ServletException {
        //given
        when(request.getParameter(ControllerConstant.ProductProperty.ID_PARAMETER)).thenReturn("1000");
        when(service.findProduct(anyInt())).thenReturn(null);

        //when
        command.execute(request,response);

        //then
        verify(request).getRequestDispatcher(WebProperty.PAGE_NOT_FOUND);
        verify(dispatcher).forward(request,response);
    }


    @Test(expected = NumberFormatException.class)
    public void when_product_id_parameter_is_not_number_then_should_redirect_to_ErrorPage() throws IOException, ControllerException, ServletException {

        //given
        when(request.getParameter(ControllerConstant.ProductProperty.ID_PARAMETER)).thenReturn(anyString());

        //when
        command.execute(request,response);

        //then
        verify(response).sendRedirect(WebProperty.PAGE_ERROR);


    }



}
