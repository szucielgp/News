package com.szucie.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.szucie.news.R;
import com.szucie.news.home.BasePage;
import com.szucie.news.home.FunctionPage;
import com.szucie.news.home.GovAffairsPage;
import com.szucie.news.home.NewsCenterPage;
import com.szucie.news.home.SettingPage;
import com.szucie.news.home.SmartServicePage;
import com.szucie.news.view.CustomViewPager;
import com.szucie.news.view.LazyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUA on 2015/5/18.
 */
public class HomeNFragment extends BaseFragment  {
    @ViewInject(R.id.viewpager)
    public CustomViewPager viewPager;
    @ViewInject(R.id.main_radio)
    public RadioGroup radioGroup;
    public View view;
    //public SlidingMenu sm;
    //public Context ct;

    public List<BasePage> pagerList = new ArrayList<BasePage>();

//    public HomeNFragment(SlidingMenu sm) {
//        super(sm);
//    }

    @Override
    public void initData(Bundle bundle) {
          // ct = getActivity();
           pagerList.add(new FunctionPage(ct));
           pagerList.add(new NewsCenterPage(ct));
           pagerList.add(new SmartServicePage(ct));
           pagerList.add(new GovAffairsPage(ct));
           pagerList.add(new SettingPage(ct));
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.home_layout,null);
      ViewUtils.inject(this, view);
       // viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
       // radioGroup = (RadioGroup) view.findViewById(R.id.main_radio);
        //定义数组记录几个按钮的id，将postion与id对应起来
        final int[] radioGroupId = new int[]{R.id.rb_function,R.id.rb_news_center,
                                        R.id.rb_smart_service,R.id.rb_gov_affairs,R.id.rb_setting};

        radioGroup.check(radioGroupId[0]);
        MyAdapter myAdapter = new MyAdapter(ct,pagerList);

        viewPager.setAdapter(myAdapter);//初始数据是在onCreate中的，还没有拿到viewpager,不能给它设置adapter
// 通过viewpager改变按钮
        viewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroupId[position]);
                //如果位置是o的话，才能去滑动菜单！其他的都不能滑动菜单
                if(position==0){
                    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                }
                else {
                    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
                BasePage page = pagerList.get(position);
                page.initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //通过按钮改变viewpager
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           /*
           * 05-19 15:47:15.659  15218-15218/com.szucie.news E/>>﹕ 2131296329
             05-19 15:47:22.639  15218-15218/com.szucie.news E/>>﹕ 2131296330
             05-19 15:47:28.789  15218-15218/com.szucie.news E/>>﹕ 2131296331
              05-19 15:47:30.189  15218-15218/com.szucie.news E/>>﹕ 2131296332
            05-19 15:47:31.359  15218-15218/com.szucie.news E/>>﹕ 2131296333
           * */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_function:
                     //   Log.e(">>", String.valueOf(checkedId));
                       // viewPager.setCurrentItem(0, false);
                       // 这里的true/false的变量是控制在其中是否有滑动的效果
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.rb_news_center:
             //           Log.e(">>", String.valueOf(checkedId));

                      //  viewPager.setCurrentItem(1, false);
                        viewPager.setCurrentItem(1, true);
                        break;
                    case R.id.rb_smart_service:
              //          Log.e(">>", String.valueOf(checkedId));

                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_gov_affairs:
               //         Log.e(">>", String.valueOf(checkedId));

                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_setting:
               //         Log.e(">>", String.valueOf(checkedId));

                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }
            }
        });
        return view ;
    }



    class  MyAdapter extends PagerAdapter {

        private  Context ct;
        private List<BasePage> list;
        //private List<BasePage> list = new ArrayList<BasePage>();在创建它的时候进行初始化即可。
        public MyAdapter(Context ct,List list){
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

            ((CustomViewPager)container).addView(list.get(position).getView(), 0);
            return list.get(position).getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ((CustomViewPager)container).removeView(list.get(position).getView());
        }
    }
}
