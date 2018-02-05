package by.online.pharmacy.controller.command;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.validator.ProductValidator;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberModifier;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class SaveProductCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Product product;

    @Mock
    private ProductItem productItem;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ProductService productService;

    @Mock
    private ProductValidator productValidator;

    private SaveProductCommand command;


    @Before
    public  void setUpClass() throws IllegalAccessException, ServiceException {
        MockitoAnnotations.initMocks(this);
        command = new SaveProductCommand();
        when(request.getSession()).thenReturn(session);
        MemberModifier.field(SaveProductCommand.class,"productService").set(command,productService);
        MemberModifier.field(SaveProductCommand.class,"productValidator").set(command,productValidator);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }










}
