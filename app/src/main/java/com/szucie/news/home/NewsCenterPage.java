package com.szucie.news.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.heima.news.base.BasePage;
import com.heima.news.bean.NewsCenterCategory;
import com.heima.news.bean.NewsCenterCategory.CenterCategory;
import com.heima.news.fragment.MenuFragment2;
import com.heima.news.utils.GsonUtils;
import com.heima.news.utils.HMApi;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

public class NewsCenterPage extends BasePage {

	public NewsCenterPage(Context ct) {
		super(ct);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("我是新闻中心");
		return textView;
	}

	@Override
	public void initData() {
		TestGet();
	}

	private void TestGet() {
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET, HMApi.NEWS_CENTER_CATEGORIES,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// textView.setText(responseInfo.result);

						// Gson gson = new Gson();
						// NewsCenterCategory category =
						// gson.fromJson(responseInfo.result,
						// NewsCenterCategory.class);
						LogUtils.d(responseInfo.result);

						ProcessData(responseInfo.result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});

	}

	private List<String> menuNewCenterList = new ArrayList<String>();

	protected void ProcessData(String result) {
		NewsCenterCategory category = GsonUtils.jsonToBean(result,
				NewsCenterCategory.class);
		if (200 == category.retcode) {
			List<CenterCategory> data = category.data;
			for (CenterCategory cate : data) {
				menuNewCenterList.add(cate.title);
			}
			
		}
	}

}
