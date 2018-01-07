package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

public class GetAllProductsCommand implements Command{

    private final static Logger logger = Logger.getLogger(SaveProductCommand.class);

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        String language = request.getParameter(WebProperty.LANGUAGE_PARAMETER);

        if(language.startsWith("ru")){
            language ="ru";
        }else if(language.startsWith("en")){
            language = "en";
        }

        try {
            List<Product> productList = productService.getAllProducts(language);
            request.getSession().setAttribute("productList",productList);
            request.getRequestDispatcher("/catalog.jsp").forward(request,response);

        } catch (ServiceException | ServletException e) {
            e.printStackTrace();
        }

    }
}
