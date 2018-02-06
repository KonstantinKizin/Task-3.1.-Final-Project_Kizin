package by.online.pharmacy.controller.command;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductCommand implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private final static Logger logger = Logger.getLogger(SaveProductCommand.class);
    private ProductService productService = factory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {
        int id = Integer.parseInt(request.getParameter(ProductProperty.ID_PARAMETER));
        try {
            productService.deleteProduct(id);
            logger.info("Product with id="+id+" was delete");
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_MAIN);
        } catch (ServiceException e) {
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
        }


    }
}
