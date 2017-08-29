package com.higgsup.bizwebcrawler.methods.managedatabase;/*
    By chicanem 29/08/2017
    */

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymenDao {
    private DataSource dataSource;
    private JdbcTemplate template;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private static final Logger logger = Logger.getLogger(PaymenDao.class.getName());
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



}
