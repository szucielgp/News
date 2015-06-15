package com.szucie.news.datacallback;

import com.szucie.news.bean.NewsItem;

import java.util.List;

/**
 * Created by ASUA on 2015/6/4.
 */
public interface DataCallBack {
    void getData(List<NewsItem> list);
}
