package by.online.pharmacy.controller.command;
import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

public class GetProductCommand implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    private ProductService service = factory.getProductService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        int id = Integer.parseInt(request.getParameter(ProductProperty.ID_PARAMETER));
        Product product = null;
        try{
            product = service.findProduct(id);
            if(product != null){
                request.getSession().setAttribute(ProductProperty.CURRENT_PRODUCT_PARAMETER,product);
                response.sendRedirect(ControllerConstant.WebProperty.PAGE_PRODUCT);

            }else {
                try {
                    request.getRequestDispatcher(ControllerConstant.WebProperty.PAGE_NOT_FOUND).forward(request,response);
                } catch (ServletException e) {
                    throw new ControllerException(e);
                }
            }
        }catch (ServiceException  | NumberFormatException e ){
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
        }
    }



}
