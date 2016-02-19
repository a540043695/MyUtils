package com.qf1514.kuaikan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.qf1514.kuaikan.fragment.FenleiFragment;
import com.qf1514.kuaikan.fragment.RemenFragment;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.fg_find_fg)
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragment[] fragments;
    @ViewInject(R.id.bt_find_remen)
    private Button rm;
    @ViewInject(R.id.bt_find_fenlei)
    private Button fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        addFragment();
    }

    /**
     * 添加Fragment
     */
    private void addFragment() {
        fragments = new Fragment[]{new RemenFragment(), new FenleiFragment()};
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            transaction.add(R.id.fg_find_fg, fragments[i]);
            transaction.hide(fragments[i]);
        }
        transaction.show(fragments[0]);
        rm.setSelected(true);
        transaction.commit();
    }

    /**
     * 切换Fragment
     *
     * @param view
     */
    @OnClick(R.id.bt_find_remen)
    public void changeRemenFragment(View view) {
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
        transaction1.show(fragments[0]);
        transaction1.hide(fragments[1]);
        rm.setSelected(true);
        fl.setSelected(false);
        transaction1.commit();
    }

    @OnClick(R.id.bt_find_fenlei)
    public void changeFenleiFragment(View view) {
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
        transaction2.show(fragments[1]);
        transaction2.hide(fragments[0]);
        fl.setSelected(true);
        rm.setSelected(false);
        transaction2.commit();
    }

    @OnClick(R.id.iv_find_search)
    public void searchDatas(View view) {
        Intent intent = new Intent(this, RemenSearchActivity.class);
        startActivity(intent);
    }

}
