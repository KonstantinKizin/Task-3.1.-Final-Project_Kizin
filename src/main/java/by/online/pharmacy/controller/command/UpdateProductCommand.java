package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        int id = Integer.parseInt(request.getParameter("product_id"));

        try {

            Product product = productService.findProduct(id);



        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
