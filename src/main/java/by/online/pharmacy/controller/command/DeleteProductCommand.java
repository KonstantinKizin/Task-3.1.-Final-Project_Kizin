package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class DeleteProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        int productId = Integer.parseInt(request.getParameter("product_id"));

        try {
            productService.deleteProduct(productId);

            String language = (String) request.getSession().getAttribute("language");

            response.sendRedirect("/frontController?hidden=show_products&language="+language);

        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
