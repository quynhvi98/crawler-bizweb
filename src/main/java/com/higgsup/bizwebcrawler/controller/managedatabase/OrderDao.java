package com.higgsup.bizwebcrawler.controller.managedatabase;/*
    By chicanem 29/08/2017
    */

import com.higgsup.bizwebcrawler.model.objectorder.ObjectOrder;
import com.higgsup.bizwebcrawler.model.objectorder.ObjectOrderAddress;
import com.higgsup.bizwebcrawler.model.objectorder.ObjectOrderProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Repository
public class OrderDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

    public void setDataFromOrder(ObjectOrder dataFromOrder) {
        try {
            query = "SELECT order_ID FROM dbo.Order_ WHERE order_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrder.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_ ( order_ID ,date ,status_Paymen ,status_Delivery ,total_Bill ,total_Weight ,fee_Delivery ,customer_ID ,payment_ID )VALUES  ( ? , GETDATE(), ? , ? , ? , ? , ? ,  ? , ? )";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1,dataFromOrder.getOrderID());
                ps.setString(2,dataFromOrder.getStatusPaymen());
                ps.setString(3,dataFromOrder.getStatusDelivery());
                ps.setDouble(4,dataFromOrder.getTotalBill());
                ps.setDouble(5,dataFromOrder.getTotalWeight());
                ps.setDouble(6,dataFromOrder.getFeeDelivery());
                ps.setString(7,dataFromOrder.getCustomerID());
                ps.setInt(8,dataFromOrder.getPaymentID());

                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    public void setDataFromOrderAndProduct(ObjectOrderProduct dataFromOrderAndProduct) {
        try {
            query = "SELECT order_product_ID FROM Order_Product WHERE product_ID =? AND order_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrderAndProduct.getProductID());
            ps.setString(2, dataFromOrderAndProduct.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_Product(quantity, product_ID, order_ID )VALUES  ( ?, ?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setDouble(1, dataFromOrderAndProduct.getQuantity());
                ps.setString(2, dataFromOrderAndProduct.getProductID());
                ps.setString(3, dataFromOrderAndProduct.getOrderID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void setDataFromOrderAddress(ObjectOrderAddress dataFromOrderAddress){
        try {
            query = " SELECT *FROM dbo.Order_Address WHERE order_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrderAddress.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_Address(email, NameCustomer ,Phone ,Order_Address ,ZipCode ,nation ,city ,district ,PaymentAddress ,order_ID)VALUES  ( ? , ? , ? , ? , ? ,? , ? ,? , ?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, dataFromOrderAddress.getEmail());
                ps.setString(2, dataFromOrderAddress.getNameCustomer());
                ps.setString(3, dataFromOrderAddress.getPhone());
                ps.setString(4, dataFromOrderAddress.getOrderAddress());
                ps.setString(5, dataFromOrderAddress.getZipCode());
                ps.setString(6, dataFromOrderAddress.getNation());
                ps.setString(7, dataFromOrderAddress.getCity());
                ps.setString(8, dataFromOrderAddress.getDistrict());
                ps.setString(9, dataFromOrderAddress.getPaymentAddress());
                ps.setString(10, dataFromOrderAddress.getOrderID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
