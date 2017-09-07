package com.higgsup.bizwebcrawler.controller.managedatabase;
/*
    By chicanem 29/08/2017
 */
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    public void setDataFromOrder(Order dataFromOrder) {
        try {
            query = "SELECT order_id FROM dbo.order_product WHERE order_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrder.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.order_product ( order_id ,date ,status_paymen ,status_delivery ,total_bill ,total_weight ,fee_delivery ,customer_id ,payment_id )VALUES  ( ? , GETDATE(), ? , ? , ? , ? , ? ,  ? , ? )";
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

    public void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        try {
            query = "SELECT order_product_id FROM product_order WHERE product_id =? AND order_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrderAndProduct.getProductID());
            ps.setString(2, dataFromOrderAndProduct.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT product_order(quantity, product_id, order_id )VALUES  ( ?, ?,?)";
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

    public void setDataFromOrderAddress(OrderAddress dataFromOrderAddress){
        try {
            query = " SELECT order_address_id FROM dbo.order_address WHERE order_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrderAddress.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.order_address(email, namecustomer ,phone ,order_address_content ,zipcode ,nation ,city ,district ,payment_address ,order_id)VALUES  ( ? , ? , ? , ? , ? ,? , ? ,? , ?,?)";
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
