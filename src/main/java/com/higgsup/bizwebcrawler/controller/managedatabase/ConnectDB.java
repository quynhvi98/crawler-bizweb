
package com.higgsup.bizwebcrawler.controller.managedatabase;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author viquynh
 */
public class ConnectDB {
    private static final Logger logger = Logger.getLogger(ConnectDB.class.getName());
    protected static Connection con;
    protected String query;
    protected PreparedStatement ps;
    protected ResultSet rs;
    public Connection startConnect() throws ClassNotFoundException {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            logger.log(Level.SEVERE, e.getMessage());
        }try{
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TestWebCrawler;Username=sa;Password =123456");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return con;
    }
}
