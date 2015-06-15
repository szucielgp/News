package com.szucie.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szucie.news.R;
import com.szucie.news.bean.NewsItemLeast;
import com.szucie.news.loopj.android.image.SmartImageView;

import java.util.List;

/**
 * Created by ASUA on 2015/5/28.
 */
public class NewsListAdapter extends BaseAdapter {

    public Context context;
    public RelativeLayout newsItemLayout;
    List<NewsItemLeast> list;
    public NewsListAdapter(Context context,List <NewsItemLeast>list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        newsItemLayout = (RelativeLayout) inflater.inflate(R.layout.news_item_layout,null);
        TextView newsTitle = (TextView) newsItemLayout.findViewById(R.id.tv_listview_item_title);
        TextView newsTime = (TextView) newsItemLayout.findViewById(R.id.tv_listview_item_time);
        SmartImageView smartImageView = (SmartImageView) newsItemLayout.findViewById(R.id.siv_listview_item_icon);

        newsTitle.setText(list.get(position).getTitle().toString());
        newsTime.setText(list.get(position).getPubdate().toString());
         smartImageView.setImageUrl(list.get(position).getListimage());
        return newsItemLayout;
    }
}
