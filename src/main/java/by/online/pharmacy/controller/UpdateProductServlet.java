package by.online.pharmacy.controller;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Product;
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

public class UpdateProductServlet extends HttpServlet {

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final ProductService productService = factory.getProductService();

    private Map<String,String> parametersMap = new HashMap<String,String>();

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

             Product product = productService.findProduct(Integer.parseInt(parametersMap.get("product_id")));

             String price = parametersMap.get("product_price");
             String name = parametersMap.get("product_name");
             String description = parametersMap.get("product_description");
             String prescription = parametersMap.get("prescription");
             String currentLanguage = parametersMap.get("current_language");

             if(!price.isEmpty()){
                 product.setPrice(Float.parseFloat(price));
             }

             if(!name.isEmpty()){
                 product.getProductItemMap().get(currentLanguage).setName(name);
             }

             if(!description.isEmpty()){
                 product.getProductItemMap().get(currentLanguage).setDescription(description);
             }

             if(!prescription.isEmpty()){
                 product.setPrescription(Boolean.parseBoolean(prescription));
             }

             if(imageBytes != null){
                 product.setImage(imageBytes);
             }

             productService.updateProduct(product);

             request.getSession().setAttribute("certain_product",product);

             response.sendRedirect("/product.jsp");

        } catch (FileUploadException | ServiceException e) {

            e.printStackTrace();

      }

    }



    private void putParameter(FileItem fi) throws UnsupportedEncodingException {

        String parameterName = fi.getFieldName();
        String parameterValue = fi.getString("UTF-8");
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




}
