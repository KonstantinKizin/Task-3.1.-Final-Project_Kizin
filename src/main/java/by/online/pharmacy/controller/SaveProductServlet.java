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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveProductServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final ProductService productService = factory.getProductService();
    private final Map<String,String> parametersMap = new HashMap<String,String>();




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] imageBytes = null;
        Product product = null;
        ProductItem productItem = null;

        request.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        response.setCharacterEncoding(ControllerConstant.WebProperty.CHARACTER_ENCODING);

        String language = (String) request.getSession().getAttribute("language");

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
            product.setPrice(Float.parseFloat(parametersMap.get("product_price")));
            product.setCount(Integer.parseInt(parametersMap.get("product_count")));
            product.setPrescription(Boolean.parseBoolean(parametersMap.get("product_prescription")));
            product.setImage(imageBytes);

            productItem = new ProductItem();
            productItem.setName(parametersMap.get("product_name"));
            productItem.setManufacture(parametersMap.get("product_manufacture"));
            productItem.setDescription(parametersMap.get("product_description"));
            productItem.setCategoryName(parametersMap.get("product_category"));

            product.getProductItemMap().put(parametersMap.get("product_language"),productItem);

            productService.saveProduct(product);

            response.sendRedirect("/frontController?hidden=show_products&language="+language);

        } catch (FileUploadException e) {
            response.sendRedirect(ControllerConstant.WebProperty.ERROR_PAGE);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }



    private void createParametersMap(FileItem fi) throws UnsupportedEncodingException {

        String parameterName = fi.getFieldName();
        String parameterValue =  fi.getString("UTF-8");
        parametersMap.put(parameterName,parameterValue);
    }


}
