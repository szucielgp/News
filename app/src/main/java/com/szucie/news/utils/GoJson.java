package com.szucie.news.utils;


import com.google.gson.Gson;

/**
 * Created by ASUA on 2015/5/21.
 */
//将数据解析抽象成类，都可用这个类来解析。
public class GoJson {
    public static <T> T jsonToBean(String jsonResult,Class<T> clz){
        Gson gson = new Gson();
        T t = gson.fromJson(jsonResult,clz);
        return  t;
    }
    //此处要使用T使用E无法报错
}
