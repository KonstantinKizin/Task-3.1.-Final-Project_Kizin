package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.StorageException;
import by.online.pharmacy.service.storage.ProductStorage;
import by.online.pharmacy.service.validator.ProductValidator;
import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final DAOFactory factory = DAOFactory.getInstance();

    private final ProductDAO productDAO  = factory.getProductDAO();

    private final ProductStorage storage = ProductStorage.getInstance();

    private final ProductValidator validator = new ProductValidatorImpl();


    @Override
    public int saveProduct(Product product) throws ServiceException {
        try{
            return productDAO.save(product);
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Exception in service save product method",e);
        }

    }

    @Override
    public Product findProduct(int id) throws ServiceException {
        try {
            Product product = storage.getProductMap().get(id);
            if(product == null){
                product = productDAO.get(id);
            }
            return product;
        } catch (StorageException | DAOException e) {
            throw new ServiceException("Find product by id method",e);
        }
    }



    @Override
    public List<Product> getAllProducts(String language) throws ServiceException {
        try {
            return productDAO.getAll(language);
        } catch (DAOException e) {
            throw new ServiceException("Get All products by language method",e);
        }
    }

    @Override
    public List<Product> getAllProduct() throws ServiceException {
        try {
            return productDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("getAllProduct method ",e);
        }
    }

    @Override
    public boolean updateProduct(Product product, String language) throws ServiceException{

        try {
            productDAO.update(product,language);
            storage.getProductMap().replace(product.getId(),product);
            return  true;
        } catch (DAOException e) {
            throw new ServiceException("update product method",e);
        }
    }

    @Override
    public boolean deleteProduct(int id) throws ServiceException {

        try {
            productDAO.delete(id);
            storage.getProductMap().remove(id);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Delete product in service ",e);
        }
    }



    @Override
    public ProductStorage getProductStorage() {
        return ProductStorage.getInstance();
    }




    @Override
    public Product findProductsByName(String productName, String language) {

        Product product = null;

        for(Product tmp : storage.getProductMap().values()){
            if(product.getProductItemMap().get(language).getName().equalsIgnoreCase(productName)){
                product = tmp;
                break;
            }
        }
        return product;
    }

    @Override
    public List<Product> findProductsByCategory(String categoryName, String language) throws ServiceException {

        List<Product> products = new ArrayList<>();
        for(Product tmp : storage.getProductMap().values()){
            if(tmp.getProductItemMap().get(language).getCategoryName().equalsIgnoreCase(categoryName)){
                products.add(tmp);
            }
        }

        return products;
    }

    @Override
    public List<Product> findProductsByManufacture(String manufactureName, String language) throws ServiceException {
        List<Product> products = new ArrayList<>();
        for(Product tmp : storage.getProductMap().values()){
            if(tmp.getProductItemMap().get(language).getManufacture().equalsIgnoreCase(manufactureName)){
                products.add(tmp);
            }
        }

        return products;
    }

    @Override
    public List<Product> findProductsCheaperThan(long price) throws ServiceException {

        List<Product> products = new ArrayList<>();
        for(Product tmp : storage.getProductMap().values()){
            if(tmp.getPrice() < price){
                products.add(tmp);
            }
        }
        return products;

    }




}
