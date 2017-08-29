package com.higgsup.bizwebcrawler.methods.managedatabase;/*
    By chicanem 29/08/2017
    */

import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrder;
import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrderAddress;
import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrderProduct;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            ps.setString(1, dataFromOrder.getOrder_ID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_ ( order_ID ,date ,status_Paymen ,status_Delivery ,total_Bill ,total_Weight ,fee_Delivery ,customer_ID ,payment_ID )VALUES  ( ? , GETDATE(), ? , ? , ? , ? , ? ,  ? , ? )";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1,dataFromOrder.getOrder_ID());
                //  ps.setString(2,dataFromOrder.getDate());
                ps.setString(2,dataFromOrder.getStatus_Paymen());
                ps.setString(3,dataFromOrder.getStatus_Delivery());
                ps.setDouble(4,dataFromOrder.getTotal_Bill());
                ps.setDouble(5,dataFromOrder.getTotal_Weight());
                ps.setDouble(6,dataFromOrder.getFee_Delivery());
                ps.setString(7,dataFromOrder.getCustomer_ID());
                ps.setInt(8,dataFromOrder.getPayment_ID());

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
            ps.setString(1, dataFromOrderAndProduct.getProduct_ID());
            ps.setString(2, dataFromOrderAndProduct.getOrder_ID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_Product(quantity, product_ID, order_ID )VALUES  ( ?, ?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setDouble(1, dataFromOrderAndProduct.getQuantity());
                ps.setString(2, dataFromOrderAndProduct.getProduct_ID());
                ps.setString(3, dataFromOrderAndProduct.getOrder_ID());
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
            ps.setString(1, dataFromOrderAddress.getOrder_ID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Order_Address(email, NameCustomer ,Phone ,Order_Address ,ZipCode ,nation ,city ,district ,PaymentAddress ,order_ID)VALUES  ( ? , ? , ? , ? , ? ,? , ? ,? , ?,?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, dataFromOrderAddress.getEmail());
                ps.setString(2, dataFromOrderAddress.getNameCustomer());
                ps.setString(3, dataFromOrderAddress.getPhone());
                ps.setString(4, dataFromOrderAddress.getOrder_Address());
                ps.setString(5, dataFromOrderAddress.getZipCode());
                ps.setString(6, dataFromOrderAddress.getNation());
                ps.setString(7, dataFromOrderAddress.getCity());
                ps.setString(8, dataFromOrderAddress.getDistrict());
                ps.setString(9, dataFromOrderAddress.getPaymentAddress());
                ps.setString(10, dataFromOrderAddress.getOrder_ID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
