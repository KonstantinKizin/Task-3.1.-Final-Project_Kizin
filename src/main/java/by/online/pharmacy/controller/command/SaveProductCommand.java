package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.SaveProductServlet;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveProductCommand implements Command {

    private  ServiceFactory factory = ServiceFactory.getInstance();
    private final static Logger logger = Logger.getLogger(SaveProductServlet.class);
    private  ProductService productService = factory.getProductService();
    private  ProductValidator productValidator = new ProductValidatorImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        try{

        if(!productValidator.parametersValidate(buildAttrMap(request))){
            request.getRequestDispatcher(WebProperty.PAGE_ERROR);
        }else {
            Product product = new Product();

            product.setPrice(((Float)request.getAttribute(ProductProperty.PRICE_PARAMETER)).floatValue());
            product.setCount(((Integer)request.getAttribute(ProductProperty.COUNT_PARAMETER)).intValue());
            product.setPrescription(((Boolean)request.getAttribute(ProductProperty.PRESCRIPTION_PARAMETER)).booleanValue());
            product.setImage((byte[])request.getAttribute(ProductProperty.IMAGE_PARAMETER));

            ProductItem productItem = new ProductItem();
            productItem.setName(request.getAttribute(ProductProperty.NAME_PARAMETER).toString());
            productItem.setManufacture(request.getAttribute(ProductProperty.MANUFACTURE_PARAMETER).toString());
            productItem.setDescription(request.getAttribute(ProductProperty.DESCRIPTION_PARAMETER).toString());
            productItem.setCategoryName(request.getAttribute(ProductProperty.CATEGORY_PARAMETER).toString());

            product.getProductItemMap().put(
                    request.getAttribute(ProductProperty.LANGUAGE_PARAMETER).toString(),productItem
            );
            int id = productService.saveProduct(product);
            ((List<Product>)request.getServletContext()
                    .getAttribute(WebProperty.PRODUCT_LIST_ATTR_NAME))
                    .add(product);
            request.getSession().setAttribute(ProductProperty.CURRENT_PRODUCT_PARAMETER,product);
            response.sendRedirect(WebProperty.PAGE_PRODUCT_SETTING+id);
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
            String attName = attrNames.nextElement();
            if(request.getAttribute(attName) instanceof byte[]){
                continue;
            }else {
                String attrValue = (String)request.getAttribute(attName);
                attrMap.put(attName,attrValue);
            }
        }
        return attrMap;
    }

}
