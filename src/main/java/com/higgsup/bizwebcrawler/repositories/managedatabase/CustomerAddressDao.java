package com.higgsup.bizwebcrawler.repositories.managedatabase;

import com.higgsup.bizwebcrawler.entites.customer.CustomerAddress;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
@Repository
public class CustomerAddressDao {
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

    public void setDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        try {
            query = "SELECT customer_id FROM dbo.Customer_Address WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddress.getId());
            rs = ps.executeQuery();
            if (!(rs.next()) == false) {
                query = "INSERT dbo.customer_address(customer_add_id, address_user ,name ,phone ,company ,zipe_code ,customer_id ,nation ,city ,district)VALUES  ( ?,? ,?  ,?  , ?  ,?  , ? , ?  , ? , ? )";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddress.getId());
                ps.setString(2, objectCustomerAddress.getAddressUser());
                ps.setString(3, objectCustomerAddress.getFullName());
                ps.setString(4, objectCustomerAddress.getPhoneNumber());
                ps.setString(5, objectCustomerAddress.getCompany());
                ps.setString(6, objectCustomerAddress.getZipeCode());
                ps.setString(7, objectCustomerAddress.getCustomerID());
                ps.setString(8, objectCustomerAddress.getNation());
                ps.setString(9, objectCustomerAddress.getCity());
                ps.setString(10, objectCustomerAddress.getDistrict());
                ps.executeUpdate();
            }


        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public ArrayList<String> getListCustomerAddIDFormCustomerID(String customer_ID) {
        ArrayList<String> ListCustomerAddiD = new ArrayList<String>();
        try {
            query = "SELECT customer_add_id FROM dbo.customer_address WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListCustomerAddiD.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return ListCustomerAddiD;
    }

    public void updateDataCustomerAddress(CustomerAddress objectCustomerAddress) {
        try {
            query = "SELECT *FROM dbo.customer_address WHERE customer_add_id=? AND customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddress.getId());
            ps.setString(2, objectCustomerAddress.getCustomerID());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.customer_address SET address_user=?,name=?,phone=?,company=?,zipe_code=?,nation=?,city=?,district=? WHERE customer_add_id=? AND customer_id=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddress.getAddressUser());
                ps.setString(2, objectCustomerAddress.getFullName());
                ps.setString(3, objectCustomerAddress.getPhoneNumber());
                ps.setString(4, objectCustomerAddress.getCompany());
                ps.setString(5, objectCustomerAddress.getZipeCode());
                ps.setString(6, objectCustomerAddress.getNation());
                ps.setString(7, objectCustomerAddress.getCity());
                ps.setString(8, objectCustomerAddress.getDistrict());
                ps.setString(9, objectCustomerAddress.getId());
                ps.setString(10, objectCustomerAddress.getCustomerID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void deleteDataCustomerAddress(String ID) {
        try {
            query = "DELETE dbo.customer_address WHERE customer_add_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, ID);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public ArrayList<CustomerAddress> getListAddressFormCustomerId(String customer_id) {
        ArrayList<CustomerAddress> listCustomerAddress = new ArrayList<CustomerAddress>();
        try {
            query = "SELECT *FROM dbo.customer_address WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //listCustomerAddress.add(new CustomerAddress(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), customer_id, rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return listCustomerAddress;
    }
}
