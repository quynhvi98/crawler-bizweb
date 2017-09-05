package com.higgsup.bizwebcrawler.controller.authentication;
import com.higgsup.bizwebcrawler.controller.common.SendEmail;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by viquynh on 26/07/2017.
 *
 */
public class AuthenticationPostRequest {
    private static final Logger logger = Logger.getLogger("AuthenticationPostRequest");
    private String cookie;
    public String doRequest() throws IOException {
        try {
            String email="lethanh9398@gmail.com";
            String password="abc123456789";
            String url = "https://booktest2.bizwebvietnam.net/admin/authorization/login?Email="+email+"&Password="+password;
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            HttpResponse response = client.execute(post);
            Header[] allHeaders = response.getAllHeaders();
           if(allHeaders[11].getValue().equalsIgnoreCase("1; mode=block")){
               SendEmail.send("smtp.gmail.com","vi.quynh.31598@gmail.com", "higgsupcompany@gmail.com", "abc123456789", "Thông báo vấn đề tài khoản ","Xin chào bạn,<br> higgsup thông báo tài khoản truy cập bizweb <br> Email:"+email+"<br> Password:"+password+"<br> bạn cung cấp cho chúng tôi không chính xác ");
               return this.cookie="FalseAccount";
           }
            return this.cookie=allHeaders[11].getValue();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return this.cookie="Not Connect";
        }
    }
    public String getCookie() {
        return cookie;
    }
}
