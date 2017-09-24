package com.higgsup.bizwebcrawler.controller.authentication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 * Created by viquynh on 26/07/2017.
 * Send request from client --> server --> receive html data from web
 */
public class HtmlData {
    private String htmlData;
    public void connectURLAndTakeHTML(String url, String cookie){
        StringBuilder htmlChain = new StringBuilder();
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            request.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            request.addHeader("accept-language", "en-US,en;q=0.8,vi;q=0.6,en-GB;q=0.4");
            request.addHeader("cookie", cookie);
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                htmlChain.append(inputLine);
            }
            in.close();
            this.htmlData = htmlChain.toString();
        }catch (UnknownHostException e) {
            throw new Error("Not Connect Internet");
        }catch (Exception e) {
            htmlChain.append(" <title>Đăng nhập quản trị hệ thống</title>\n");
        }
        this.htmlData = htmlChain.toString();
    }
    public String getHtmlData() {
        return htmlData;
    }
}
