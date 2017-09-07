package com.higgsup.bizwebcrawler.controller.managedatabase;

import com.higgsup.bizwebcrawler.model.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.model.customer.Customer;
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
import com.higgsup.bizwebcrawler.model.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
public class QueryDataBase {
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger("QueryDataBase");

    //oki
    public void setDataProductCategory(String productCate_ID, String name) {
        try {
            query = " SELECT product_cate_id FROM product_category WHERE product_cate_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setInt(1, Integer.parseInt(productCate_ID));
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "  INSERT dbo.product_category ( product_cate_id, name ) VALUES  ( ?,?)";
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
    //oki
    public int getIDProductCategory(String name) {
        try {
            query = "SELECT product_cate_id FROM product_category WHERE name=?";
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
    //oki
    public void setDataProductGroup(String name) {
        try {
            query = "SELECT product_group_id FROM product_group WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.product_group ( name )VALUES ( ?)";
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
    //oki
    public int getIDProductGroup(String name) {
        try {
            query = "SELECT product_group_id FROM product_group WHERE name=?";
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
    //oki
    public void setDataProducer(String Name) {
        try {
            query = " SELECT producer_ID FROM producer WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, Name);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.producer( name )VALUES  ( ?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, Name);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }
    //oki
    public int getIDProducer(String Name) {
        try {
            query = "SELECT producer_id FROM producer WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, Name);
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
    ///oki
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
    ///oki
    public boolean hasProductID(String product_ID) {
        try {
            query = "SELECT product_id FROM dbo.product WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, product_ID);
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
    //oki
    public void setDataFromCategoryProductAndProduct(String productCate_ID, String product_ID) {//set category và product
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
            e.getStackTrace();
            // logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            e.getStackTrace();
            // logger.log(Level.SEVERE, e.getMessage());
        }
    }
    //oki
    public ArrayList<Product> getDataProductFromProductID(String product_ID) {

        try {
            query = "SELECT * FROM product WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, product_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                ArrayList<Product> dataProducerFromProductID = new ArrayList<Product>();
                dataProducerFromProductID.add(new Product(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
                return dataProducerFromProductID;
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    //oki
    public void updateProduct(String product_ID, String name, Double price, int stork, float weight_, String content, String IMG, String description_, int productGroup_iD, int producer_ID) {
        try {
            query = "UPDATE dbo.product SET name =?,price=?,stork=?,weight=?,content=?,IMG=?,description=?,product_group_id=?,producer_id=? WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stork);
            ps.setDouble(4, weight_);
            ps.setString(5, content);
            ps.setString(6, IMG);
            ps.setString(7, description_);
            ps.setInt(8, productGroup_iD);
            ps.setInt(9, producer_ID);
            ps.setString(10, product_ID);
            ps.executeUpdate();
            System.out.println("update  oki");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    //oki
    public void remoDataCategoryProductFromCateIdAndProductId(String productCate_ID, String product_ID) {
        try {
            query = "DELETE dbo.category_product WHERE product_cate_id=? AND product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, productCate_ID);
            ps.setString(2, product_ID);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    //oki
    public ArrayList<String> getListProductCateIdFormProductIdInCategoryProduct(String product_ID) {
        ArrayList<String> listProductCateID = new ArrayList<String>();
        try {
            query = "SELECT product_cate_id FROM dbo.category_product WHERE product_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, product_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProductCateID.add(rs.getString(1));
            }
            return listProductCateID;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return listProductCateID;
    }

    // oki
    public void setDataPaymenFromOrder(String content) {
        try {
            query = " SELECT payment_id FROM dbo.paymen WHERE content=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, content);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.paymen( content )VALUES  ( ?)";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, content);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    // oki
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
                //  ps.setString(2,dataFromOrder.getDate());
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

    // oki
    public int getIDPaymentFromContent(String content) {
        try {
            query = "SELECT payment_id FROM paymen WHERE content=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, content);
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
    // oki

    public void setDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        try {
                query = "SELECT order_product_id FROM product_order WHERE product_id =? AND order_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, dataFromOrderAndProduct.getProductID());
            ps.setString(2, dataFromOrderAndProduct.getOrderID());
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.product_order(quantity, product_ID, order_ID )VALUES  ( ?, ?,?)";
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
    // oki

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

    //oki

    public void setDataFromCustomer(String customer_ID, String fullName, String email, String totalBill) {
        try {
            query = " SELECT customer_id FROM dbo.customer WHERE customer_id =?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT customer (customer_id, full_name, email, total_bill) VALUES (?,?,?,?)";
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
    //oki
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
    //CustomerAddress
    //oki
    public void setDataCustomerAddress(CustomerAddress objectCustomerAddress) {


        try {
            query = "SELECT customer_id FROM dbo.customer_address WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddress.getId());
            rs = ps.executeQuery();
            if (rs.next() == false) {
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
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    //oki
    public ArrayList<String> getListCustomerDddIdFormCustomerId(String customer_ID) {
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

    // update del CustomerAddress
    //oki
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
    //oki
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
    //okie
    public ArrayList<CustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        ArrayList<CustomerAddress> loi = new ArrayList<CustomerAddress>();
        try {
            query = "SELECT *FROM dbo.customer_address WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
               // loi.add(new CustomerAddress(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), customer_ID, rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return loi;
    }

    // end update del CustomerAddress

    //update Customer
    //oki
    public Customer getDataCustomersFromCustomerID(String customer_ID) {

        try {
            query = "SELECT *FROM dbo.customer WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
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
    //oki
    public void updateDataCustomersFromObjectCustomer(Customer objectCustomers) {
        Customer objectCustomers1=objectCustomers;
        try {
            query = "SELECT *FROM dbo.customer WHERE customer_id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomers.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.Customer SET full_name=?,email=?,total_bill=? WHERE customer_id=?";
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

    //end update Customer

    //order staer get set



    //order end get set
}
