
package com.higgsup.bizwebcrawler.repositories.managedatabase;

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
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_SERVER = "jdbc:sqlserver://localhost:1433;";
    private static final String DB_NAME = "databaseName=TestWebCrawler;";
    private static final String DB_USER = "Username=sa;";
    private static final String DB_PASSWORD = "Password =123456;";

    public Connection startConnect() throws ClassNotFoundException {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_SERVER + DB_NAME + DB_USER + DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return con;
    }
}
