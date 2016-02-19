package com.qf1514.kuaikan;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qf1514.kuaikan.adapter.ItemFlDetailsAdapter;
import com.qf1514.kuaikan.bean.FlDetails;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FenleiDetailActivity extends Activity {
    private String title;
    private List<FlDetails.DataEntity.TopicsEntity> list;
    private ItemFlDetailsAdapter<FlDetails.DataEntity.TopicsEntity> adapter;
    private PullToRefreshListView listView;
    private TextView heard;
    private ImageView back;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fldetails);
        heard = (TextView) findViewById(R.id.tv_base);
        back = (ImageView) findViewById(R.id.iv_base_back);
        listView = (PullToRefreshListView) findViewById(R.id.lv_detail_content);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        initDatas();
        goBack();
        list = new ArrayList<>();
        adapter = new ItemFlDetailsAdapter<>(this, list);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initDatas();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strName = list.get(position - 1).getTitle();
                Toast.makeText(FenleiDetailActivity.this, strName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatas() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        heard.setVisibility(View.VISIBLE);
        heard.setText(title);
        heard.setTextColor(Color.BLACK);
        String url = "http://api.kuaikanmanhua.com/v1/topics?offset=" + count + "&limit=20&tag=";
        count += 20;
        String urlContent = null;
        try {
            urlContent = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringRequest stringRequest = new StringRequest(url + urlContent, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.w("HCB", response);
                Gson gson = new Gson();
                FlDetails flDetails = gson.fromJson(response, FlDetails.class);
                showContent(flDetails);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.requestQueue.add(stringRequest);
    }

    private void showContent(FlDetails flDetails) {
        list.addAll(flDetails.getData().getTopics());
        adapter.notifyDataSetChanged();
        listView.onRefreshComplete();
        Log.w("COUNTTTTTTTTTTTT", count + "");
    }

    private void goBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
