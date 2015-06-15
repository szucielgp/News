package com.szucie.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.szucie.news.fragment.Fragment1;
import com.szucie.news.fragment.Fragment2;
import com.szucie.news.fragment.HomeNFragment;
import com.szucie.news.fragment.MenuFragment;


public class MainActivity extends SlidingFragmentActivity implements MenuFragment.OnFragmentInteractionListener{
   private SlidingMenu  sm;
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.menu);
        setContentView(R.layout.content);//这里相当于一个父容器
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);//设置全屏；将这句注释掉之后可以正常运行。
        sm = getSlidingMenu();
        //2 设置滑动菜单是在左边出来还是右边出来
        //参数可以设置左边LEFT，也可以设置右边RIGHT ，还能设置左右LEFT_RIGHT
        sm.setMode(SlidingMenu.LEFT);
        sm.setBackgroundColor(getResources().getColor(R.color.black));
        //3 设置滑动菜单出来之后，内容页，显示的剩余宽度
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //4 设置滑动菜单的阴影 设置阴影，阴影需要在开始的时候，特别暗，慢慢的变淡
        sm.setShadowDrawable(R.drawable.shadow);
        //5 设置阴影的宽度
      //  sm.setShadowWidth(R.dimen.shadow_width);
        //6 设置滑动菜单的范围
        // 第一个参数 SlidingMenu.TOUCHMODE_FULLSCREEN 可以全屏滑动
        // 第二个参数 SlidingMenu.TOUCHMODE_MARGIN 只能在边沿滑动
        // 第三个参数 SlidingMenu.TOUCHMODE_NONE 不能滑动
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//        HomeFragment homeFragment  = HomeFragment.newInstance("","");
//        StartFragment(R.id.content_frame,homeFragment);


        HomeNFragment newsFragment = new HomeNFragment();
        StartFragment(R.id.content_frame,newsFragment);

        MenuFragment menuFragment = new MenuFragment();
        StartFragment(R.id.menu_frame,menuFragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onFragmentInteraction(int position) {
        Fragment f = null;
        switch (position){
            case 0:
           f = Fragment1.newInstance("a","");

                break;
            case 1:
              f = Fragment2.newInstance("b", "");

                break;
            default:
               f = Fragment1.newInstance("c","");

                break;
        }

        StartFragment(R.id.content_frame,f);
    }


    public void StartFragment(int id, Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(id,fragment).commit();
        sm.toggle();
    }
//    public void switchFragment(Fragment f){
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
//        //自动切换
//        sm.toggle();
//    }


}
