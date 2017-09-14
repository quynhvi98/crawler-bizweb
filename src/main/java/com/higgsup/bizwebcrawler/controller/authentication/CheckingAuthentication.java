package com.higgsup.bizwebcrawler.controller.authentication;

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

    public String doRequestTakeCookie() throws IOException {
        try {
            takeAccountAdminFromDatabase();
            String url = "https://bookweb1.bizwebvietnam.net/admin/authorization/login?Email=" + email + "&Password=" + password;
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            HttpResponse response = client.execute(post);
            Header[] allHeaders = response.getAllHeaders();
            if (allHeaders[11].getValue().equalsIgnoreCase("1; mode=block")) {
                SendEmail.send("smtp.gmail.com", "vi.quynh.31598@gmail.com", "higgsupcompany@gmail.com", "abc123456789", "Thông báo vấn đề tài khoản ", "Xin chào bạn,<br> higgsup thông báo tài khoản truy cập bizweb <br> Email:" + email + "<br> Password:" + password + "<br> bạn cung cấp cho chúng tôi không chính xác ");
                throw new Error("FalseAccount");
            }
            return this.cookie = allHeaders[11].getValue();

        } catch (UnknownHostException e) {
            throw new Error("Not Connect Internet");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new Error("Not Connect Internet");
        }
    }

    public String getCookie() {
        System.out.println(this.cookie);
        return this.cookie;
    }

    public void takeAccountAdminFromDatabase() {
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
