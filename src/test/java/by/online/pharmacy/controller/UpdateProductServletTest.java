package by.online.pharmacy.controller;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;

import org.apache.commons.fileupload.FileUploadException;


import org.junit.Before;
;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import java.io.UnsupportedEncodingException;



public class UpdateProductServletTest {

    private UpdateProductServlet uProduct;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Product product;

    @Before
   public void setUp() throws ServiceException, FileUploadException, UnsupportedEncodingException {
        MockitoAnnotations.initMocks(this);
        uProduct = new UpdateProductServlet();
        uProduct.productService = mock(ProductService.class);

    }


    @Test
    public void when_price_change() throws IOException, ServletException, ServiceException {


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
