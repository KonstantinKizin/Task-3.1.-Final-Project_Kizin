package by.online.pharmacy.controller;

import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageLoaderServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(ImageLoaderServlet.class);

    private ServiceFactory factory = ServiceFactory.getInstance();

    private ProductService productService = factory.getProductService();

    private final static String  PRODUCT_ID_PARAMETER = "product_id";

    private final static String RESPONSE_CONTENT_TYPE = "image/jpg";





    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(PRODUCT_ID_PARAMETER));
        byte[] image = null;

        try {
            Product product = productService.findProduct(id);
            if (product != null) {
                image = product.getImage();
            }
        } catch (ServiceException e) {
            logger.error("Image Servlet exception", e);
            response.sendRedirect(WebProperty.PAGE_ERROR);
        }

        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getOutputStream().write(image,0,image.length);
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }
}
