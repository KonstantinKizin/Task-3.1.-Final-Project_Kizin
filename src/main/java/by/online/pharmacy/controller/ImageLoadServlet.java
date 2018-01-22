package by.online.pharmacy.controller;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.storage.ProductStorage;
import org.apache.log4j.Logger;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ImageLoadServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(ImageLoadServlet.class);

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();

    private final ProductStorage productStorage = ProductStorage.getInstance();

    private final static String  PRODUCT_ID_PARAMETER = "product_id";

    private final static String PRODUCT_LIST_ATTRIBUTE = "productList";

    private final static String RESPONSE_CONTENT_TYPE = "image/jpg";





    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(PRODUCT_ID_PARAMETER));
        byte[] image = null;

        List<Product> productList = (List<Product>)request.getServletContext().getAttribute(PRODUCT_LIST_ATTRIBUTE);


        if(productList.isEmpty()){
            try {
                Product product =  productService.findProduct(id);
                image = product.getImage();
            } catch (ServiceException e) {
                logger.error("Image Servlet exception",e);
                response.sendRedirect(WebProperty.PAGE_ERROR);
            }

        }else {

            for(Product product : productList){
                if(product.getId() == id){
                    image = product.getImage();
                    break;
                }
            }
        }

        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getOutputStream().write(image,0,image.length);
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }
}
