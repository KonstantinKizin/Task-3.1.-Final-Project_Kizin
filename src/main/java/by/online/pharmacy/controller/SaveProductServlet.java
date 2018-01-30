package by.online.pharmacy.controller;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;

import by.online.pharmacy.service.validator.ProductValidator;
import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

public class SaveProductServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final static Logger logger = Logger.getLogger(SaveProductServlet.class);
    private final ProductService productService = factory.getProductService();
    private final ProductValidator parametersValidate = new ProductValidatorImpl();
    private final Map<String,String> parametersMap = new HashMap<>();
    private final static String ATTRIBUTE_SAVED_PRODUCT = "saved_product";
    private final static String ATTRIBUTE_PRODUCT_LIST = "productList";
    private final static String ATTRIBUTE_REASON = "reason";
    private final static String ATTRIBUTE_REASON_TEXT = "invalid values was put";



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        response.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        byte[] imageBytes = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            List fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()){

                FileItem fi = (FileItem)iterator.next();
                if(fi.isFormField()){
                    createParametersMap(fi);
                }else {
                    imageBytes = fi.get();
                }
            }

            if(!parametersValidate.parametersValidate(parametersMap)){
                request.getSession().setAttribute(ATTRIBUTE_REASON,ATTRIBUTE_REASON_TEXT);
                request.getRequestDispatcher(ControllerConstant.WebProperty.PAGE_ERROR);
            }else {
                Product product = new Product();
                product.setPrice(Float.parseFloat(parametersMap.get(ProductProperty.PRICE_PARAMETER)));
                product.setCount(Integer.parseInt(parametersMap.get(ProductProperty.COUNT_PARAMETER)));
                product.setPrescription(Boolean.parseBoolean(parametersMap.get(ProductProperty.PRESCRIPTION_PARAMETER)));
                product.setImage(imageBytes);

                ProductItem productItem = new ProductItem();
                productItem.setName(parametersMap.get(ProductProperty.NAME_PARAMETER));
                productItem.setManufacture(parametersMap.get(ProductProperty.MANUFACTURE_PARAMETER));
                productItem.setDescription(parametersMap.get(ProductProperty.DESCRIPTION_PARAMETER));
                productItem.setCategoryName(parametersMap.get(ProductProperty.CATEGORY_PARAMETER));

                product.getProductItemMap().put(parametersMap.get(ProductProperty.LANGUAGE_PARAMETER),productItem);
                int id = productService.saveProduct(product);
                ((List<Product>)this.getServletContext().getAttribute(ATTRIBUTE_PRODUCT_LIST)).add(product);
                request.getSession().setAttribute(ATTRIBUTE_SAVED_PRODUCT,product);
                response.sendRedirect(ControllerConstant.WebProperty.PAGE_PRODUCT_SETTING+id);
            }

        } catch (FileUploadException  | ServiceException e) {
            logger.error("Exception in save product Servlet ",e);
            response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
        }

    }



    private void createParametersMap(FileItem fi) throws UnsupportedEncodingException {
        String parameterName = fi.getFieldName();
        String parameterValue =  fi.getString("UTF-8");
        parametersMap.put(parameterName,parameterValue);
    }
}