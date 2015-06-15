package com.szucie.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends LazyViewPager {
	public boolean setTouchModel = false;//控制是否可以滑动的

	public CustomViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
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
