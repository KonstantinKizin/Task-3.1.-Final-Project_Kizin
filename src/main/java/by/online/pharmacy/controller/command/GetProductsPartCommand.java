package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetProductsPartCommand implements Command {


    private final int RECORDS_PER_PAGE = 3;
    private final int PAGE_DIF = 1;
    private final double DOUBLE_ONE = 1.0;
    private final String NO_OF_PAGE_ATTR_NAME = "noOfPages";
    private final String PAGE_PARAMETER_NAME = "page";
    private final String CURRENT_PAGE_ATTR_NAME = "currentPage";
    private final String ATTRIBUTE_PART_OF_PRODUCT = "productPart";
    private static int noOfRecords;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {


        ServletContext context = request.getServletContext();

        List<Product> pList = (List<Product>)context.getAttribute("productList");

        int page = 1;

        noOfRecords = pList.size() - RECORDS_PER_PAGE;

        if(request.getParameter(PAGE_PARAMETER_NAME) != null){
            page = Integer.parseInt(request.getParameter(PAGE_PARAMETER_NAME));
        }

        List<Product> responseList = getPartOfEmployee(pList,(page-PAGE_DIF)*RECORDS_PER_PAGE,RECORDS_PER_PAGE);

        int noOfPages = (int) Math.ceil(noOfRecords * DOUBLE_ONE / RECORDS_PER_PAGE);

        request.getSession().setAttribute(ATTRIBUTE_PART_OF_PRODUCT,responseList);
        request.getSession().setAttribute(NO_OF_PAGE_ATTR_NAME, noOfPages);
        request.getSession().setAttribute(CURRENT_PAGE_ATTR_NAME, page);

        response.sendRedirect("/products");


    }



    public List<Product> getPartOfEmployee(List<Product> list , int start , int count){

        List<Product> productsPart = new ArrayList<>();
        int index = 0;
        for(int i = start; i < (start + count); i++  ){
            productsPart.add(list.get(i));
            index++;
        }
        noOfRecords = list.size() - index;
        return productsPart;
    }





}
