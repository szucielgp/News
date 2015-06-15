package com.szucie.news.home;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.szucie.news.R;
import com.szucie.news.adapter.NewsListAdapter;
import com.szucie.news.bean.NewsCenterCategory;
import com.szucie.news.bean.NewsItem;
import com.szucie.news.datacallback.DataCallBack;
import com.szucie.news.newscenter.NewsBasePage;
import com.szucie.news.newscenter.NewsBeiJing;
import com.szucie.news.newscenter.NewsChina;
import com.szucie.news.newscenter.NewsEcnomic;
import com.szucie.news.newscenter.NewsEnterTainment;
import com.szucie.news.newscenter.NewsFemale;
import com.szucie.news.newscenter.NewsFunny;
import com.szucie.news.newscenter.NewsInternational;
import com.szucie.news.newscenter.NewsLife;
import com.szucie.news.newscenter.NewsMilitary;
import com.szucie.news.newscenter.NewsSports;
import com.szucie.news.newscenter.NewsTechnology;
import com.szucie.news.newscenter.NewsTravel;
import com.szucie.news.utils.Config;
import com.szucie.news.utils.GoJson;
import com.szucie.news.view.LazyViewPager;
import com.szucie.news.view.NewsCustomViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPage extends BasePage {


    public Handler handler;
    public List<NewsBasePage> pagerList = new ArrayList<>();
    public NewsCustomViewPager newsviewPager;
    public NewsAdapter newsAdapter;
    public NewsListAdapter newsListAdapter;
    public View view;
    public ListView listView;
    public RadioGroup radioGroup;
    List<ImageView> imageViewList ;
    public List<NewsItem> newsList;
    public NewsBeiJing beiJing;
    //scrollview的变量
    public HorizontalScrollView hScrollView;
    private int lastValue = -1;
    //imgaview的变量

    public NewsCenterPage(Context ct) {
		super(ct);
        beiJing = new NewsBeiJing(ct,NewsCenterPage.this);
        beiJing.askData();
		// TODO Auto-generated constructor stub
	}



    //从服务器接受数据然后处理数据的线程。
//    class InitThread implements Runnable{
//
//        @Override
//        public void run() {
//            Looper.prepare();
//            handler = new Handler() {
//               @Override
//                public void handleMessage(final Message msg){
//                    if(msg.what==0x123){
////                          newsListAdapter = new NewsListAdapter(ct,(List)msg.obj);
////                          listView.setAdapter(newsListAdapter);
//                        dataCallBack =  new NewsDataCallBack() {
//                            @Override
//                            public void getData(List <NewsItem>list) {
//                                dataCallBack.getData((List)msg.obj);
//                            }
//                        };
//
//                    }
//                    else if(msg.what==0x234){
//                           //List<NewsItem> list = (List)msg.obj;
//                        dataCallBack.getData((List)msg.obj);
////                           for (int i=0;i<list.size();i++){
////                               newList.add(list.get(i).getTitle());
////                           }
////                           newsListAdapter = new NewsListAdapter(ct,newList);
////                           listView.setAdapter(newsListAdapter);
//                   }
//                }
//            };
//            Looper.loop();
//        }
//    }

	@Override
	public View initView(LayoutInflater inflater) {

//       view =  inflater.inflate(R.layout.newslist_layout,null);
//       listView = (ListView) view.findViewById(R.id.news_list_view);
      //  new Thread(new InitThread()).start();
        //定义数组记录几个按钮的id，将postion与id对应起来
        imageViewList = new ArrayList<ImageView>();
        final int[] newsRadioGroupId = new int[]{
                R.id.rb_news_beijing, R.id.rb_news_china,
                R.id.rb_news_international, R.id.rb_news_entertainment,
                R.id.rb_news_sports, R.id.rb_news_life,
                R.id.rb_news_travel,R.id.rb_news_technology,
                R.id.rb_news_military,R.id.rb_news_finance_economics,
                R.id.rb_news_female,R.id.rb_news_funny};
        final int[] signImageViewListId = new int[]{
                R.id.sign_news_beijing, R.id.sign_news_china,
                R.id.sign_news_international, R.id.sign_news_entertainment,
                R.id.sign_news_sports, R.id.sign_news_life,
                R.id.sign_news_travel,R.id.sign_news_technology,
                R.id.sign_news_military,R.id.sign_news_finance_economics,
                R.id.sign_news_female,R.id.sign_news_funny};

        view = inflater.inflate(R.layout.news_layout,null);
        newsviewPager = (NewsCustomViewPager) view.findViewById(R.id.news_viewpager);
        radioGroup = (RadioGroup) view.findViewById(R.id.news_radio);
        //初始化小红条
       for(int i=0;i<signImageViewListId.length;i++){
           imageViewList.add((ImageView) view.findViewById(signImageViewListId[i]));
       }

        radioGroup.check(newsRadioGroupId[0]);
       // newsviewPager.setCurrentItem(0);


        hScrollView = (HorizontalScrollView) view.findViewById(R.id.news_title_scrollview);
        //通过viewpager改变Radiogroup
        newsviewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(lastValue>positionOffsetPixels){//向右滑动
                        hScrollView.scrollBy(-3,0);
                    }else if(lastValue < positionOffsetPixels){//向左滑动
                        hScrollView.scrollBy(3,0);
                    }
                    lastValue = positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {
                //让数组对应的id与position像对应。
                radioGroup.check(newsRadioGroupId[position]);
              //  hScrollView.scrollBy(10,0);
               // newsviewPager.gl
                //进行回调初始化每个viewpager中的数据
                NewsBasePage basepage = pagerList.get(position);
                basepage.initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      //  newsviewPager.setCurrentItem(0);
        //通过按钮改变viewpager
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_news_beijing:

                        // 这里的true/false的变量是控制在其中是否有滑动的效果
                        newsviewPager.setCurrentItem(0, true);
                        setNewsSignVisible(0);

//                        imageView.setVisibility(View.VISIBLE);//设置可见
//                        imageView1.setVisibility(View.INVISIBLE);//设置不可见，gone:意思是不可见的，而且不占用布局空间

                        break;
                    case R.id.rb_news_china:

                        newsviewPager.setCurrentItem(1, true);
                        setNewsSignVisible(1);
                        break;
                    case R.id.rb_news_international:

                        newsviewPager.setCurrentItem(2);
                        setNewsSignVisible(2);
                        break;
                    case R.id.rb_news_entertainment:

                        newsviewPager.setCurrentItem(3);
                        setNewsSignVisible(3);
                        break;
                    case R.id.rb_news_sports:

                        newsviewPager.setCurrentItem(4);
                        setNewsSignVisible(4);
                        break;
                    case R.id.rb_news_life:

                        newsviewPager.setCurrentItem(5);
                        setNewsSignVisible(5);
                        break;
                    case R.id.rb_news_travel:

                        newsviewPager.setCurrentItem(6);
                        setNewsSignVisible(6);
                        break;
                    case R.id.rb_news_technology:

                        newsviewPager.setCurrentItem(7);
                        setNewsSignVisible(7);
                        break;
                    case R.id.rb_news_military:

                        newsviewPager.setCurrentItem(8);
                        setNewsSignVisible(8);
                        break;
                    case R.id.rb_news_finance_economics:

                        newsviewPager.setCurrentItem(9);
                        setNewsSignVisible(9);
                        break;
                    case R.id.rb_news_female:

                        newsviewPager.setCurrentItem(10);
                        setNewsSignVisible(10);
                        break;
                    case R.id.rb_news_funny:

                        newsviewPager.setCurrentItem(11);
                        setNewsSignVisible(11);
                        break;
                    default:
                        newsviewPager.setCurrentItem(0);
                        setNewsSignVisible(0);
                        break;
                }
            }
        });
        return view;

//        pagerList.add(new TextView(ct));应该在初始化数据时为其动态添加
        //   newsAdapter = new NewsAdapter(ct,pagerList);
        //    newsviewPager.setAdapter(newsAdapter);
//        newsListAdapter = new NewsListAdapter(ct,newList);因为newList要在从服务器中得到，需要耗时间，所以不能再主线程中去做这个操作。
//        listView.setAdapter(newsListAdapter);

	}

  //设置小红条是否可见的方法
    public void setNewsSignVisible(int position){
        for(int i=0;i<imageViewList.size();i++){
            imageViewList.get(i).setVisibility(View.INVISIBLE);
        }
        imageViewList.get(position).setVisibility(View.VISIBLE);
    }

	@Override
	public void initData() {
        getNews();
     //   new NewsBeiJing(ct,NewsCenterPage.this).askData();
      //  pagerList.add(new NewsBeiJing(ct,NewsCenterPage.this));
        pagerList.add(beiJing);
        pagerList.add(new NewsChina(ct));
        pagerList.add(new NewsInternational(ct));
        pagerList.add(new NewsEnterTainment(ct));
        pagerList.add(new NewsSports(ct));
        pagerList.add(new NewsLife(ct));
        pagerList.add(new NewsTravel(ct));
        pagerList.add(new NewsTechnology(ct));
        pagerList.add(new NewsMilitary(ct));
        pagerList.add(new NewsEcnomic(ct));
        pagerList.add(new NewsFemale(ct));
        pagerList.add(new NewsFunny(ct));

        beiJing.initData();
            newsAdapter = new NewsAdapter(ct,pagerList);
            newsviewPager.setAdapter(newsAdapter);






	}

    public void getNews(){
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
                Config.NEWS_CENTER_CATEGORIES,
                new RequestCallBack<String>(){

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e(">>>>", responseInfo.result);
                     //  handleJson(responseInfo.result);
                        getNewsFromJson(responseInfo.result);
                    }
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }


    //解析json数据
    public List<NewsItem> getNewsFromJson(String jsonResult){
        try {

            JSONObject job = new JSONObject(jsonResult);
            JSONArray jsonArray = job.getJSONArray("data");
            JSONObject newobj = jsonArray.getJSONObject(0);
            JSONArray newsArray = newobj.getJSONArray("children");
            int len =newsArray.length();
           List<NewsItem> list = new ArrayList<NewsItem>();

            for(int i=0;i<len;i++) {
                 NewsItem item = getObjectFromJson(newsArray.getJSONObject(i));
               list.add(item);
                System.out.println(item.getTitle());
            }


           Message msg = new Message();
          msg.what= 0x235;
         //  msg.what= 0x789;
            msg.obj = list;
          //  NewsBeiJing.Myhandler.sendMessage(msg);
            if (msg!=null){
               handler.sendMessage(msg);
            }

            return newsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    NewsItem getObjectFromJson(JSONObject jobj) {
        try {
            NewsItem newsItem = new NewsItem();
           // newsItem.setId();
            newsItem.setId(jobj.getString("id"));
            newsItem.setTitle(jobj.getString("title"));
            newsItem.setType(jobj.getInt("type"));
            newsItem.setUrl(jobj.getString("url"));

            return newsItem;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

//解析NewsCenterCategory 的CerterCategoryItem时出错
    public void handleJson(String jsonResult){
       NewsCenterCategory newsCenterCategory  = GoJson.jsonToBean(jsonResult, NewsCenterCategory.class);
       if(newsCenterCategory.retcode==200){
            List<NewsCenterCategory.CenterCategory> centerCategories = newsCenterCategory.data;
         //  NewsCenterCategory.CenterCategoryItem0 centerCategoryItem0 = newsCenterCategory.data0;
          List<NewsCenterCategory.CenterCategoryItem> newLocation  = centerCategories.get(0).childrenItem;
          // String newLocation  = centerCategories.get(1).url1;
         //  System.out.println(newLocation);
//           for(NewsCenterCategory.CenterCategory cate:centerCategories){
//                // newList.add(cate.title.toString());
//               System.out.println(cate.childrenItem);
//           }
       }
//        adapter = new ArrayAdapter<String>(ct,
//                android.R.layout.simple_list_item_1, android.R.id.text1,
//                newList);
//        newsListAdapter = new NewsListAdapter(ct,newList);
//        listView.setAdapter(newsListAdapter);
      //  dataCallBack.getData(menuList);
//        for(int i=0;i<menuList.size();i++){
//            System.out.println(menuList.get(i));
//        }

    //因为intiData要去请求网络，而initView需要数据，这是会造成UI阻塞，所以使用Handler的消息处理机制，另外用一条线程去等待网络获取数据
//        Message msg = new Message();
//        msg.what= 0x123;
//        msg.obj = newList;
//        handler.sendMessage(msg);

    }



    public void executeMessage(final DataCallBack dataCallBack1){
       // dataCallBack1.getData(newsList);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler(){
                    @Override
                    public void handleMessage( Message msg) {
                        super.handleMessage(msg);
                        if (msg.what==0x235){
                            dataCallBack1.getData((List<NewsItem>) msg.obj);
                        }
                    }
                };
                Looper.loop();
            }
        }).start();

    }
    //内部类viewpage的adapter
    class  NewsAdapter extends PagerAdapter {

        private  Context ct;
        private List<NewsBasePage> list;
        //private List<BasePage> list = new ArrayList<BasePage>();在创建它的时候进行初始化即可。
        public NewsAdapter(Context ct,List list){
            this.ct = ct;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(list.get(position).getView(), 0);
            return list.get(position).getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

           container.removeView(list.get(position).getView());
        }
    }


}
