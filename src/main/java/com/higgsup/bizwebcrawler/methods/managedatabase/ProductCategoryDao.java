package com.higgsup.bizwebcrawler.methods.managedatabase;/*
    By chicanem 29/08/2017
    */

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCategoryDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query = "";

    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(ProductCategoryDao.class.getName());

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    public void setDataProductCategory(String productCate_ID, String name) {
        try {
            query = " SELECT productCate_ID FROM Product_Category WHERE productCate_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setInt(1, Integer.parseInt(productCate_ID));
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "  INSERT dbo.Product_Category ( productCate_ID, name ) VALUES  ( ?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setInt(1, Integer.parseInt(productCate_ID));
                ps.setString(2, name);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public int getIDProductCategory(String name) {
        try {
            query = "SELECT productCate_ID FROM Product_Category WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }
    public boolean hasCategoryProduct(String productCate_ID, String product_ID) {
        query = "SELECT *FROM dbo.Category_Product WHERE productCate_ID =? AND product_ID =?";
        try {
            String ID = (String) template.queryForObject(
                    query, new Object[]{productCate_ID,product_ID}, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
           e.printStackTrace();
        }

        return true;
    }
}
