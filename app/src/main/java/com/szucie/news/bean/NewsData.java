package com.szucie.news.bean;

import java.util.List;

/**
 * Created by ASUA on 2015/6/3.
 */
public class NewsData {
    public NewsData_1 data;
    public int retcode;


    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class NewsData_1{
        public String countcommenturl;
        public String more;
        public List<NewsItemLeast> news;
        public String title;
        public List topic;
        public List<NewsItemLeast> topnews;


    }

}
