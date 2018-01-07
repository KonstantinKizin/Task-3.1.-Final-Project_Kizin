package by.online.pharmacy.entity;
import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.impl.ProductDAOImpl;

import java.util.List;


public class test {

    public static void main(String[] args) throws DAOException {

        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAll();

        for(Product product : products){
            System.out.println(product);
            System.out.println(product.getProductItemMap());
            System.out.println(product.getProductItemMap().size());
        }





    }
}
