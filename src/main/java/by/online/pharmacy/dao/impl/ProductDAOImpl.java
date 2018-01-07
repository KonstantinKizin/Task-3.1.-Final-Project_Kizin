package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.dao.impl.connectionPool.ConnectionPool;
import by.online.pharmacy.dao.impl.connectionPool.ConnectionPoolImpl;
import by.online.pharmacy.dao.impl.connectionPool.WrappedConnection;
import by.online.pharmacy.entity.Product;
import by.online.pharmacy.entity.ProductItem;
import org.apache.log4j.Logger;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductDAOImpl implements ProductDAO {

    private final static Logger logger = Logger.getLogger(CustomerDAOImpl.class);

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private final Map<String,Integer> languageMap = new HashMap<>();


    private final static String TABLE_PRODUCT = "product";

    private final static String TABLE_TRANSLATION_PRODUCT = "translation_product";

    private final static String TABLE_TRANSLATION_CATEGORY = "translation_category";

    private final static String SQL_LANGUAGE_CONDITION = "HAVING language.name = ?";

    private final static String SQL_ID_CONDITION = "HAVING product.product_id = ?";

    private final static String SQL_INSERT_PRODUCT = "INSERT INTO "+TABLE_PRODUCT+" (prescription," +
            "count, price,dosage,image,category_id) VALUES(?,?,?,?,?,?)";

    private final static String SQL_SELECT_CATEGORY_ID_BY_NAME = "SELECT category_id FROM "+TABLE_TRANSLATION_CATEGORY+" " +
            "WHERE category_name = ?";

    private final static String SQL_SELECT_ALL_PRODUCTS = "SELECT product_id,price,count,image,dosage,prescription" +
            " FROM "+TABLE_PRODUCT;


    private final static String SQL_SELECT_ALL_PRODUCT_ITEMS = "SELECT\n" +
            "translation_product.product_id,\n" +
            "translation_product.language_id,\n" +
            "translation_product.name,\n" +
            "translation_product.manufacture,\n" +
            "translation_product.description,\n" +
            "translation_category.category_name,\n" +
            "language.name as 'lang_name'\n" +
            "from translation_product\n" +
            "inner join product on product.product_id = translation_product.product_id\n" +
            "inner join language on translation_product.language_id = language.lang_id\n" +
            "inner join translation_category on product.category_id = translation_category.category_id and language.lang_id = translation_category.lang_id";

    private final static String SQL_INSERT_PRODUCT_ITEMS = "INSERT INTO  "+TABLE_TRANSLATION_PRODUCT+" VALUES(?,?,?,?,?)" ;

    private final static String SQL_DELETE_PRODUCT = "DELETE from "+TABLE_PRODUCT+" WHERE product_id=?";

    private final static String SQL_DELETE_PRODUCT_ITEMS = "DELETE from "+TABLE_TRANSLATION_PRODUCT+" WHERE product_id=?";

    private final static String SQL_GET_PRODUCTS = "SELECT \n" +
            "product.product_id, \n" +
            "product.price,\n" +
            "product.count,\n" +
            "product.dosage,\n" +
            "product.image,\n" +
            "product.prescription,\n" +
            "translation_product.name, \n" +
            "translation_product.manufacture,\n" +
            "translation_product.description,\n" +
            "translation_category.category_name,\n" +
            "language.name as lang_name \n" +
            "FROM product\n" +
            "INNER JOIN translation_category ON translation_category.category_id = product.category_id\n" +
            "INNER JOIN language on language.lang_id = translation_category.lang_id\n" +
            "INNER JOIN translation_product ON translation_product.product_id = product.product_id\n" +
            "AND translation_product.language_id = language.lang_id";

    private final static String SQL_GET_ALL_PRODUCTS_BY_LANGUAGE = SQL_GET_PRODUCTS +"\n"
            +SQL_LANGUAGE_CONDITION;

    private final static String SQL_GET_PRODUCT_BY_ID = SQL_GET_PRODUCTS+"\n"
            +SQL_ID_CONDITION;



   private final String ID_COLUMN_NAME = "product_id";
   private final String PRICE_COLUMN_NAME = "price";
   private final String COUNT_COLUMN_NAME = "count";
   private final String DOSAGE_COLUMN_NAME = "dosage";
   private final String IMAGE_COLUMN_NAME = "image";
   private final String PRESCRIPTION_COLUMN_NAME = "prescription";
   private final String NAME_COLUMN_NAME = "name";
   private final String MANUFACTURE_COLUMN_NAME = "manufacture";
   private final String DESCRIPTION_COLUMN_NAME = "description";
   private final String CATEGORY_COLUMN_NAME = "category_name";
   private final String LANGUAGE_NAME_COLUMN  = "lang_name";


   public ProductDAOImpl(){

       languageMap.put("en",1);
       languageMap.put("ru",2);
   }




    @Override
    public void save(Product product) throws DAOException {

        try {
            saveProductAsTransaction(product);
        } catch (SQLException | DAOException e) {
            logger.error("Exception in save product method", e);
            throw new DAOException("Save product method", e);
        }
    }


    @Override
    public Product get(int id) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
        PreparedStatement statement = connection.getPreparedStatement(SQL_GET_PRODUCT_BY_ID)){

         statement.setInt(1,id);
         Product product = null;
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()){

             if(product == null){
                 product = new Product();
                 Blob image = resultSet.getBlob(IMAGE_COLUMN_NAME);
                 byte[] imageBytes = image.getBytes(1,(int)image.length());
                 product.setImage(imageBytes);
                 product.setPrescription(resultSet.getBoolean(PRESCRIPTION_COLUMN_NAME));
                 product.setPrice(resultSet.getFloat(PRICE_COLUMN_NAME));
                 product.setCount(resultSet.getInt(COUNT_COLUMN_NAME));
                 product.setId(resultSet.getInt(ID_COLUMN_NAME));
                 product.setDosage(resultSet.getFloat(DOSAGE_COLUMN_NAME));
             }

             String language = resultSet.getString(LANGUAGE_NAME_COLUMN);

             ProductItem productItem = new ProductItem();
             product.getProductItemMap().put(language,productItem);
             productItem.setDescription(resultSet.getString(DESCRIPTION_COLUMN_NAME));
             productItem.setName(resultSet.getString(NAME_COLUMN_NAME));
             productItem.setCategoryName(resultSet.getString(CATEGORY_COLUMN_NAME));
             productItem.setManufacture(resultSet.getString(MANUFACTURE_COLUMN_NAME));
         }
         return product;
        } catch (SQLException | DAOException e) {
            logger.error("Exception in get Product by id method",e);
            throw new DAOException(e);
        }


    }



    @Override
    public List<Product> getAll(String language) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement statement = connection.getPreparedStatement(SQL_GET_ALL_PRODUCTS_BY_LANGUAGE);
            ){

            statement.setString(1,language);
            ResultSet rs = statement.executeQuery();

            List<Product> productList = new ArrayList<>();

            if(rs != null){

                while (rs.next()){
                    Product product = new Product();
                    ProductItem productItem = new ProductItem();
                    product.getProductItemMap().put(language,productItem);
                    product.setPrice(rs.getFloat(PRICE_COLUMN_NAME));
                    product.setPrescription(rs.getBoolean(PRESCRIPTION_COLUMN_NAME));
                    Blob image = rs.getBlob(IMAGE_COLUMN_NAME);
                    byte[] imageBytes = image.getBytes(1,(int)image.length());
                    product.setImage(imageBytes);
                    product.setDosage(rs.getFloat(DOSAGE_COLUMN_NAME));
                    product.setCount(rs.getInt(COUNT_COLUMN_NAME));
                    product.setId(rs.getInt(ID_COLUMN_NAME));
                    productItem.setCategoryName(rs.getString(CATEGORY_COLUMN_NAME));
                    productItem.setDescription(rs.getString(DESCRIPTION_COLUMN_NAME));
                    productItem.setManufacture(rs.getString(MANUFACTURE_COLUMN_NAME));
                    productItem.setName(rs.getString(NAME_COLUMN_NAME));
                    productList.add(product);
                }
            }
            return productList;

        }catch (SQLException e){
            logger.error("Exception in getAll product by language method",e);
            throw new DAOException("get all product method ",e);
        }

    }

    @Override
    public List<Product> getAll() throws DAOException {
        try {
            return getAllProductsAsTransaction();
        } catch (SQLException e) {
            logger.error("Exception in getAll product method", e);
            throw new DAOException("GetAll product method", e);
        }
    }




    //solve the problem next time.
    @Override
    public void delete(int id) throws DAOException{
        try {
            this.deleteProductAsTransaction(id);
        } catch (SQLException | DAOException e) {
            logger.error("Exception in delete product method", e);
            throw new DAOException("Delete product method", e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {


    }




    private void saveProductAsTransaction(Product product) throws DAOException, SQLException {
        WrappedConnection connectionRefCopy = null;

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement productStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT);
            PreparedStatement tProductStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT_ITEMS);
            PreparedStatement categoryStatement = connection.getPreparedStatement(SQL_SELECT_CATEGORY_ID_BY_NAME)
        ){
            connectionRefCopy = connection;
            connection.setAutoCommit(false);
            Map<String,ProductItem> productItemMap = product.getProductItemMap();
            String lang = null;
            for(String tmp: productItemMap.keySet()){
                lang = tmp;
            }
            categoryStatement.setString(1,productItemMap.get(lang).getCategoryName());

            ResultSet categoryRs = categoryStatement.executeQuery();

            int catId = 0;

            if(categoryRs.next()){
                catId = categoryRs.getInt(1);
            }
            productStatement.setBoolean(1,product.isPrescription());
            productStatement.setInt(2,product.getCount());
            productStatement.setFloat(3,product.getPrice());
            productStatement.setFloat(4,product.getDosage());
            productStatement.setBytes(5,product.getImage());
            Blob image = new javax.sql.rowset.serial.SerialBlob(product.getImage());
            productStatement.setBlob(5,image);
            productStatement.setInt(6,catId);
            productStatement.executeUpdate();

            ResultSet rs = productStatement.getGeneratedKeys();
            int productId = 0;
            if(rs.next()){
                productId = rs.getInt(1);
            }

            Integer languageId = languageMap.get(lang);
            tProductStatement.setInt(1,productId);
            tProductStatement.setInt(2,languageId);
            tProductStatement.setString(3,productItemMap.get(lang).getName());
            tProductStatement.setString(5,productItemMap.get(lang).getDescription());
            tProductStatement.setString(4,productItemMap.get(lang).getManufacture());
            tProductStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            connectionRefCopy.rollback();
            throw e;
        }finally {
            try {
                connectionRefCopy.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException("Exception in save product method, in set autocommit true",e);
            }
        }
    }





    private void deleteProductAsTransaction(int id) throws DAOException, SQLException {
        WrappedConnection connectionRefCopy = null;
        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement productStatement = connection.getPreparedStatement(SQL_DELETE_PRODUCT);
            PreparedStatement tProductStatement = connection.getPreparedStatement(SQL_DELETE_PRODUCT_ITEMS)){

            connection.setAutoCommit(false);
            connectionRefCopy = connection;

            tProductStatement.setInt(1,id);
            tProductStatement.executeUpdate();

            productStatement.setInt(1,id);
            productStatement.executeUpdate();

            connection.commit();
        }catch (SQLException e){
            connectionRefCopy.rollback();
            throw e;

        }finally {
            try {
                connectionRefCopy.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException("Exception in save product method, in set autocommit true",e);
            }
        }
    }




    private List<Product> getAllProductsAsTransaction() throws DAOException, SQLException {

        Map<Integer,Product> productsMap = new HashMap<>();
        WrappedConnection connectionRefCopy = null;
        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            Statement productStatement = connection.createStatement();
            Statement tProductStatement = connection.createStatement()
        ){
            connection.setAutoCommit(false);
            connectionRefCopy = connection;

            ResultSet productRS = productStatement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
            while (productRS.next()){
                Product product = buildProduct(productRS);
                int productId = productRS.getInt(ID_COLUMN_NAME);
                productsMap.put(productId,product);
            }
            ResultSet tProductRS = tProductStatement.executeQuery(SQL_SELECT_ALL_PRODUCT_ITEMS);
            while (tProductRS.next()){
                int id = tProductRS.getInt(ID_COLUMN_NAME);
                String language = tProductRS.getString(LANGUAGE_NAME_COLUMN);
                Product product = productsMap.get(id);
                if(product != null) {
                    if(product.getProductItemMap().containsKey(language)){
                        this.buildProductItem(language,tProductRS,product);
                    }else {
                        product.getProductItemMap().put(language,new ProductItem());
                        this.buildProductItem(language,tProductRS,product);

                    }
                }
            }
            connection.commit();
            return new ArrayList<>(productsMap.values());
        }catch (SQLException e){
            connectionRefCopy.rollback();
            throw e;
        }finally {
            try {
                connectionRefCopy.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException("Exception in get All  method, in set autocommit true",e);
            }
        }
    }




    private Product getProductById(int id , Collection<Product> products){
       Product product = null;
       for(Product tmp: products){
           if(tmp.getId() == id){
               product = tmp;
               break;
           }
       }
       return product;
    }



    private Product buildProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        int productId = rs.getInt(ID_COLUMN_NAME);
        product.setId(productId);
        product.setCount(rs.getInt(COUNT_COLUMN_NAME));
        product.setPrice(rs.getFloat(PRICE_COLUMN_NAME));
        product.setPrescription(rs.getBoolean(PRESCRIPTION_COLUMN_NAME));
        product.setDosage(rs.getFloat(DOSAGE_COLUMN_NAME));
        Blob image = rs.getBlob(IMAGE_COLUMN_NAME);
        byte[] imageBytes = image.getBytes(1, (int) image.length());
        product.setImage(imageBytes);
        return product;
    }


    private void buildProductItem(String language, ResultSet rs, Product product) throws SQLException{
        product.getProductItemMap().get(language).setName(rs.getString(NAME_COLUMN_NAME));
        product.getProductItemMap().get(language).setManufacture(rs.getString(MANUFACTURE_COLUMN_NAME));
        product.getProductItemMap().get(language).setCategoryName(rs.getString(MANUFACTURE_COLUMN_NAME));
        product.getProductItemMap().get(language).setDescription(rs.getString(DESCRIPTION_COLUMN_NAME));
    }
}
