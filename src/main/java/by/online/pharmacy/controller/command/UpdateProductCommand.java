package by.online.pharmacy.controller.command;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

import by.online.pharmacy.controller.constant.ControllerConstant;
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
import java.util.Map;

public class UpdateProductCommand implements Command {


    private ServiceFactory factory = ServiceFactory.getInstance();
    private final static Logger logger = Logger.getLogger(SaveProductCommand.class);
    private ProductService productService = factory.getProductService();
    private ProductValidator productValidator = new ProductValidatorImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {

        Map<String,String> attrMap = buildAttrMap(request);

        int id = Integer.parseInt(attrMap.get(ProductProperty.ID_PARAMETER));

        try {

            Product product = productService.findProduct(id);

            int productIndex = productService.getProductStorage().indexOf(product);
            String price = attrMap.get(ProductProperty.PRICE_PARAMETER);
            String name = attrMap.get(ProductProperty.NAME_PARAMETER);
            String description = attrMap.get(ProductProperty.DESCRIPTION_PARAMETER);
            String currentLanguage = attrMap.get(ProductProperty.LANGUAGE_PARAMETER);
            String count = attrMap.get(ProductProperty.COUNT_PARAMETER);
            String manufacture = attrMap.get(ProductProperty.MANUFACTURE_PARAMETER);
            byte[] image = (byte[])request.getAttribute(ProductProperty.IMAGE_PARAMETER);

            if(product.getProductItemMap().get(currentLanguage) == null){
                ProductItem productItem = buildEmptyProductItems();
                product.getProductItemMap().put(currentLanguage,productItem);
            }

            if(checkForNullAndEmpty(count)){
                product.setCount(Integer.parseInt(count));
            }

            if(checkForNullAndEmpty(price)){
                product.setPrice(Float.parseFloat(price));
            }

            if(checkForNullAndEmpty(name)){
                product.getProductItemMap().get(currentLanguage).setName(name);
            }

            if(checkForNullAndEmpty(description)){
                product.getProductItemMap().get(currentLanguage).setDescription(description);
            }

            if(checkForNullAndEmpty(manufacture)){
                product.getProductItemMap().get(currentLanguage).setManufacture(manufacture);
            }

            if(image != null && image.length > 0){
                product.setImage(image);
            }

            productService.updateProduct(product,currentLanguage);

            response.sendRedirect(ControllerConstant.WebProperty.PAGE_PRODUCT_SETTING);


        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }




    private Map<String,String> buildAttrMap(HttpServletRequest request){

        Map<String,String> attrMap = new HashMap<>();
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()){
            String attrName = attrNames.nextElement();
            Object attrValue = request.getAttribute(attrName);
            if(attrValue instanceof String) {
                attrMap.put(attrName, (String) attrValue);
            }
        }
        return attrMap;
    }

    private boolean checkForNullAndEmpty(String parameter){
        if(parameter == null || parameter.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    private ProductItem buildEmptyProductItems(){
        ProductItem items = new ProductItem();
        items.setDescription("");
        items.setCategoryName("");
        items.setName("");
        items.setDescription("");
        return items;
    }





}
