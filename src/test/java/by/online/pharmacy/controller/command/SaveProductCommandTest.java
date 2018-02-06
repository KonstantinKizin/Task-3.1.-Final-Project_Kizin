package by.online.pharmacy.controller.command;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.ProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberModifier;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SaveProductCommandTest {

    private final static int PRODUCT_ID = 10;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ProductService productService;

    @Mock
    private ServiceFactory factory;

    @Mock
    private ProductValidator productValidator;

    @Mock
    private Enumeration enumeration;

    @Mock
    private ServletContext context;

    @Mock
    private List list;

    private SaveProductCommand command;


    @Before
    public  void setUpClass() throws IllegalAccessException, ServiceException {
        MockitoAnnotations.initMocks(this);
        command = new SaveProductCommand();
        when(request.getSession()).thenReturn(session);
        MemberModifier.field(SaveProductCommand.class,"factory").set(command,factory);
        MemberModifier.field(SaveProductCommand.class,"productService").set(command,productService);
        MemberModifier.field(SaveProductCommand.class,"productValidator").set(command,productValidator);
        when(request.getAttributeNames()).thenReturn(enumeration);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(productService.saveProduct(anyObject())).thenReturn(PRODUCT_ID);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(WebProperty.PRODUCT_LIST_ATTR_NAME)).thenReturn(list);
        when(request.getSession()).thenReturn(session);

    }


    @Test
    public void when_put_invalid_date_test() throws IOException, ControllerException, ServletException {

        //given
        when(productValidator.parametersValidate(anyMap())).thenReturn(false);

        //when
        command.execute(request,response);

        //then
        verify(dispatcher).forward(request,response);
        verify(request).getRequestDispatcher(WebProperty.PAGE_ERROR);
        verify(request).getAttributeNames();
        verifyNoMoreInteractions(request);

    }

    @Test
    public void when_come_empty_byte_array() throws IOException, ControllerException, ServiceException {

        //given
        when(productValidator.parametersValidate(anyMap())).thenReturn(true);
        prepareProduct();
        when(request.getAttribute(ProductProperty.IMAGE_PARAMETER)).thenReturn(new byte[0]);

        //when
        command.execute(request,response);

        //then
        verify(productService).getDefaultImage();
        verify(response).sendRedirect(WebProperty.PAGE_PRODUCT_SETTING + PRODUCT_ID);
    }



    private void prepareProduct(){

        when(request.getAttribute(ProductProperty.PRICE_PARAMETER)).thenReturn(10);
        when(request.getAttribute(ProductProperty.COUNT_PARAMETER)).thenReturn(10);
        when(request.getAttribute(ProductProperty.PRESCRIPTION_PARAMETER)).thenReturn(false);
        when(request.getAttribute(ProductProperty.NAME_PARAMETER)).thenReturn("Name");
        when(request.getAttribute(ProductProperty.MANUFACTURE_PARAMETER)).thenReturn("Manufacture");
        when(request.getAttribute(ProductProperty.DESCRIPTION_PARAMETER)).thenReturn("Description");
        when(request.getAttribute(ProductProperty.CATEGORY_PARAMETER)).thenReturn("Category");
        when(request.getAttribute(ProductProperty.LANGUAGE_PARAMETER)).thenReturn("en");

    }









}
