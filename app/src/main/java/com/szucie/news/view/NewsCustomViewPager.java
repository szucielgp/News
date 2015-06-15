package com.szucie.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NewsCustomViewPager extends LazyViewPager {
	public boolean setTouchModel = true;//控制是否可以滑动的

	public NewsCustomViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NewsCustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
//截断事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (setTouchModel)
			return super.onInterceptTouchEvent(ev);
		else
			return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (setTouchModel)
			return super.onTouchEvent(ev);
		else
			return false;
	}

}
