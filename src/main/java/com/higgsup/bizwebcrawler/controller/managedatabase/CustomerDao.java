package com.higgsup.bizwebcrawler.controller.managedatabase;

import com.higgsup.bizwebcrawler.model.objectcustomer.ObjectCustomers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
@Repository
public class CustomerDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    public void setDataFromCustomer(String customer_ID, String fullName, String email, String totalBill) {
        try {
            query = " SELECT customer_ID FROM dbo.Customer WHERE customer_ID =?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT Customer (customer_ID, fullName, email, totalBill) VALUES (?,?,?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, customer_ID);
                ps.setString(2, fullName);
                ps.setString(3, email);
                ps.setDouble(4, Double.parseDouble(totalBill));
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public boolean hasCustomerID(String customerID) {
        try {
            query = "SELECT customer_ID FROM dbo.Customer WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customerID);
            rs = ps.executeQuery();
            if (rs.next()) return false;
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }
    public ObjectCustomers getDataCustomersFromCustomerID(String customer_ID) {

        try {
            query = "SELECT *FROM dbo.Customer WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                ObjectCustomers objectCustomers = new ObjectCustomers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                return objectCustomers;
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return null;
    }
    public void updateDataCustomersFromObjectCustomer(ObjectCustomers objectCustomers) {
        ObjectCustomers objectCustomers1=objectCustomers;
        try {
            query = "SELECT *FROM dbo.Customer WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomers.getCustomerID());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.Customer SET fullName=?,email=?,totalBill=? WHERE customer_ID=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1,objectCustomers1.getFullName());
                ps.setString(2,objectCustomers1.getEmail());
                ps.setDouble(3,objectCustomers1.getTotalBill());
                ps.setString(4,objectCustomers1.getCustomerID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
