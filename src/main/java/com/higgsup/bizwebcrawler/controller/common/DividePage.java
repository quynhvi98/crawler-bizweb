package com.higgsup.bizwebcrawler.controller.common;

/**
 * Created by viquy 9:20 AM 9/19/2017
 */
public class DividePage {
    private int page;

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
