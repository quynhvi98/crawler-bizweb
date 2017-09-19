package com.higgsup.bizwebcrawler.controller.common;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by viquy 9:20 AM 9/19/2017
 */
public class DividePage {
    private int page;
    private Elements checkDataFromWeb;

    public Elements getCheckDataFromWeb() {
        return checkDataFromWeb;
    }

    public void setCheckDataFromWeb(Document getHTML) {
        String titleURL = getHTML.title();
        if (titleURL.equals("Đăng nhập quản trị hệ thống")) {
            throw new Error("Error cookie");
        }
        Elements getDataWeb = getHTML.select("div div[class*=t-status-text dataTables_info]");
        if(getDataWeb.text().equalsIgnoreCase("")){
            throw new Error("Error Id = null!");
        }
        this.checkDataFromWeb =getDataWeb;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page > 50) {
            if (page % 50 == 0) {
                page = page / 50;
            } else {
                page = page / 50 + 1;
            }
        } else {
            page = 1;
        }
        this.page = page;
    }
}
