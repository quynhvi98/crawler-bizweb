package com.higgsup.bizwebcrawler.methods.managedatabase;

import com.higgsup.bizwebcrawler.object.objectcustomer.ObjectCustomerAddress;
import com.higgsup.bizwebcrawler.object.objectcustomer.ObjectCustomers;
import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrder;
import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrderAddress;
import com.higgsup.bizwebcrawler.object.objectorder.ObjectOrderProduct;
import com.higgsup.bizwebcrawler.object.objectproduct.Product;

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
            query = " SELECT productCate_ID FROM Product_Category WHERE productCate_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setInt(1, Integer.parseInt(productCate_ID));
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "  INSERT dbo.Product_Category ( productCate_ID, name ) VALUES  ( ?,?)";
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
            query = "SELECT productCate_ID FROM Product_Category WHERE name=?";
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
    //oki
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
    //oki
    public void setDataProducer(String Name) {
        try {
            query = " SELECT producer_ID FROM Producer WHERE name=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, Name);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Producer( name )VALUES  ( ?)";
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
            query = "SELECT producer_ID FROM Producer WHERE name=?";
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
            query = "SELECT product_ID FROM dbo.Product WHERE product_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, product_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Product( product_ID ,name ,price ,stork ,content ,IMG ,productGroup_iD ,producer_ID)VALUES  ( ? , ? ,? , ? ,? , ? , ? , ?)";
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
            query = "SELECT product_ID FROM dbo.Product WHERE product_ID=?";
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
            query = "SELECT category_ID FROM Category_Product WHERE productCate_ID =? AND product_ID=?";//category_ID khóa chính bảng liên kết thể loại và sản phẩm
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, productCate_ID);
            ps.setString(2, product_ID);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Category_Product( productCate_ID, product_ID )VALUES  ( ?, ?)";
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
            query = "SELECT * FROM Product WHERE product_ID=?";
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
            query = "UPDATE dbo.Product SET name =?,price=?,stork=?,weight_=?,content=?,IMG=?,description_=?,productGroup_iD=?,producer_ID=? WHERE product_ID=?";
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
            query = "DELETE dbo.Category_Product WHERE productCate_ID=? AND product_ID=?";
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
            query = "SELECT productCate_ID FROM dbo.Category_Product WHERE product_ID=?";
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
            query = " SELECT payment_ID FROM dbo.Paymen WHERE content=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, content);
            rs = ps.executeQuery();
            if (!(rs.next())) {
                query = "INSERT dbo.Paymen( content )VALUES  ( ?)";
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

    // oki
    public int getIDPaymentFromContent(String content) {
        try {
            query = "SELECT payment_ID FROM Paymen WHERE content=?";
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
    // oki

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

    ///////////// chưa làm.

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

    public boolean hasCustomerID(String customer_ID) {
        try {
            query = "SELECT customer_ID FROM dbo.Customer WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
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

    public void setDataCustomerAddress(ObjectCustomerAddress objectCustomerAddress) {

        ObjectCustomerAddress objectCustomerAddres1 = new ObjectCustomerAddress();
        objectCustomerAddres1 = objectCustomerAddress;
        try {
            query = "SELECT *FROM dbo.Customer_Address WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddres1.getCustomerAdd_iD());
            rs = ps.executeQuery();
            if (rs.next() == false) {
                query = "INSERT dbo.Customer_Address(customerAdd_iD, addressUser ,name ,phone ,company ,zipeCode ,customer_ID ,nation ,city ,district)VALUES  ( ?,? ,?  ,?  , ?  ,?  , ? , ?  , ? , ? )";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddres1.getCustomerAdd_iD());
                ps.setString(2, objectCustomerAddres1.getAddressUser());
                ps.setString(3, objectCustomerAddres1.getName());
                ps.setString(4, objectCustomerAddres1.getPhone());
                ps.setString(5, objectCustomerAddres1.getCompany());
                ps.setString(6, objectCustomerAddres1.getZipeCode());
                ps.setString(7, objectCustomerAddres1.getCustomer_ID());
                ps.setString(8, objectCustomerAddres1.getNation());
                ps.setString(9, objectCustomerAddres1.getCity());
                ps.setString(10, objectCustomerAddres1.getDistrict());
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

    // update del CustomerAddress

    public void updateDataCustomerAddress(ObjectCustomerAddress objectCustomerAddress) {

        ObjectCustomerAddress objectCustomerAddres1 = new ObjectCustomerAddress();
        objectCustomerAddres1 = objectCustomerAddress;
        try {
            query = "SELECT *FROM dbo.Customer_Address WHERE customerAdd_iD=? AND customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, objectCustomerAddres1.getCustomerAdd_iD());
            ps.setString(2, objectCustomerAddres1.getCustomer_ID());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.Customer_Address SET addressUser=?,name=?,phone=?,company=?,zipeCode=?,nation=?,city=?,district=? WHERE customerAdd_iD=? AND customer_ID=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1, objectCustomerAddres1.getAddressUser());
                ps.setString(2, objectCustomerAddres1.getName());
                ps.setString(3, objectCustomerAddres1.getPhone());
                ps.setString(4, objectCustomerAddres1.getCompany());
                ps.setString(5, objectCustomerAddres1.getZipeCode());
                ps.setString(6, objectCustomerAddres1.getNation());
                ps.setString(7, objectCustomerAddres1.getCity());
                ps.setString(8, objectCustomerAddres1.getDistrict());
                ps.setString(9, objectCustomerAddres1.getCustomerAdd_iD());
                ps.setString(10, objectCustomerAddres1.getCustomer_ID());
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

    // end update del CustomerAddress

    //update Customer
    public ObjectCustomers getDataCustomersFromCustomerID(String customer_ID) {

        try {
            query = "SELECT *FROM dbo.Customer WHERE customer_ID=?";
            ps = con.startConnect().prepareCall(query);
            ps.setString(1, customer_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                ObjectCustomers objectCustomers = new ObjectCustomers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
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
            ps.setString(1, objectCustomers.getCustomer_ID());
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "UPDATE dbo.Customer SET fullName=?,email=?,totalBill=? WHERE customer_ID=?";
                ps = con.startConnect().prepareCall(query);
                ps.setString(1,objectCustomers1.getFullName());
                ps.setString(2,objectCustomers1.getEmail());
                ps.setDouble(3,objectCustomers1.getTotalBill());
                ps.setString(4,objectCustomers1.getCustomer_ID());
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
