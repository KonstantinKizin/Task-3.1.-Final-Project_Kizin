package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProductSettingCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        int id = Integer.parseInt(request.getParameter("product_id"));

        Product product = null;

        try {

            if(request.getServletContext().getAttribute("current_product") != null){
                product = (Product)request.getSession().getAttribute("current_product");
            }else {
                product = productService.findProduct(id);
            }

            request.setAttribute("current_product",product);

            request.getRequestDispatcher("/admin/product_setting").forward(request,response);

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
