package com.szucie.news.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.heima.news.base.BasePage;

public class SettingPage extends BasePage{


	public SettingPage(Context ct) {
		super(ct);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("我是设置");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
