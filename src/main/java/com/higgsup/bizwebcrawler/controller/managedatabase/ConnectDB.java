
package com.higgsup.bizwebcrawler.controller.managedatabase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author viquynh
 */

public class ConnectDB {
    private static final Logger logger = Logger.getLogger("ConnectDB");
    private static Connection conn;

    public Connection startConnect() throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TestWebCrawler;Username=sa;Password =123456");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return conn;
    }

}
