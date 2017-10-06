package com.higgsup.bizwebcrawler.services.authentication;

import com.higgsup.bizwebcrawler.entites.customer.Administrator;
import com.higgsup.bizwebcrawler.repositories.AdministratorRepo;
import com.higgsup.bizwebcrawler.utils.FileTemplate;
import com.higgsup.bizwebcrawler.utils.RequestHeader;
import com.higgsup.bizwebcrawler.utils.SendEmail;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by viquynh on 26/07/2017.
 */
@Component
public class CheckingAuthentication {
    private static final Logger logger = Logger.getLogger(CheckingAuthentication.class.getName());
    private static String cookie;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    private static String email;
    private static String password;
    @Autowired
    AdministratorRepo administratorRepo;
    private Administrator administrator;
    private static final String url = RequestHeader.urlWebsite+"/authorization/login?Email=%s&Password=%s";
    final String smtpServer = "smtp.gmail.com";
    final String username = "vi.quynh.31598@gmail.com";
    final String psw = "abc123456789";
    final String gmailCompany ="higgsupcompany@gmail.com";
    final String subjectEmail ="Thông báo vấn đề tài khoản";

    public void doRequestTakeCookie() throws IOException {
        try {
            takeAccountAdminFromDatabase();
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(String.format(url, administrator.getEmail(),administrator.getPassWord()));
            post.setHeader(RequestHeader.RQ_HEADER, RequestHeader.RQ_HEADER_VALUE);
            HttpResponse response = client.execute(post);
            Header[] allHeaders = response.getAllHeaders();
            if (allHeaders[11].getValue().equalsIgnoreCase("1; mode=block")) {
                SendEmail.send(smtpServer, username, gmailCompany, psw, subjectEmail, FileTemplate.mailContent(email, password).toString());
            }
            setCookie(allHeaders[11].getValue());
        } catch (UnknownHostException e) {
            throw new Error("Not Connect Internet");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());

        }
    }

    public static void setCookie(String cookie) {
        CheckingAuthentication.cookie = cookie;
    }

    public String getCookie() {
        return this.cookie;
    }
    public void takeAccountAdminFromDatabase(){
        administrator=administratorRepo.getAdministrator("0");
    }

    public CheckingAuthentication() {

    }

}
