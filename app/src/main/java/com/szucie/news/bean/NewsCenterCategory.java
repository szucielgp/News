package com.szucie.news.bean;

import java.util.List;

/**
 * Created by ASUA on 2015/5/21.
 */
public class NewsCenterCategory {
    public int retcode;
    public List<CenterCategory> data;
    public List<Integer> extend;
   // public CenterCategoryItem0 data0;
//每一层再单独抽象成一个类
    public static class CenterCategory{
        public List<CenterCategoryItem> childrenItem;
        public int id;
        public String title;
        public int type;
        public String url1;
       public String url;
        public String dayurl;
        public String excurl;
        public String weekurl;


    }

    public static class CenterCategoryItem{
        public int id;
        public String title;
        public int type;
        public String url;
    }
//    public static class CenterCategoryItem0{
//        public String id;
//        public  String title;
//        public  int type;
//        public  List<CenterCategoryItem> childrenItem;
//    }


}
