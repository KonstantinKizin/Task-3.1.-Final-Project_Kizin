package by.online.pharmacy.controller.command;

import by.online.pharmacy.controller.constant.ControllerConstant;
import by.online.pharmacy.controller.exception.ControllerException;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class SaveProductCommand implements Command {

    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final ProductService productService = factory.getProductService();
    private final static Logger logger = Logger.getLogger(SaveCustomerCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String engDescription = request.getParameter("eng_description");
        String rusDescription = request.getParameter("rus_description");
        String engName = request.getParameter("eng_name");
        String rusName = request.getParameter("rus_name");
        String rusCategory = request.getParameter("rus_category");
        String engCategory = request.getParameter("eng_category");
        String engManufacture = request.getParameter("eng_manufacture");
        String rusManufacture = request.getParameter("rus_manufacture");
        String prescription = request.getParameter("prescription");
        int count = Integer.parseInt(request.getParameter("count"));
        float price = Float.parseFloat(request.getParameter("price"));
        float dosage = Float.parseFloat(request.getParameter("dosage"));
        File image = new File(request.getParameter("image_path"));

        ProductItem productItem = new ProductItem();
        productItem.setEngCategory(engCategory);
        productItem.setRusCategory(rusCategory);
        productItem.setEngDescription(engDescription);
        productItem.setRusDescription(rusDescription);
        productItem.setEngManufacture(engManufacture);
        productItem.setRusManufacture(rusManufacture);
        productItem.setEngName(engName);
        productItem.setRusName(rusName);

        Product product = new Product();
        product.setProductItem(productItem);
        product.setCount(count);
        product.setDosage(dosage);
        product.setImage(image);
        product.setPrescription(Boolean.parseBoolean(prescription));
        product.setPrice(price);


        try {
            productService.saveProduct(product);
            request.getSession().setAttribute(ControllerConstant.WebProperty.PAGE,
                    ControllerConstant.WebProperty.SAVE_PRODUCT_PAGE);
            response.sendRedirect(ControllerConstant.WebProperty.REDIRECT_URL);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
