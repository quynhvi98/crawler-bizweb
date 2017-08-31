package com.higgsup.bizwebcrawler.controller.managedatabase;

import com.higgsup.bizwebcrawler.model.objectcustomer.ObjectCustomerAddress;
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

    public void setDataCustomerAddress(ObjectCustomerAddress objectCustomerAddress) {
        try {
            query = "SELECT *FROM dbo.Customer_Address WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddress.getCustomerAddID());
            rs = ps.executeQuery();
            if (!(rs.next()) == false) {
                query = "INSERT dbo.Customer_Address(customerAdd_iD, addressUser ,name ,phone ,company ,zipeCode ,customer_ID ,nation ,city ,district)VALUES  ( ?,? ,?  ,?  , ?  ,?  , ? , ?  , ? , ? )";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddress.getCustomerAddID());
                ps.setString(2, objectCustomerAddress.getAddressUser());
                ps.setString(3, objectCustomerAddress.getName());
                ps.setString(4, objectCustomerAddress.getPhone());
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
    public ArrayList<String> getListCustomerDddIdFormCustomerId(String customer_ID) {
        ArrayList<String> ListCustomerAddiD = new ArrayList<String>();
        try {
            query = "SELECT customerAdd_iD FROM dbo.Customer_Address WHERE customer_ID=?";
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
    public void updateDataCustomerAddress(ObjectCustomerAddress objectCustomerAddress) {
        try {
            query = "SELECT *FROM dbo.Customer_Address WHERE customerAdd_iD=? AND customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddress.getCustomerAddID());
            ps.setString(2, objectCustomerAddress.getCustomerID());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.Customer_Address SET addressUser=?,name=?,phone=?,company=?,zipeCode=?,nation=?,city=?,district=? WHERE customerAdd_iD=? AND customer_ID=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddress.getAddressUser());
                ps.setString(2, objectCustomerAddress.getName());
                ps.setString(3, objectCustomerAddress.getPhone());
                ps.setString(4, objectCustomerAddress.getCompany());
                ps.setString(5, objectCustomerAddress.getZipeCode());
                ps.setString(6, objectCustomerAddress.getNation());
                ps.setString(7, objectCustomerAddress.getCity());
                ps.setString(8, objectCustomerAddress.getDistrict());
                ps.setString(9, objectCustomerAddress.getCustomerAddID());
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
            query = "DELETE dbo.Customer_Address WHERE customerAdd_iD=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, ID);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    public ArrayList<ObjectCustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        ArrayList<ObjectCustomerAddress> loi = new ArrayList<ObjectCustomerAddress>();

        try {
            query = "SELECT *FROM dbo.Customer_Address WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                loi.add(new ObjectCustomerAddress(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), customer_ID, rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return loi;
    }
}
