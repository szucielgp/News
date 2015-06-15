package com.szucie.news.newscenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ASUA on 2015/5/18.
 */
public abstract class NewsBasePage {
    //画界面，初始化数据
    public View view;
    public  Context ct;

    public NewsBasePage(Context ct){
        this.ct = ct;
    //    Log.e("<<<", "构造父类");
      //  initData();//要在构造父类的时候去调用这个方法。
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = initView(inflater);


    }
    public View getView(){return view;}
    public abstract View initView(LayoutInflater inflater);
    public abstract void initData();


}
