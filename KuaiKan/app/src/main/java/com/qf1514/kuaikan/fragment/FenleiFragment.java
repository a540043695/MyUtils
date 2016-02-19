package com.qf1514.kuaikan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.qf1514.kuaikan.FenleiDetailActivity;
import com.qf1514.kuaikan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/10.
 */
public class FenleiFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<Map<String, Object>> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fenlei, container, false);
        gridView = (GridView) view.findViewById(R.id.gv_fenlei_gv);
        showGridView();
        return view;
    }


    private void showGridView() {
        list = new ArrayList<>();
        String[] text = {"优秀新连载", "短篇", "已完结", "男性", "恋爱", "爆笑", "耽美", "恐怖", "剧情", "日常", "三次元", "治愈", "百合", "奇幻", "古风"};
        for (int i = 0; i < 15; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.mipmap.e);
            map.put("text", text[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.item_fenlei, new String[]{"image", "text"}, new int[]{R.id.iv_item_fenlei_image, R.id.tv_item_fenlei_style});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, Object> item2 = (HashMap<String, Object>) parent.getItemAtPosition(position);
        String content = item2.get("text").toString();
        Intent intent = new Intent(getActivity(), FenleiDetailActivity.class);
        intent.putExtra("title", content);
        Log.w("FASONG", content);
        startActivity(intent);
    }
}
