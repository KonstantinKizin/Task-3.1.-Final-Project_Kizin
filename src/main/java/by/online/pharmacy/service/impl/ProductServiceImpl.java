package by.online.pharmacy.service.impl;

import by.online.pharmacy.controller.constant.ControllerConstant;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProductServiceImpl implements ProductService {

    private  DAOFactory factory = DAOFactory.getInstance();

    private  ProductDAO productDAO  = factory.getProductDAO();

    private  ProductStorage storage = ProductStorage.getInstance();

    private  ProductValidator validator = new ProductValidatorImpl();



    @Override
    public int saveProduct(Product product) throws ServiceException {
        boolean result = false;
        try{
            if(!validator.validate(product)){
                throw new ValidatorException("invalid product for save");
            }
            storage.add(product);
            int id = productDAO.save(product);
            result = true;
            if(result){
                return id;
            }else {
                throw new DAOException("product was not save");
            }
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Exception in service save product method",e);
        }
    }



    @Override
    public byte[] getDefaultImage() throws ServiceException {
        ClassLoader classLoader = getClass().getClassLoader();
        String path = new String(classLoader.getResource(
                ControllerConstant.WebProperty.DEFAULT_PRODUCT_IMAGE_NAME)
                .getPath());
        File file = new File(path);
        BufferedImage image = null;
        byte[] arr = null;
        try {
            image  = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "png", baos );
            baos.flush();
            arr = baos.toByteArray();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return arr;
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
            for(Product product : storage.getProductList()){
                if(product.getId() == id){
                    storage.getProductList().remove(product);
                    break;
                }
            }
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
