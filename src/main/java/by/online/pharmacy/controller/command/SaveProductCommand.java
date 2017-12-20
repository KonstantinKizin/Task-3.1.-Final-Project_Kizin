package by.online.pharmacy.controller.command;

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
import java.io.File;
import java.io.IOException;

import static by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;

public class SaveProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final ProductService productService = factory.getProductService();
    private final ProductValidator productValidator = new ProductValidatorImpl();
    private final static Logger logger = Logger.getLogger(SaveCustomerCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String engDescription = request.getParameter(ProductProperty.ENG_DESCRIPTION_PARAMETER);
        String rusDescription = request.getParameter(ProductProperty.RUS_DESCRIPTION_PARAMETER);
        String engName = request.getParameter(ProductProperty.ENG_NAME_PARAMETER);
        String rusName = request.getParameter(ProductProperty.RUS_NAME_PARAMETER);
        String rusCategory = request.getParameter(ProductProperty.RUS_CATEGORY_PARAMETER);
        String engCategory = request.getParameter(ProductProperty.ENG_CATEGORY_PARAMETER);
        String engManufacture = request.getParameter(ProductProperty.ENG_MANUFACTURE_PARAMETER);
        String rusManufacture = request.getParameter(ProductProperty.RUS_MANUFACTURE_PARAMETER);
        String prescription = request.getParameter(ProductProperty.PRESCRIPTION_PARAMETER);
        String count = request.getParameter(ProductProperty.COUNT_PARAMETER);
        String price = request.getParameter(ProductProperty.PRICE_PARAMETER);
        String dosage = request.getParameter(ProductProperty.DOSAGE_PARAMETER);
        String image =request.getParameter(ProductProperty.IMAGE_URL_PARAMETER);


        ProductItem productItem = null;
        Product product = null;

        try {
                productItem = new ProductItem();
                productItem.setEngCategory(engCategory);
                productItem.setRusCategory(rusCategory);
                productItem.setEngDescription(engDescription);
                productItem.setRusDescription(rusDescription);
                productItem.setEngManufacture(engManufacture);
                productItem.setRusManufacture(rusManufacture);
                productItem.setEngName(engName);
                productItem.setRusName(rusName);

                product = new Product();
                product.setProductItem(productItem);
                product.setCount(Integer.parseInt(count));
                product.setDosage(Float.parseFloat(dosage));
                product.setImage(new File(image));
                product.setPrescription(Boolean.parseBoolean(prescription));
                product.setPrice(Float.parseFloat(price));
                productService.saveProduct(product);
                response.sendRedirect(ControllerConstant.WebProperty.SAVE_PRODUCT_URL);

        } catch (ServiceException | IOException e) {
            throw new ControllerException("Exception in save pruduct command",e);
        }

    }
}
