package com.szucie.news.newscenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class NewsTravel extends NewsBasePage{


	public NewsTravel(Context ct) {
		super(ct);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("旅游新闻");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
