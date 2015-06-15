package com.szucie.news.acty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.szucie.news.R;


public class NewsDtailActivity extends ActionBarActivity {
    String url;
    WebView webView;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x123){
              // webView.loadUrl((String) msg.obj);
                webView.loadDataWithBaseURL(null,(String)msg.obj,"text/html","utf-8",null);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtail);
        webView = (WebView) findViewById(R.id.dtailnews);
        Intent intent = getIntent();
        url = intent.getStringExtra("newUrl");
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBeiJingNews();
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_dtail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getBeiJingNews(){
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>(){

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        Log.e(">>>>", responseInfo.result.toString());
                        Message msg = new Message();
                        msg.what = 0x123;
                        msg.obj = responseInfo.result.toString();
                        handler.sendMessage(msg);
                      //  handleJson(responseInfo.result);
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
}
