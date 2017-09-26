package com.higgsup.bizwebcrawler.controller.managedatabase;

import com.higgsup.bizwebcrawler.model.customer.Customer;
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

    public void setDataFromCustomer(String customer_id, String full_name, String email, String total_bill) {
        try {
            query = " SELECT customer_id FROM dbo.customer WHERE customer_id =?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_id);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT Customer (customer_id, full_name, email, total_bill) VALUES (?,?,?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, customer_id);
                ps.setString(2, full_name);
                ps.setString(3, email);
                ps.setDouble(4, Double.parseDouble(total_bill));
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
            query = "SELECT customer_id FROM dbo.customer WHERE customer_id=?";
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

    public Customer getDataCustomersFromCustomerID(String customer_id) {

        try {
            query = "SELECT * FROM dbo.customer WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Customer objectCustomers = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                //return objectCustomers;
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return null;
    }

    public void updateDataCustomersFromObjectCustomer(Customer objectCustomers) {
        Customer objectCustomers1=objectCustomers;
        try {
            query = "SELECT *FROM dbo.customer WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomers.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.customer SET full_name=?,email=?,total_bill=? WHERE customer_id=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1,objectCustomers1.getFullName());
                ps.setString(2,objectCustomers1.getEmail());
                ps.setDouble(3,objectCustomers1.getTotalBill());
                ps.setString(4,objectCustomers1.getId());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
