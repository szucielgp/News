package com.szucie.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.szucie.news.R;
import com.szucie.news.home.BasePage;
import com.szucie.news.home.FunctionPage;
import com.szucie.news.home.GovAffairsPage;
import com.szucie.news.home.NewsCenterPage;
import com.szucie.news.home.SettingPage;
import com.szucie.news.home.SmartServicePage;
import com.szucie.news.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUA on 2015/5/18.
 */
public class HomeNFragment extends BaseFragment  {
    public CustomViewPager viewPager;
    public RadioGroup radioGroup;
    public View view;
    public Context ct = getActivity();
    public List<BasePage> pagerList = new ArrayList<BasePage>();
    @Override
    public void initData(Bundle bundle) {
           pagerList.add(new FunctionPage(ct));
           pagerList.add(new GovAffairsPage(ct));
           pagerList.add(new NewsCenterPage(ct));
           pagerList.add(new SmartServicePage(ct));
           pagerList.add(new SettingPage(ct));
           MyAdapter myAdapter = new MyAdapter(ct,pagerList);
           viewPager.setAdapter(myAdapter);


    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.home_layout,null, false);
        viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) view.findViewById(R.id.main_radio);
        return view ;
    }



    class  MyAdapter extends PagerAdapter {

        private  Context ct;
        private List<BasePage> list = new ArrayList<BasePage>();
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

            ((CustomViewPager)container).addView(list.get(position).getView(),0);
            return list.get(position).getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ((CustomViewPager)container).removeView(list.get(position).getView());
        }
    }
}
