package com.higgsup.bizwebcrawler.methods.managedatabase;

/**
 * Created by viquynh
 */

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

public class QueryDataProduct implements InterfaceDataProduct {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String SQL = "";
    Connection conn = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    public void setDataProductCategory(String productCate_ID, String name) {

    }

    public int getIDProductCategory(String name) {
        return 0;
    }

    public void setDataProductGroup(String name) {

    }

    public int getIDProductGroup(String name) {
        return 0;
    }

    public void setDataProducer(String name) {

    }

    public void setDataProduct(String product_ID, String name, String price, String stork, String content, String IMG, int productGroup_iD, int producer_ID) {

    }

    public boolean hasProductID(String product_ID) {
        SQL = "SELECT product_ID FROM dbo.Product WHERE product_ID=?";
        try {
            String ID = (String) template.queryForObject(
                    SQL, new Object[]{product_ID}, String.class);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }catch (Exception e){
            System.out.println("lôi");
        }

        return true;

    }

    public boolean hasCategoryProduct(String productCate_ID, String product_ID) {
        return false;
    }

    public void setDataFromCategoryProductAndProduct(String productCate_ID, String product_ID) {

    }
}
