package by.online.pharmacy.controller;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class UpdateProductServletTest {

    protected final static String PARAMETER_PRICE = "product_price";
    protected final static String PARAMETER_NAME = "product_name";
    protected final static String PARAMETER_DESCRIPTION = "product_description";
    protected final static String PARAMETER_LANGUAGE = "product_language";
    protected final static String PARAMETER_ID = "product_id";
    protected final static String PARAMETER_CURRENT_PRODUCT = "current_product";
    protected final static String PARAMETER_COUNT = "product_count";
    protected final static String PARAMETER_MANUFACTURE = "product_manufacture";


    private UpdateProductServlet uProduct;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FileItem fileItem;

    @Mock
    private Product product;

    @Mock
    private List<FileItem> iList;

    @Mock
    private Iterator<FileItem> iterator;

    @Before
   public void setUp() throws ServiceException, FileUploadException, UnsupportedEncodingException {
        MockitoAnnotations.initMocks(this);
        uProduct = new UpdateProductServlet();
        uProduct.productService = mock(ProductService.class);
        uProduct.upload = mock(ServletFileUpload.class);
        when(uProduct.upload.parseRequest(request)).thenReturn(iList);
        when(iList.iterator()).thenReturn(iterator);
    }


    @Test
    public void when_price_change() throws IOException, ServletException, ServiceException {

        when(iterator.hasNext()).thenReturn(true,true,true,false);
        when(iterator.next()).thenReturn(fileItem,fileItem,fileItem);
        when(fileItem.isFormField()).thenReturn(true,true,true);
        when(fileItem.getFieldName()).thenReturn(PARAMETER_ID,PARAMETER_PRICE,PARAMETER_LANGUAGE);
        when(fileItem.getString(ControllerConstant.WebProperty.CHARACTER_ENCODING)).thenReturn("10","13.2f","en");
        when(uProduct.productService.findProduct(anyInt())).thenReturn(product);
        uProduct.doPost(request,response);


    }



    private  void buildDefaultProduct(Product product){

        product.setCount(10);
        product.setDosage(100);
        product.setImage(new byte[1024]);
        product.setPrescription(false);
        product.setPrice(12.67f);
        product.setId(1);
        ProductItem productItem = new ProductItem();
        productItem.setName("Eng_name");
        productItem.setManufacture("Eng_manufacture");
        productItem.setDescription("Eng_description");
        productItem.setCategoryName("Eng_Category");
        product.getProductItemMap().put("en",productItem);

    }



}
