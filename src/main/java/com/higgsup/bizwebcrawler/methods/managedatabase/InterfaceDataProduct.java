package com.higgsup.bizwebcrawler.methods.managedatabase;

/**
 * Created by viquynh
 */
import javax.sql.DataSource;

public interface InterfaceDataProduct {
    void setDataSource(DataSource dataSource);
    void setDataProductCategory(String productCate_ID, String name);

    int getIDProductCategory(String name);

    void setDataProductGroup(String name);

    int getIDProductGroup(String name);

    void setDataProducer(String name);

    void setDataProduct(String product_ID, String name, String price, String stork, String content, String IMG, int productGroup_iD, int producer_ID);

    boolean hasProductID(String product_ID);

    boolean hasCategoryProduct(String productCate_ID, String product_ID);

    void setDataFromCategoryProductAndProduct(String productCate_ID, String product_ID);

}
