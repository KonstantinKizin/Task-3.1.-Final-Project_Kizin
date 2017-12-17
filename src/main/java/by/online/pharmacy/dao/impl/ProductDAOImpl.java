package by.online.pharmacy.dao.impl;

import by.online.pharmacy.dao.ConnectionPool;
import by.online.pharmacy.dao.ProductDAO;
import by.online.pharmacy.dao.exception.DAOException;
import by.online.pharmacy.entity.Product;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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



    @Override
    public void save(Product product) throws DAOException {

        try(WrappedConnection connection = new WrappedConnection(connectionPool.getConnection());
            PreparedStatement firstStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT);
            PreparedStatement secondStatement = connection.getPreparedStatement(SQL_SELECT_LAST_PRODUCT_ID);
            PreparedStatement thirdStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT_ITEMS);
            PreparedStatement fifthStatement = connection.getPreparedStatement(SQL_INSERT_PRODUCT_ITEMS)){

            firstStatement.setBoolean(1,product.isPrescription());
            firstStatement.setInt(2,product.getCount());
            firstStatement.setFloat(3,product.getPrice());
            firstStatement.setFloat(4,product.getDosage());
            File image = product.getImage();

            firstStatement.setBinaryStream(5,
                    new FileInputStream(image),
                    (int)image.length()
            );
            firstStatement.executeUpdate();

            ResultSet rs = secondStatement.executeQuery();

            if(rs != null){

                if(rs.next()) {
                    int lastId = rs.getInt("last");
                    thirdStatement.setInt(1,lastId);
                    thirdStatement.setInt(2,1);
                    thirdStatement.setString(3,product.getProductItem().getEngName());
                    thirdStatement.setString(4,product.getProductItem().getEngManufacture());
                    thirdStatement.setString(5,product.getProductItem().getEngDescription());
                    thirdStatement.setString(6,product.getProductItem().getEngCategory());
                    thirdStatement.executeUpdate();

                    fifthStatement.setInt(1,lastId);
                    fifthStatement.setInt(2,2);
                    fifthStatement.setString(3,product.getProductItem().getRusName());
                    fifthStatement.setString(4,product.getProductItem().getRusManufacture());
                    fifthStatement.setString(5,product.getProductItem().getRusDescription());
                    fifthStatement.setString(6,product.getProductItem().getRusCategory());
                    fifthStatement.executeUpdate();
                }

            }

        }catch (SQLException e){

            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
