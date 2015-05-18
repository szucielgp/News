package com.szucie.news.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ASUA on 2015/5/18.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initData(savedInstanceState);
    }
//    public void start(){
//        System.out.print("");
//    }抽象类中并不一定全是抽象的方法！！
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View  view = initView(inflater);
        return view;
    }

    public  abstract  View  initView(LayoutInflater inflater);
    public abstract  void  initData(Bundle bundle);
//定义借口不能实现
//    public static interface InitView{//这个借口要定义在类的里面！一个类只有一个public的类型！
//        public  View  initView(LayoutInflater inflater);
//    }
//    public  interface  InitData{
//        public  void  initData(Bundle bundle);
//    }
}



