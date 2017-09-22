package com.higgsup.bizwebcrawler.controller.managedatabase;

import com.higgsup.bizwebcrawler.controller.common.CommonUtil;
import com.higgsup.bizwebcrawler.model.customer.CustomerAddress;
import com.higgsup.bizwebcrawler.model.customer.Customer;
import com.higgsup.bizwebcrawler.model.order.Order;
import com.higgsup.bizwebcrawler.model.order.OrderAddress;
import com.higgsup.bizwebcrawler.model.order.OrderProduct;
import com.higgsup.bizwebcrawler.model.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh
 */
public class QueryDataBase extends  ConnectDB {
    private String queryy;
    protected PreparedStatement pss;
    private ResultSet rss;
    private ConnectDB conn = new ConnectDB();
    private static final Logger logger = Logger.getLogger("QueryDataBase");

    //oki
    public void setDataProductCategory(String productCate_ID, String name) {
        try {
            queryy = " SELECT product_cate_id FROM product_category WHERE product_cate_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setInt(1, Integer.parseInt(productCate_ID));
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "  INSERT dbo.product_category ( product_cate_id, name ) VALUES  ( ?,?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT product_cate_id FROM product_category WHERE name=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, name);
            rss= pss.executeQuery();
            if (rss.next()) {
                return rss.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //oki
    public void setDataProductGroup(String name) {
        try {
            queryy= "SELECT product_group_id FROM product_group WHERE name=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, name);
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.product_group ( name )VALUES ( ?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT product_group_id FROM product_group WHERE name=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, name);
            rss = pss.executeQuery();
            if (rss.next()) {
                return rss.getInt(1);
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
            queryy = " SELECT producer_ID FROM producer WHERE name=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, Name);
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.producer( name )VALUES  ( ?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT producer_id FROM producer WHERE name=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, Name);
            rss = pss.executeQuery();
            if (rss.next()) {
                return rss.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    ///oki
    public void setDataProduct(Product product) {
        try {
            queryy = "SELECT product_id FROM dbo.product WHERE product_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, product.getProductID());
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.product( product_id ,name ,price ,stork,weight ,content ,IMG ,description,product_group_id ,producer_id)VALUES  ( ? , ?,?,? ,? , ? ,? , ? , ? , ?)";
                ps = con.prepareCall(query);
                ps.setString(1, product.getProductID());
                ps.setString(2, product.getName());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4, product.getStork());
                ps.setDouble(5, product.getWeight());
                ps.setString(6, product.getContent());
                ps.setString(7, product.getImg());
                ps.setString(8, product.getDescription());
                ps.setInt(9, product.getProductGroupId());
                ps.setInt(10, product.getProducerId());
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
            queryy = "SELECT product_id FROM dbo.product WHERE product_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, product_ID);
            rss = pss.executeQuery();
            if (rss.next()) return false;
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
            queryy = "SELECT category_id FROM category_product WHERE product_cate_id =? AND product_id=?";//category_ID khóa chính bảng liên kết thể loại và sản phẩm
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, productCate_ID);
            pss.setString(2, product_ID);
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.category_product( product_cate_id, product_id )VALUES  ( ?, ?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT * FROM product WHERE product_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, product_ID);
            rss = pss.executeQuery();
            if (rss.next()) {
                ArrayList<Product> dataProducerFromProductID = new ArrayList<Product>();
                dataProducerFromProductID.add(new Product(rss.getString(1), rss.getString(2), rss.getDouble(3), rss.getInt(4), rss.getDouble(5), rss.getString(6), rss.getString(7), rss.getString(8), rss.getInt(9), rss.getInt(10)));
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
    public void updateProduct(String product_ID, String name, Double price, int stork, double weight, String content, String IMG, String description, int productGroup_iD, int producer_ID) {
        try {
            query = "UPDATE dbo.product SET name =?,price=?,stork=?,weight=?,content=?,IMG=?,description=?,product_group_id=?,producer_id=? WHERE product_id=?";
            ps = con.prepareCall(query);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, stork);
            ps.setDouble(4, weight);
            ps.setString(5, content);
            ps.setString(6, IMG);
            ps.setString(7, description);
            ps.setInt(8, productGroup_iD);
            ps.setInt(9, producer_ID);
            ps.setString(10, product_ID);
            ps.executeUpdate();
            System.out.println("update  oki " + name + " ffff");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    //oki
    public void remoDataCategoryProductFromCateIdAndProductId(String productCate_ID, String product_ID) {
        try {
            query = "DELETE dbo.category_product WHERE product_cate_id=? AND product_id=?";
            ps = con.prepareCall(query);
            ps.setString(1, productCate_ID);
            ps.setString(2, product_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    //oki
    public ArrayList<String> getListProductCateIdFormProductIdInCategoryProduct(String product_ID) {
        ArrayList<String> listProductCateID = new ArrayList<String>();
        try {
            queryy = "SELECT product_cate_id FROM dbo.category_product WHERE product_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, product_ID);
            rss = pss.executeQuery();
            while (rss.next()) {
                listProductCateID.add(rss.getString(1));
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
            queryy = " SELECT payment_id FROM dbo.paymen WHERE content=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, content);
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.paymen( content )VALUES  ( ?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT order_id FROM dbo.order_product WHERE order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrder.getOrderID());
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.order_product ( order_id ,date ,status_paymen ,status_delivery ,total_bill ,total_weight ,fee_delivery ,customer_id ,payment_id )VALUES  ( ? , ?, ? , ? , ? , ? , ? ,  ? , ? )";
                ps = con.prepareCall(query);
                ps.setString(1, dataFromOrder.getOrderID());
                ps.setString(2, dataFromOrder.getDate());
                ps.setString(3, dataFromOrder.getStatusPaymen());
                ps.setString(4, dataFromOrder.getStatusDelivery());
                ps.setDouble(5, dataFromOrder.getTotalBill());
                ps.setDouble(6, dataFromOrder.getTotalWeight());
                ps.setDouble(7, dataFromOrder.getFeeDelivery());
                ps.setString(8, dataFromOrder.getCustomerID());
                ps.setInt(9, dataFromOrder.getPaymentID());

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
            queryy = "SELECT payment_id FROM paymen WHERE content=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, content);
            rss = pss.executeQuery();
            if (rss.next()) {
                return rss.getInt(1);
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
            queryy = "SELECT order_product_id FROM product_order WHERE product_id =? AND order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrderAndProduct.getProductID());
            pss.setString(2, dataFromOrderAndProduct.getOrderID());
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.product_order(quantity, product_ID, order_ID )VALUES  ( ?, ?,?)";
                ps = con.prepareCall(query);
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

    public void setDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
        try {
            queryy = " SELECT order_address_id FROM dbo.order_address WHERE order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrderAddress.getOrderID());
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT dbo.order_address(email, namecustomer ,phone ,order_address_content ,zipcode ,nation ,city ,district ,payment_address ,order_id)VALUES  ( ? , ? , ? , ? , ? ,? , ? ,? , ?,?)";
                ps = con.prepareCall(query);
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
            queryy = " SELECT customer_id FROM dbo.customer WHERE customer_id =?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, customer_ID);
            rss = pss.executeQuery();
            if (!(rss.next())) {
                query = "INSERT customer (customer_id, full_name, email, total_bill) VALUES (?,?,?,?)";
                ps = con.prepareCall(query);
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
            queryy = "SELECT customer_id FROM dbo.customer WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, customerID);
            rss = pss.executeQuery();
            if (rss.next()) return false;
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
            queryy = "SELECT customer_id FROM dbo.customer_address WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, objectCustomerAddress.getId());
            rss = pss.executeQuery();
            if (rss.next() == false) {
                query = "INSERT dbo.customer_address(customer_add_id, address_user ,name ,phone ,company ,zipe_code ,customer_id ,nation ,city ,district)VALUES  ( ?,? ,?  ,?  , ?  ,?  , ? , ?  , ? , ? )";
                ps = con.prepareCall(query);
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
            queryy = "SELECT customer_add_id FROM dbo.customer_address WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, customer_ID);
            rss = pss.executeQuery();
            while (rss.next()) {
                ListCustomerAddiD.add(rss.getString(1));
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
            queryy = "SELECT *FROM dbo.customer_address WHERE customer_add_id=? AND customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, objectCustomerAddress.getId());
            pss.setString(2, objectCustomerAddress.getCustomerID());
            rss = pss.executeQuery();
            if (rss.next()) {
                query = "UPDATE dbo.customer_address SET address_user=?,name=?,phone=?,company=?,zipe_code=?,nation=?,city=?,district=? WHERE customer_add_id=? AND customer_id=?";
                ps = con.prepareCall(query);
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
            ps = con.prepareCall(query);
            ps.setString(1, ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    //okie
    public ArrayList<CustomerAddress> getListAddressFormCustomerId(String customer_ID) {
        ArrayList<CustomerAddress> loi = new ArrayList<CustomerAddress>();
        try {
            queryy = "SELECT *FROM dbo.customer_address WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, customer_ID);
            rss = pss.executeQuery();
            while (rss.next()) {
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
            queryy = "SELECT *FROM dbo.customer WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, customer_ID);
            rss = pss.executeQuery();
            while (rss.next()) {
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
        Customer objectCustomers1 = objectCustomers;
        try {
            queryy = "SELECT *FROM dbo.customer WHERE customer_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, objectCustomers.getId());
            rss = pss.executeQuery();
            if (rss.next()) {
                query = "UPDATE dbo.Customer SET full_name=?,email=?,total_bill=? WHERE customer_id=?";
                ps = con.prepareCall(query);
                ps.setString(1, objectCustomers1.getFullName());
                ps.setString(2, objectCustomers1.getEmail());
                ps.setDouble(3, objectCustomers1.getTotalBill());
                ps.setString(4, objectCustomers1.getId());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    //end update Customer

    //order start update
    public ArrayList<Order> getListDataOrders() {
        ArrayList<Order> listOrder = new ArrayList<Order>();
        try {
            CommonUtil commonUtil = new CommonUtil();
            queryy = "SELECT * FROM dbo.order_product";
            pss = conn.startConnect().prepareCall(queryy);
            rss = pss.executeQuery();
            while (rs.next()) {
                listOrder.add(new Order(rss.getString(1), commonUtil.cutDateSQL(rss.getString(2)), rss.getString(3), rss.getString(4), rss.getDouble(5), rss.getDouble(6), rss.getDouble(7), rss.getString(8), rss.getInt(9)));
            }
            return listOrder;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return listOrder;
    }

    public void updateDataFromOrder(Order dataFromOrder) {
        try {
            queryy = "SELECT order_id FROM dbo.order_product WHERE order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrder.getOrderID());
            rss = pss.executeQuery();
            if ((rss.next())) {
                query = "UPDATE  order_product SET date=?, status_paymen=?,status_delivery=?,total_bill=?,total_weight=?,fee_delivery=?,customer_id=?,payment_id=? WHERE order_id=? AND customer_id=?";
                ps = con.prepareCall(query);

                ps.setString(1, dataFromOrder.getDate());
                ps.setString(2, dataFromOrder.getStatusPaymen());
                ps.setString(3, dataFromOrder.getStatusDelivery());
                ps.setDouble(4, dataFromOrder.getTotalBill());
                ps.setDouble(5, dataFromOrder.getTotalWeight());
                ps.setDouble(6, dataFromOrder.getFeeDelivery());
                ps.setString(7, dataFromOrder.getCustomerID());
                ps.setInt(8, dataFromOrder.getPaymentID());
                ps.setString(9, dataFromOrder.getOrderID());
                ps.setString(10, dataFromOrder.getCustomerID());
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public ArrayList<OrderProduct> getListDataOrderProduct(String id) {
        ArrayList<OrderProduct> listOrderProduct = new ArrayList<OrderProduct>();
        try {
            CommonUtil commonUtil = new CommonUtil();
            queryy = "SELECT  quantity,product_id,order_id FROM  product_order WHERE order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, id);
            rss = pss.executeQuery();
            while (rss.next()) {
                listOrderProduct.add(new OrderProduct(rss.getDouble(2), rss.getString(3), rss.getString(4)));
            }
            return listOrderProduct;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return listOrderProduct;
    }

    public boolean updateDataFromOrderAndProduct(OrderProduct dataFromOrderAndProduct) {
        try {
            queryy = "SELECT order_product_id FROM product_order WHERE product_id =? AND order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrderAndProduct.getProductID());
            pss.setString(2, dataFromOrderAndProduct.getOrderID());
            rss = pss.executeQuery();
            if ((rss.next())) {
                query = "UPDATE product_order SET quantity=? ,product_id=?,order_id=? WHERE product_id=? AND  order_id=?";
                ps = con.prepareCall(query);
                ps.setDouble(1, dataFromOrderAndProduct.getQuantity());
                ps.setString(2, dataFromOrderAndProduct.getProductID());
                ps.setString(3, dataFromOrderAndProduct.getOrderID());
                ps.setString(4, dataFromOrderAndProduct.getProductID());
                ps.setString(5, dataFromOrderAndProduct.getOrderID());
                ps.executeUpdate();
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return true;
    }

    public void updateDataFromOrderAddress(OrderAddress dataFromOrderAddress) {
        try {
            queryy = " SELECT order_address_id FROM dbo.order_address WHERE order_id=?";
            pss = conn.startConnect().prepareCall(queryy);
            pss.setString(1, dataFromOrderAddress.getOrderID());
            rss = pss.executeQuery();
            if ((rss.next())) {
                query = "UPDATE order_address SET email=?,namecustomer=?,phone=?,order_address_content=?,zipcode=?,nation=?,city=?,district=?,payment_address=? WHERE order_id=?";
                ps = con.prepareCall(query);
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

    public ArrayList<OrderAddress> getListDataOrderAddress() {
        ArrayList<OrderAddress> listOrderAddress = new ArrayList<OrderAddress>();
        try {
            queryy = "SELECT * FROM order_address";
            pss = conn.startConnect().prepareCall(queryy);
            rss = ps.executeQuery();
            while (rss.next()) {
                listOrderAddress.add(new OrderAddress(rss.getInt(1), rss.getString(2), rss.getString(3), rss.getString(4), rss.getString(5), rss.getString(6), rss.getString(7), rss.getString(8), rss.getString(9), rss.getString(10), rss.getString(11)));
            }
            return listOrderAddress;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listOrderAddress;
    }
    //order end get set
}
