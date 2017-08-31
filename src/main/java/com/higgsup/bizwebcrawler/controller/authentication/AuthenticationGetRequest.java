package com.higgsup.bizwebcrawler.controller.authentication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by viquynh on 26/07/2017.
 */

public class AuthenticationGetRequest {

    public String connectURLAndReturnHTML(String url, String cookie) throws IOException {

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
            return htmlChain.toString();
        } catch (Exception e) {
            htmlChain.append(" <title>Đăng nhập quản trị hệ thống</title>\n");
        }
        return htmlChain.toString();
    }
}
