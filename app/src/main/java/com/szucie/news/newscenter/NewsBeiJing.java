package com.szucie.news.newscenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.szucie.news.R;
import com.szucie.news.acty.NewsDtailActivity;
import com.szucie.news.adapter.NewsListAdapter;
import com.szucie.news.bean.NewsData;
import com.szucie.news.bean.NewsItem;
import com.szucie.news.bean.NewsItemLeast;
import com.szucie.news.datacallback.DataCallBack;
import com.szucie.news.home.NewsCenterPage;
import com.szucie.news.utils.GoJson;

import java.util.List;

public class NewsBeiJing extends NewsBasePage implements DataCallBack {
    public NewsCenterPage newsCenterPage;
    public  String  BEIJING_URL = "http://zhbj.qianlong.com/static/api/news/10007/list_1.json";;
    public View view;
    public   ListView bjNewsList;
    public  NewsListAdapter newsListAdapter;
    List<NewsItemLeast> newsItemLeasts;
  //  public Handler handler;
	public NewsBeiJing(Context ct,NewsCenterPage newsCenterPage) {
		super(ct);
        this.newsCenterPage = newsCenterPage;
		// TODO Auto-generated constructor stub
	}

    public void askData(){
        //这里用一个线程就是异步，
     //   newsCenterPage.executeMessage(NewsBeiJing.this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 这就相当于A类调用B的方法C
                 */
                newsCenterPage.executeMessage(NewsBeiJing.this);
            }
        }).start();



    }

    public  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x123){
                newsListAdapter = new NewsListAdapter(ct,(List)msg.obj);
                bjNewsList.setAdapter(newsListAdapter);
            }


        }
    };//这仍然是主线程，不能在子线程中去改变UI
    //从服务器接受数据然后处理数据的线程。但这样是错误的
//    class GetNewsThread implements Runnable{
//
//        @Override
//        public void run() {
//            Looper.prepare();
//            handler = new Handler() {
//                @Override
//                public void handleMessage(Message msg){
//                    if(msg.what==0x123){
//                        newsListAdapter = new NewsListAdapter(ct,(List)msg.obj);
//                        bjNewsList.setAdapter(newsListAdapter);
//                    }
//                    else if(msg.what==0x234){
//                        List<NewsItem> list = (List)msg.obj;
//
//                    }
//                }
//            };
//            Looper.loop();
//        }
//    }

	@Override
	public View initView(LayoutInflater inflater) {

		view = inflater.inflate(R.layout.newslist_layout,null);
        bjNewsList = (ListView) view.findViewById(R.id.news_list_view);
       // new Thread(new GetNewsThread()).start();
        //处理每个新闻项被点击进去之后的列表
        bjNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(ct,NewsDtailActivity.class);
                Bundle data = new Bundle();
                data.putString("newUrl",newsItemLeasts.get(position).getUrl());
                intent.putExtras(data);
                ct.startActivity(intent);

            }
        });
		return view;
	}

	@Override
	public void initData() {

     getBeiJingNews(BEIJING_URL);

	}

    @Override
    public void getData(List<NewsItem> list) {
           BEIJING_URL = list.get(0).getUrl();
      //  BEIJING_URL = "http://zhbj.qianlong.com/static/api/news/10007/list_1.json";
       // getBeiJingNews(list.get(0).getUrl());

        Log.e(">>>","回调成功");
    }

    public void getBeiJingNews(String url){
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>(){

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        Log.e(">>>>", responseInfo.result.toString());
                          handleJson(responseInfo.result);
                      //  getNewsFromJson(responseInfo.result);

                    }
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    public void handleJson(String jsonResult){
      //  NewsCenterCategory newsCenterCategory  = GoJson.jsonToBean(jsonResult, NewsCenterCategory.class);
        NewsData newsData = GoJson.jsonToBean(jsonResult,NewsData.class);
        if(newsData.getRetcode()==200){
           NewsData.NewsData_1 newsDatas = newsData.data;
           newsItemLeasts = newsDatas.news;
            for(int i=0;i<newsItemLeasts.size();i++){
                Log.e(">>>",newsItemLeasts.get(i).getTitle());
            }
        Message msg = new Message();
        msg.what= 0x123;

        msg.obj = newsItemLeasts;
      //  Myhandler.sendMessage(msg);
            if(msg.obj!=null){
                handler.sendMessage(msg);
            }
        }
    }
}
