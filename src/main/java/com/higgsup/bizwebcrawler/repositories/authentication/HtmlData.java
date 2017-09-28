package com.higgsup.bizwebcrawler.repositories.authentication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 * Created by viquynh on 26/07/2017.
 * Send request from client --> server --> receive html data from web
 */
public class HtmlData {
    private String htmlData;
    public void connectURLAndTakeHTML(String url, String cookie) {
        StringBuilder htmlChain = new StringBuilder();
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader(RequestHeader.RQ_HEADER, RequestHeader.RQ_HEADER_VALUE);
            request.addHeader(RequestHeader.RQ_ACCEPT, RequestHeader.RQ_ACCEPT_VALUE);
            request.addHeader(RequestHeader.RQ_ACCEPT_LANGUAGE,RequestHeader.RQ_ACCEPT_LANGUAGE_VALUE);
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
