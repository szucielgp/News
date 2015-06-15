package com.szucie.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szucie.news.R;

import java.util.List;

/**
 * Created by ASUA on 2015/5/22.
 */
public class MyAdapter extends BaseAdapter {

    public Context context;
    public LinearLayout linearLayout;
    List<String> list;
    public MyAdapter(Context context,List <String>list){
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
       linearLayout = (LinearLayout) inflater.inflate(R.layout.menuitem_layout,null);
        TextView menutv = (TextView) linearLayout.findViewById(R.id.menu_textView);
    //    ImageView imageView = linearLayout.findViewById(R.id)
        menutv.setText(list.get(position).toString());
        return linearLayout;
    }
}
