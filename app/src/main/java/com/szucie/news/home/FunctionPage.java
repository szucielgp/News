package com.szucie.news.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ASUA on 2015/5/18.
 */
public class FunctionPage extends BasePage {

    public  FunctionPage(Context ct){

        super(ct);//构造子类，必须要先构造父类！
    //    Log.e("<<<", "构造子类");

    }
    @Override
    public View initView(LayoutInflater inflater) {
      //  Log.e("<<<", "调用子类的方法");

        TextView textView  = new TextView(ct);
        textView.setText("首页");
        return textView;
    }

    @Override
    public void initData() {

    }
}
