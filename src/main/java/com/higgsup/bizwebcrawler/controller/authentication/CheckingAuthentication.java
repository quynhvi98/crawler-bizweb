package com.higgsup.bizwebcrawler.controller.authentication;

import com.higgsup.bizwebcrawler.controller.common.FileTemplate;
import com.higgsup.bizwebcrawler.controller.common.SendEmail;
import com.higgsup.bizwebcrawler.controller.managedatabase.ConnectDB;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh on 26/07/2017.
 */
public class CheckingAuthentication {
    private static final Logger logger = Logger.getLogger(CheckingAuthentication.class.getName());
    private static String cookie;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConnectDB con = new ConnectDB();
    private String email;
    private String password;
    private static final String url = RequestHeader.urlWebsite+"/authorization/login?Email=%s&Password=%s";
    final String smtpServer = "smtp.gmail.com";
    final String username = "vi.quynh.31598@gmail.com";
    final String psw = "abc123456789";
    final String gmailCompany ="higgsupcompany@gmail.com";
    final String subjectEmail ="Thông báo vấn đề tài khoản";

    public void doRequestTakeCookie() throws IOException {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(String.format(url, email,password));
            post.setHeader(RequestHeader.RQ_HEADER, RequestHeader.RQ_HEADER_VALUE);
            HttpResponse response = client.execute(post);
            Header[] allHeaders = response.getAllHeaders();
            if (allHeaders[11].getValue().equalsIgnoreCase("1; mode=block")) {
                SendEmail.send(smtpServer, username, gmailCompany, psw, subjectEmail, FileTemplate.mailContent(email, password).toString());
                throw new Error("FalseAccount");
            }
            setCookie(allHeaders[11].getValue());

        } catch (UnknownHostException e) {
            throw new Error("Not Connect Internet");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new Error("Not Connect Internet");
        }
    }

    public static void setCookie(String cookie) {
        CheckingAuthentication.cookie = cookie;
    }

    public String getCookie() {
        return this.cookie;
    }

    public CheckingAuthentication() {
        takeAccountAdminFromDatabase();
    }

    private void takeAccountAdminFromDatabase() {
        try {
            query = "SELECT email,pass_word FROM administrator WHERE id=?";
            ps = con.startConnect().prepareCall(query);
            ps.setInt(1, 0);
            rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString(1);
                password = rs.getString(2);
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
