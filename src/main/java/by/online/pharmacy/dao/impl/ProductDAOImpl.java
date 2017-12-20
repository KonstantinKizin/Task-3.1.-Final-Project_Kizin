package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Product;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private final static Logger logger = Logger.getLogger(CustomerDAOImpl.class);

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private final static String TABLE_FIRST = "product";

    private final static String TABLE_SECOND = "translation_product";

    private final static String SQL_INSERT_PRODUCT = "INSERT INTO "+TABLE_FIRST+" (prescription," +
            "count, price,dosage,image) VALUES(?,?,?,?,?)";

    private final static String SQL_SELECT_LAST_PRODUCT_ID = "SELECT MAX(product_id) as last FROM "+TABLE_FIRST;

    private final static String SQL_INSERT_PRODUCT_ITEMS = "INSERT INTO "+TABLE_SECOND+" " +
            "VALUES(?,?,?,?,?,?)";

    private final static int PRESCRIPTION_COLUMN_INDEX = 1;
    private final static int COUNT_COLUMN_INDEX = 2;
    private final static int PRICE_COLUMN_INDEX = 3;
    private final static int DOSAGE_COLUMN_INDEX = 4;
    private final static int IMAGE_COLUMN_INDEX = 5;
    private final static String MAX_PRODUCT_ID_COLUMN_NAME = "last";
    private final static int PRODUCT_ID_COLUMN_INDEX = 1;
    private final static int ENG_LANGUAGE_ID_COLUMN_VALUE = 1;
    private final static int RUS_LANGUAGE_ID_COLUMN_VALUE = 2;
    private final static int LANGUAGE_ID_COLUMN_INDEX = 2;
    private final static int NAME_COLUMN_INDEX = 3;
    private final static int MANUFACTURE_COLUMN_INDEX = 4;
    private final static int DESCRIPTION_COLUMN_INDEX = 5;
    private final static int CATEGORY_COLUMN_INDEX = 6;







    @Override
    public void save(Product product) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement firstStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT);
            PreparedStatement secondStatement = connection.getPreparedStatement(SQL_SELECT_LAST_PRODUCT_ID);
            PreparedStatement thirdStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT_ITEMS);
            PreparedStatement fifthStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT_ITEMS);
            FileInputStream inputStream = new FileInputStream(product.getImage())
            ){

            firstStatement.setBoolean(PRESCRIPTION_COLUMN_INDEX,product.isPrescription());
            firstStatement.setInt(COUNT_COLUMN_INDEX,product.getCount());
            firstStatement.setFloat(PRICE_COLUMN_INDEX,product.getPrice());
            firstStatement.setFloat(DOSAGE_COLUMN_INDEX,product.getDosage());
            File image = product.getImage();

            firstStatement.setBinaryStream(IMAGE_COLUMN_INDEX,
                    inputStream,
                    (int)image.length()
            );

            firstStatement.executeUpdate();

            ResultSet rs = secondStatement.executeQuery();

            if(rs != null){

                if(rs.next()) {
                    int lastId = rs.getInt(MAX_PRODUCT_ID_COLUMN_NAME);
                    thirdStatement.setInt(PRODUCT_ID_COLUMN_INDEX,lastId);
                    thirdStatement.setInt(LANGUAGE_ID_COLUMN_INDEX,ENG_LANGUAGE_ID_COLUMN_VALUE);
                    thirdStatement.setString(NAME_COLUMN_INDEX,product.getProductItem().getEngName());
                    thirdStatement.setString(MANUFACTURE_COLUMN_INDEX,product.getProductItem().getEngManufacture());
                    thirdStatement.setString(DESCRIPTION_COLUMN_INDEX,product.getProductItem().getEngDescription());
                    thirdStatement.setString(CATEGORY_COLUMN_INDEX,product.getProductItem().getEngCategory());
                    thirdStatement.executeUpdate();

                    fifthStatement.setInt(PRODUCT_ID_COLUMN_INDEX,lastId);
                    fifthStatement.setInt(LANGUAGE_ID_COLUMN_INDEX,RUS_LANGUAGE_ID_COLUMN_VALUE);
                    fifthStatement.setString(NAME_COLUMN_INDEX,product.getProductItem().getRusName());
                    fifthStatement.setString(MANUFACTURE_COLUMN_INDEX,product.getProductItem().getRusManufacture());
                    fifthStatement.setString(DESCRIPTION_COLUMN_INDEX,product.getProductItem().getRusDescription());
                    fifthStatement.setString(CATEGORY_COLUMN_INDEX,product.getProductItem().getRusCategory());
                    fifthStatement.executeUpdate();
                }
            }

        }catch (SQLException e){
            logger.error("Excption in save product method",e);
            throw new DAOException("Save product method",e);

        }catch (IOException e){
            logger.error("Exception when try to save image in product save method",e);
            throw new DAOException("storing image in product table",e);
        }

    }



    @Override
    public Product get(int id, String locale) {






        return null;
    }



    @Override
    public void update(Product product) throws DAOException {

    }



    @Override
    public void delete(Product product) throws DAOException {

    }



    @Override
    public List<Product> getAll() throws DAOException {
        return null;
    }



    @Override
    public void delete(int id) {

    }


}
