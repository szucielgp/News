package com.szucie.news.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ASUA on 2015/5/18.
 */
public abstract class BasePage {
    public View view;
    public Context ct;

    public BasePage(Context ct){
        this.ct = ct;
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = initView(inflater);
    }
    public View getView(){return view;}
    public abstract View initView(LayoutInflater inflater);
    public abstract void initData();


}
