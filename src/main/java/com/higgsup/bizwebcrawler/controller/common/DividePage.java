package com.higgsup.bizwebcrawler.controller.common;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

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

    public ArrayList<String> GetDataFromTRTagsInputSLe(Elements getDataInGetDataFromTRTagsInputSLe,ArrayList<String> listString){
        for (Element d : getDataInGetDataFromTRTagsInputSLe
                ) {
            String[] splitTakeValue = d.toString().split("\">");
            splitTakeValue = splitTakeValue[1].split("</option>");
            if (listString.indexOf(splitTakeValue[0]) < 0) {
                listString.add(splitTakeValue[0]);
            }
        }
        return listString;
    }
}
