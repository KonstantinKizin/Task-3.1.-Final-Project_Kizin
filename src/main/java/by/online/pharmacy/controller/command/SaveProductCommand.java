package by.online.pharmacy.controller.command;


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

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

public class SaveProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final ProductService productService = factory.getProductService();
    private final ProductValidator productValidator = new ProductValidatorImpl();
    private final static Logger logger = Logger.getLogger(SaveProductCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        ProductItem productItem = null;
        Product product = null;




    }
}
