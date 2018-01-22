package by.online.pharmacy.controller;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.entity.ValidationError;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

public class SaveProductServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final ProductService productService = factory.getProductService();
    private final ProductValidator requestvalidatore = new ProductValidatorImpl();
    private final Map<String,String> parametersMap = new HashMap<String,String>();
    private final static Logger logger = Logger.getLogger(SaveProductServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] imageBytes = null;
        Product product = null;
        ProductItem productItem = null;

        request.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        response.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        Locale locale  = (Locale) request.getSession().getAttribute("language");
        String language = locale.getLanguage();

            List<ValidationError> errors = requestvalidatore.validate(request);


            if(errors.size() != 0){

                HttpSession session = request.getSession();

                if(session.getAttribute("ProductErrorsList") != null){
                    session.removeAttribute("ProductErrorsList");
                }
                session.setAttribute("ProductErrorsList",errors);
                response.sendRedirect(ControllerConstant.WebProperty.PAGE_SAVE_PRODUCT);
            }else {
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


                    product = new Product();
                    product.setPrice(Float.parseFloat(parametersMap.get(ProductProperty.PRICE_PARAMETER)));
                    product.setCount(Integer.parseInt(parametersMap.get(ProductProperty.COUNT_PARAMETER)));
                    product.setPrescription(Boolean.parseBoolean(parametersMap.get(ProductProperty.PRESCRIPTION_PARAMETER)));
                    product.setImage(imageBytes);

                    productItem = new ProductItem();
                    productItem.setName(parametersMap.get(ProductProperty.NAME_PARAMETER));
                    productItem.setManufacture(parametersMap.get(ProductProperty.MANUFACTURE_PARAMETER));
                    productItem.setDescription(parametersMap.get(ProductProperty.DESCRIPTION_PARAMETER));
                    productItem.setCategoryName(parametersMap.get(ProductProperty.CATEGORY_PARAMETER));

                    product.getProductItemMap().put(parametersMap.get(ProductProperty.LANGUAGE_PARAMETER),productItem);

                    productService.saveProduct(product);

                    response.sendRedirect("/frontController?hidden=show_products&language="+language);

                } catch (FileUploadException  | ServiceException e) {
                    logger.error("Exception in save product Servlet ",e);
                    e.printStackTrace();
                    response.sendRedirect(ControllerConstant.WebProperty.PAGE_ERROR);
                }

            }



    }



    private void createParametersMap(FileItem fi) throws UnsupportedEncodingException {

        String parameterName = fi.getFieldName();
        String parameterValue =  fi.getString("UTF-8");
        parametersMap.put(parameterName,parameterValue);
    }


}
