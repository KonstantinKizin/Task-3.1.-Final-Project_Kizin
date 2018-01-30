package by.online.pharmacy.service.impl;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.ProductService;
import by.online.pharmacy.service.exception.ServiceException;
import by.online.pharmacy.service.exception.StorageException;
import by.online.pharmacy.service.exception.ValidatorException;
import by.online.pharmacy.service.storage.ProductStorage;
import by.online.pharmacy.service.validator.ProductValidator;
import by.online.pharmacy.service.validator.impl.ProductValidatorImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProductServiceImpl implements ProductService {

    private final DAOFactory factory = DAOFactory.getInstance();

    private final ProductDAO productDAO  = factory.getProductDAO();

    private final ProductStorage storage = ProductStorage.getInstance();

    private final ProductValidator validator = new ProductValidatorImpl();



    /*
    * As result of saving the method return just saved product id.
    * */

    @Override
    public int saveProduct(Product product) throws ServiceException {
        try{
            if(!validator.validate(product)){
                throw new ValidatorException("invalid product for save");
            }
            int id = productDAO.save(product);
            storage.add(product);
            return id;
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Exception in service save product method",e);
        }

    }

    @Override
    public Product findProduct(int id) throws ServiceException {
        try {
            Iterator<Product> itr = storage.iterator();
            Product product = new Product();
            while (itr.hasNext()){
                product = itr.next();
                if(product.getId() == id){
                    break;
                }
            }

            return product;
        } catch (StorageException e) {
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
            return  true;
        } catch (DAOException e) {
            throw new ServiceException("update product method",e);
        }
    }

    @Override
    public boolean deleteProduct(int id) throws ServiceException {

        try {
            productDAO.delete(id);
            storage.remove(id);
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
    public List<Product> getProducts(int start, int count) {



        return null;
    }


    @Override
    public Product findProductsByName(String productName, String language) {
        Product product = new Product();
        Product buf = null;
        Iterator<Product> itr = storage.iterator();
        while (itr.hasNext()){
            buf = itr.next();
            if(buf.getProductItemMap().get(language).getName().equalsIgnoreCase(productName)){
                product = buf;
                break;
            }
        }
        return product;
    }

    @Override
    public List<Product> findProductsByCategory(String categoryName, String language) throws ServiceException {

        Product product = null;
        List<Product> productList = new ArrayList<>();
        Iterator<Product> itr = storage.iterator();
        while (itr.hasNext()){
            product = itr.next();
            if(product.getProductItemMap().get(language).getCategoryName().equalsIgnoreCase(categoryName)){
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findProductsCheaperThan(long price) throws ServiceException {


        Product product = null;
        List<Product> productList = new ArrayList<>();
        Iterator<Product> itr = storage.iterator();
        while (itr.hasNext()){
            product = itr.next();
            if(product.getPrice() < price){
                productList.add(product);
            }
        }
        return productList;
    }




}
