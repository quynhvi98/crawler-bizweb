package com.higgsup.bizwebcrawler.controller.managedatabase;/*
    By chicanem 29/08/2017
    */

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Repository
public class ProductGroupDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(ProductGroupDao.class.getName());

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    public void setDataProductGroup(String name) {
        try {
            query = "SELECT productGroup_iD FROM Product_Group WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Product_Group ( name )VALUES ( ?)";
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

    public int getIDProductGroup(String name) {

        try {
            query = "SELECT productGroup_iD FROM Product_Group WHERE name=?";
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
}
