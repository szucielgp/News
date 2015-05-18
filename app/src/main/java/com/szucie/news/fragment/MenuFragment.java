package com.szucie.news.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.szucie.news.R;

import java.util.ArrayList;
import java.util.List;
//一开始总是出错，是因为其他的acticity没有实现定义的接口！！！！可以用来传递数据用
/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 */

public class MenuFragment extends Fragment  implements OnItemClickListener {

    private  View view;
    //  private OnFragmentInteractionListener mListener;
    private ArrayAdapter<String> adapter;
     private OnFragmentInteractionListener onFragmentInteractionListener ;

//    public MenuFragment() {
//        // Required empty public constructor
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                initData());
    }

    public List<String> initData(){
        List<String> list = new ArrayList<String>();
        list.add("fragment1");
        list.add("fragment2");
        list.add("fragment3");
        list.add("fragment4");
        list.add("fragment5");
        return list;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_menu, null);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);//把这句话给注释掉了！！！
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onFragmentInteractionListener.onFragmentInteraction(position);
//        Fragment f = null;
//        switch(position){
//            case 0:
//                f = new Fragment1();
//
//                break;
//            case 1:
//                f = new Fragment2();
//
//
//                break;
//            default:
//                f = new Fragment1();
//
//
//                break;
//        }
       // switchFragment(f);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int position);
    }

    //一种实现的方式
//    private void switchFragment(Fragment f) {
//        // TODO Auto-generated method stub
//        if(f != null){
//            if(getActivity() instanceof MainActivity){
//                ((MainActivity)getActivity()).switchFragment(f);
//            }
//        }
//    }

}
