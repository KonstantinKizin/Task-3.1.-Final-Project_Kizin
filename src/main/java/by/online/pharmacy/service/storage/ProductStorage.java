package by.online.pharmacy.service.storage;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.factory.DAOFactory;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.service.exception.StorageException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ProductStorage {

    private final static DAOFactory factory = DAOFactory.getInstance();

    private final static ProductDAO productDAO  = factory.getProductDAO();

    private static List<Product> productList = new ArrayList<>();

    private final static ProductStorage instance = new ProductStorage();

    private ProductStorage(){}

    static {
        try {
            List<Product> products =  productDAO.getAll();
            productList.addAll(products);
        } catch (DAOException e) {
            throw new StorageException("Exception while product list is loading",e);
        }
    }

    public  static ProductStorage getInstance(){
        return instance;
    }

    public Product set(int index,Product product){
        synchronized (productList){
        return productList.set(index,product);
        }
    }

    public boolean add(Product product){
        synchronized (productList){
            return productList.add(product);
        }
    }

    public Product get(int index){
        return productList.get(index);
    }

    public int size(){
        return productList.size();
    }

    public boolean remove(Product product){

        synchronized (productList){
            return productList.remove(product);
        }
    }

    public Product remove(int index){
        synchronized (productList){
            return productList.remove(index);
        }
    }

    public Iterator<Product> iterator(){
        return productList.iterator();
    }

    public int indexOf(Product product){
        return productList.indexOf(product);
    }

    public List<Product> getProductList(){return productList;}





}
