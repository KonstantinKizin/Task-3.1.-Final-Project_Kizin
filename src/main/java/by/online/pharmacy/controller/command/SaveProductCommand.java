package by.online.pharmacy.controller.command;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import static by.online.pharmacy.controller.constant.ControllerConstant.WebProperty;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import by.online.pharmacy.service.validator.ProductValidator;
import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SaveProductCommand implements Command {

    private  ServiceFactory factory = ServiceFactory.getInstance();
    private final static Logger logger = Logger.getLogger(SaveProductCommand.class);
    private  ProductService productService = factory.getProductService();
    private  ProductValidator productValidator = new ProductValidatorImpl();


    public SaveProductCommand(){}

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        try{
            if(!productValidator.parametersValidate(buildAttrMap(request))){
                try {
                    request.getRequestDispatcher(WebProperty.PAGE_ERROR).forward(request,response);
                } catch (ServletException e) {
                    logger.error("Servlet exception");
                }
        }else {
            Product product = new Product();
            product.setPrice(Float.parseFloat(request.getAttribute(ProductProperty.PRICE_PARAMETER).toString()));
            product.setCount(Integer.parseInt(request.getAttribute(ProductProperty.COUNT_PARAMETER).toString()));
            product.setPrescription(Boolean.parseBoolean(request.getAttribute(ProductProperty.PRESCRIPTION_PARAMETER).toString()));
            byte[] image = (byte[]) request.getAttribute(ProductProperty.IMAGE_PARAMETER);
            if (image == null || image.length == 0) {
                product.setImage(productService.getDefaultImage());
            } else {
                product.setImage(image);
            }
            ProductItem productItem = new ProductItem();
            productItem.setName(request.getAttribute(ProductProperty.NAME_PARAMETER).toString());
            productItem.setManufacture(request.getAttribute(ProductProperty.MANUFACTURE_PARAMETER).toString());
            productItem.setDescription(request.getAttribute(ProductProperty.DESCRIPTION_PARAMETER).toString());
            productItem.setCategoryName(request.getAttribute(ProductProperty.CATEGORY_PARAMETER).toString());

            product.getProductItemMap().put(
                    request.getAttribute(ProductProperty.LANGUAGE_PARAMETER).toString(), productItem
            );

            int id = productService.saveProduct(product);
            response.sendRedirect(WebProperty.PAGE_PRODUCT_SETTING + id);
        }
    } catch (ServiceException e) {
        logger.error("Exception in save product Servlet ",e);
        response.sendRedirect(WebProperty.PAGE_ERROR);
    }
}

    private Map<String,String> buildAttrMap(HttpServletRequest request){

        Map<String,String> attrMap = new HashMap<>();
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()){
            String attrName = attrNames.nextElement();
            if(attrName.equals(ProductProperty.IMAGE_PARAMETER)){
                continue;
            }else {
                Object attrValue = request.getAttribute(attrName);
                if(attrValue instanceof String) {
                    attrMap.put(attrName, (String) attrValue);
                }
            }
        }
        return attrMap;
    }

}
