package by.online.pharmacy.controller;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;

public class UpdateProductServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();

    private Map<String,String> parametersMap = new HashMap<>();

    private final static String PARAMETER_PRICE = "product_price";
    private final static String PARAMETER_NAME = "product_name";
    private final static String PARAMETER_DESCRIPTION = "product_description";
    private final static String PARAMETER_LANGUAGE = "product_language";
    private final static String PARAMETER_ID = "product_id";
    private final static String PARAMETER_CURRENT_PRODUCT = "current_product";
    private final static String PARAMETER_COUNT = "product_count";
    private final static String PARAMETER_MANUFACTURE = "product_manufacture";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        response.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        byte[] imageBytes = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        List fileItems = null;

        try {
            fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();

            imageBytes =  buildParametersMap(iterator);

            int id = Integer.parseInt(parametersMap.get(PARAMETER_ID));

            Product product = productService.findProduct(id);

            int productIndex = productService.getProductStorage().indexOf(product);

            String price = parametersMap.get(PARAMETER_PRICE);
            String name = parametersMap.get(PARAMETER_NAME);
            String description = parametersMap.get(PARAMETER_DESCRIPTION);
            String currentLanguage = parametersMap.get(PARAMETER_LANGUAGE);
            String count = parametersMap.get(PARAMETER_COUNT);
            String manufacture = parametersMap.get(PARAMETER_MANUFACTURE);

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

            if(imageBytes != null && imageBytes.length != 0){
                product.setImage(imageBytes);
            }

            productService.updateProduct(product,currentLanguage);

            productService.getProductStorage().set(productIndex,product);

            request.getSession().setAttribute(PARAMETER_CURRENT_PRODUCT,product);
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_PRODUCT);
        } catch (FileUploadException | ServiceException | RuntimeException e) {
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
      }

    }



    private void putParameter(FileItem fi) throws UnsupportedEncodingException {
        String parameterName = fi.getFieldName();
        String parameterValue = fi.getString(ControllerConstant.WebProperty.CHARACTER_ENCODING);
        parametersMap.put(parameterName,parameterValue);
        
    }


    private byte[] buildParametersMap(Iterator iterator) throws UnsupportedEncodingException {

        byte[] imageBytes = null;
        while (iterator.hasNext()){
            FileItem fi = (FileItem)iterator.next();
            if(fi.isFormField()){
                putParameter(fi);
            }else {
                imageBytes = fi.get();
            }
        }
        return imageBytes;
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


