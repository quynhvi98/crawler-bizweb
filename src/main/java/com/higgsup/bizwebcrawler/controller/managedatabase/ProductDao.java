package com.higgsup.bizwebcrawler.controller.managedatabase;

/**
 * Created by viquynh
 */
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ProductDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(ProductDao.class.getName());

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    public void setDataProducer(String name) {
        try {
            query = " SELECT producer_ID FROM Producer WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Producer( name )VALUES  ( ?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, name);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void setDataProduct(String product_ID, String name, String price, String stork, String content, String IMG, int productGroup_iD, int producer_ID) {
        try {
            query = "SELECT product_id FROM dbo.product WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, product_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.product( product_id ,name ,price ,stork ,content ,IMG ,product_group_id ,producer_id)VALUES  ( ? , ? ,? , ? ,? , ? , ? , ?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, product_ID);
                ps.setString(2, name);
                ps.setDouble(3, Double.parseDouble(price));
                ps.setInt(4, Integer.parseInt(stork));
                ps.setString(5, content);
                ps.setString(6, IMG);
                ps.setInt(7, productGroup_iD);
                ps.setInt(8, producer_ID);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public boolean hasProductID(String product_ID) {
        query = "SELECT product_id FROM dbo.product WHERE product_id=?";
        try {
            String ID = (String) template.queryForObject(
                    query, new Object[]{product_ID}, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            System.out.println("lôi");
        }
        return true;
    }

    public void setDataFromCategoryProductAndProduct(String productCate_ID, String product_ID) {
        try {
            query = "SELECT category_id FROM category_product WHERE product_cate_id =? AND product_id=?";//category_ID khóa chính bảng liên kết thể loại và sản phẩm
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, productCate_ID);
            ps.setString(2, product_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.category_product( product_cate_id, product_id )VALUES  ( ?, ?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, productCate_ID);
                ps.setString(2, product_ID);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {

            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            e.getStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public int getIDProducer(String name) {
        Integer ID = null;
        query = "SELECT producer_id FROM producer WHERE name=?";
        try {
            ID = (Integer) template.queryForObject(query, new Object[]{name}, Integer.class);
            System.out.println(ID);
        } catch (EmptyResultDataAccessException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return ID;
    }

   /* public List<Product> getDataProductFromProductID(String product_ID) {
        List<Product> products=this.template.query("SELECT * FROM product",new RowMapper<Product>(){
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product=new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10));
                return  product;
            }
        });
        return products;
    }
*/
    public void updateProduct(String product_id, String name, Double price, int stork, float weight, String content, String IMG, String description, int product_group_id, int producer_id) {
        try {
            query = "UPDATE dbo.product SET name =?,price=?,stork=?,weight=?,content=?,IMG=?,description=?,product_group_id=?,producer_id=? WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stork);
            ps.setDouble(4, weight);
            ps.setString(5, content);
            ps.setString(6, IMG);
            ps.setString(7, description);
            ps.setInt(8, product_group_id);
            ps.setInt(9, producer_id);
            ps.setString(10, product_id);
            ps.executeUpdate();
            System.out.println("update  oki");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

}
