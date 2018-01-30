package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService service = factory.getProductService();

    private final static String PRODUCT_ID_PARAMETER = "product_id";

    private final static String CURRENT_PRODUCT_PARAMETER = "found_product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {
        int id = Integer.parseInt(request.getParameter(PRODUCT_ID_PARAMETER));
        Product product = null;
        try{
            product = service.findProduct(id);
            if(product != null){
                request.getSession().setAttribute(CURRENT_PRODUCT_PARAMETER,product);
            }
        }catch (ServiceException |NumberFormatException e ){
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
        }
    }
}
