package com.qf1514.kuaikan.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qf1514.kuaikan.MyApplication;
import com.qf1514.kuaikan.R;
import com.qf1514.kuaikan.adapter.MyPagerAdapter;
import com.qf1514.kuaikan.bean.RemenDatas;
import com.qf1514.kuaikan.bean.VPImageDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/10.
 */
public class RemenFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private PullToRefreshScrollView refreshScrollView;
    private ViewPager viewPager;
    private List<String> urlList;
    private List<ImageView> imageList;
    private MyPagerAdapter adapter;
    private String s;
    private ImageView[] points;
    private List<RemenDatas.DataEntity.TopicsEntity.TopicsEntity2> topics1;
    private LinearLayout rgPoints;
    private LinearLayout llMZPHBScrollView;
    private LinearLayout llDZRPScrollView;
    private LinearLayout llXZCLScrollView;
    private List<String> mzphbImageUrl;
    private ImageView[] mzphbContent;
    private List<String> dzrpImageUrl;
    private List<String> dzrpName;
    private List<String> dzrpZuozhe;
    private List<String> xzclImageUrl;
    private List<String> xzclName;
    private List<String> xzclZuozhe;
    private List<String> mzphbName;
    private List<String> mzphbZuozhe;
    private LinearLayout llZBLTScrollView;
    private List<String> zbltImageUrl;
    private List<String> zbltName;
    private List<String> zbltZuozhe;
    private LinearLayout llGFHDScrollView;
    private List<String> gfhdImageUrl;
    private List<String> gfhdName;
    private List<String> gfhdZuozhe;
    private LinearLayout llWJJZScrollView;
    private List<String> wjjzImageUrl;
    private List<String> wjjzName;
    private List<String> wjjzZuozhe;
    private LinearLayout llSYZPScrollView;
    private List<String> syzpImageUrl;
    private List<String> syzpName;
    private List<String> syzpZuozhe;
    private RemenDatas remenDatas;
    private VPImageDatas vpImageDatas;
    private List<RemenDatas.DataEntity.TopicsEntity> remenList;
    private boolean isrunning = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                if (isrunning) {
                    handler.sendEmptyMessageDelayed(0, 2000);
                }
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remen, container, false);
        refreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sv_remen_sv);
        viewPager = (ViewPager) view.findViewById(R.id.vp_remen_vp);
        rgPoints = (LinearLayout) view.findViewById(R.id.ll_remen_point);
        llMZPHBScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv);
        llDZRPScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_dzrp);
        llXZCLScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_xzcl);
        llZBLTScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_zblt);
        llGFHDScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_gfhd);
        llWJJZScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_wjjz);
        llSYZPScrollView = (LinearLayout) view.findViewById(R.id.ll_remen_hsv_syzp);
        refreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setListener();
        urlList = new ArrayList<>();
        getGGImageUrl();
        handler.sendEmptyMessage(0);
        getMZPHBDatas();
        return view;
    }

    //下拉刷新
    protected void setListener() {
        refreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getImagetUrl(vpImageDatas);
                showRemenDatas(remenDatas);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
            }
        });
    }

    //从网上获取到热门的数据
    private void getMZPHBDatas() {
        final String rmDatasUrl = "http://api.kuaikanmanhua.com/v1/topic_lists/mixed";
        StringRequest stringRequest = new StringRequest(rmDatasUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.w("getMZPHBDatas", response);
                Gson gson = new Gson();
                remenDatas = gson.fromJson(response, RemenDatas.class);
                showRemenDatas(remenDatas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("getMZPHBDatas", error);
            }
        });
        MyApplication.requestQueue.add(stringRequest);

    }

    //显示热门页面数据
    private void showRemenDatas(RemenDatas remenDatas) {
        initList();
        List<RemenDatas.DataEntity.TopicsEntity> topics = remenDatas.getData().getTopics();
        remenList.addAll(topics);
        for (int i = 0; i < remenList.size(); i++) {
            topics1 = remenList.get(i).getTopics();
            topics.get(i).getTopics();
            String action = topics.get(i).getAction();
            switch (action) {
                case "topic_lists/1":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        mzphbImageUrl.add(imgUrl);
                        mzphbName.add(strMZPHBName);
                        mzphbZuozhe.add(strMZPHBZuozhe);
                        Log.w("HHHHHHHHHHHHHHHHH", mzphbImageUrl.size() + "");
                    }
                    initMZPHBScrollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "topic_lists/4":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        dzrpImageUrl.add(imgUrl);
                        dzrpName.add(strMZPHBName);
                        dzrpZuozhe.add(strMZPHBZuozhe);
                    }
                    initDZRPScollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "topic_lists/5":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        xzclImageUrl.add(imgUrl);
                        xzclName.add(strMZPHBName);
                        xzclZuozhe.add(strMZPHBZuozhe);
                    }
                    initXZCLScollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "topic_lists/3":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        zbltImageUrl.add(imgUrl);
                        zbltName.add(strMZPHBName);
                        zbltZuozhe.add(strMZPHBZuozhe);
                    }
                    initZBLTScollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "comic_lists/2":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        gfhdImageUrl.add(imgUrl);
                        gfhdName.add(strMZPHBName);
                        gfhdZuozhe.add(strMZPHBZuozhe);
                    }
                    initGFHDScollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "topic_lists/2":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        wjjzImageUrl.add(imgUrl);
                        wjjzName.add(strMZPHBName);
                        wjjzZuozhe.add(strMZPHBZuozhe);
                    }
                    initWJJZScollview();
                    refreshScrollView.onRefreshComplete();
                    break;
                case "topic_lists/others":
                    for (int j = 0; j < topics1.size(); j++) {
                        String imgUrl = topics1.get(j).getCover_image_url();
                        String strMZPHBName = topics1.get(j).getTitle();
                        String strMZPHBZuozhe = topics1.get(j).getUser().getNickname();
                        syzpImageUrl.add(imgUrl);
                        syzpName.add(strMZPHBName);
                        syzpZuozhe.add(strMZPHBZuozhe);
                    }
                    initSYZPScollview();
                    refreshScrollView.onRefreshComplete();
                    break;

            }
        }
    }

    //初始化各种list集合
    private void initList() {
        remenList = new ArrayList<>();
        mzphbImageUrl = new ArrayList<>();
        mzphbName = new ArrayList<>();
        mzphbZuozhe = new ArrayList<>();
        dzrpImageUrl = new ArrayList<>();
        dzrpName = new ArrayList<>();
        dzrpZuozhe = new ArrayList<>();
        xzclImageUrl = new ArrayList<>();
        xzclName = new ArrayList<>();
        xzclZuozhe = new ArrayList<>();
        zbltImageUrl = new ArrayList<>();
        zbltName = new ArrayList<>();
        zbltZuozhe = new ArrayList<>();
        gfhdImageUrl = new ArrayList<>();
        gfhdName = new ArrayList<>();
        gfhdZuozhe = new ArrayList<>();
        wjjzImageUrl = new ArrayList<>();
        wjjzName = new ArrayList<>();
        wjjzZuozhe = new ArrayList<>();
        syzpImageUrl = new ArrayList<>();
        syzpName = new ArrayList<>();
        syzpZuozhe = new ArrayList<>();
    }


    //显示每周排行榜数据

    private void initMZPHBScrollview() {
        mzphbContent = new ImageView[mzphbImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, mzphbImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(mzphbName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(mzphbZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), mzphbName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llMZPHBScrollView.addView(itemMZPHB);
        }
    }

    //显示读者热评数据
    private void initDZRPScollview() {
        mzphbContent = new ImageView[dzrpImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, dzrpImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(dzrpName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(dzrpZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), dzrpName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llDZRPScrollView.addView(itemMZPHB);
        }
    }

    //显示新作出炉的数据
    private void initXZCLScollview() {
        mzphbContent = new ImageView[xzclImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, xzclImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(xzclName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(xzclZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), xzclName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llXZCLScrollView.addView(itemMZPHB);
        }
    }

    //显示主编力推的数据
    private void initZBLTScollview() {
        mzphbContent = new ImageView[zbltImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, zbltImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(zbltName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(zbltZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), zbltName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llZBLTScrollView.addView(itemMZPHB);
        }
    }

    //显示官方活动的数据
    private void initGFHDScollview() {
        mzphbContent = new ImageView[gfhdImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, gfhdImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(gfhdName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(gfhdZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), gfhdName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llGFHDScrollView.addView(itemMZPHB);
        }
    }

    //显示完结作品的数据
    private void initWJJZScollview() {
        mzphbContent = new ImageView[wjjzImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, wjjzImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(wjjzName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(wjjzZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), wjjzName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llWJJZScrollView.addView(itemMZPHB);
        }
    }

    //显示所有作品的数据
    private void initSYZPScollview() {
        mzphbContent = new ImageView[syzpImageUrl.size()];
        ImageView imageView = null;
        View itemMZPHB = null;
        TextView tvName, tvZuozhe;
        for (int i = 0; i < mzphbContent.length; i++) {
            itemMZPHB = LayoutInflater.from(getContext()).inflate(R.layout.item_remen_mzphb, null);
            imageView = (ImageView) itemMZPHB.findViewById(R.id.iv_remen_mzphb);
            tvName = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_name);
            tvZuozhe = (TextView) itemMZPHB.findViewById(R.id.tv_remen_mzphb_zuozhe);
            MyApplication.bitmapUtils.display(imageView, syzpImageUrl.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            tvName.setText(syzpName.get(i));
            tvName.setTextColor(Color.WHITE);
            tvZuozhe.setText(syzpZuozhe.get(i));
            tvZuozhe.setTextColor(Color.WHITE);
            //每周排行榜里的item监听器
            final int finalI = i;
            itemMZPHB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), syzpName.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            llSYZPScrollView.addView(itemMZPHB);
        }
    }

    //activity销毁时停止延迟发送消息
    @Override
    public void onDestroy() {
        super.onDestroy();
        isrunning = false;
    }

    /**
     * 获取viewPager中显示的图片的url
     */
    protected void getGGImageUrl() {
        String url = "http://api.kuaikanmanhua.com/v1/banners";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //请求数据成功调用的方法
            @Override
            public void onResponse(String response) {
                Log.w("VPUrl", response);
                Gson gson = new Gson();
                vpImageDatas = gson.fromJson(response, VPImageDatas.class);
                getImagetUrl(vpImageDatas);
                addPoint();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.requestQueue.add(stringRequest);
    }

    /**
     * 通过网络获取到的图片地址，显示在viewPager上
     *
     * @param vpImageDatas
     */
    private void getImagetUrl(VPImageDatas vpImageDatas) {
        imageList = new ArrayList<>();
        for (int i = 0; i < vpImageDatas.getData().getBanner_group().size(); i++) {
            String imgUrl = vpImageDatas.getData().getBanner_group().get(i).getPic();
            urlList.add(imgUrl);
            s = urlList.get(i);
            ImageView imageView = new ImageView(getActivity());
            MyApplication.bitmapUtils.display(imageView, s);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //viewpager的点击监听，点击进入详情页
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你点击的是第" + (finalI + 1) + "张", Toast.LENGTH_SHORT).show();
                }
            });
            imageList.add(imageView);
        }
        ImageView hcb = new ImageView(getActivity());
        hcb.setImageResource(R.mipmap.abc_btn_radio_to_on_mtrl_000);
        imageList.add(hcb);
        adapter = new MyPagerAdapter(imageList);
        viewPager.setAdapter(adapter);
        //给viewpager设置滑动监听
        viewPager.setOnPageChangeListener(this);


    }

    //动态添加小圆点在广告栏底部
    protected void addPoint() {
        points = new ImageView[urlList.size()];
        ImageView imageView;
        for (int i = 0; i < points.length; i++) {
            imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            params.setMargins(10, 0, 0, 0);
            imageView.setLayoutParams(params);
            points[i] = imageView;
            if (i == 0) {
                points[i].setBackgroundResource(R.mipmap.abc_btn_radio_to_on_mtrl_015);
            } else {
                points[i].setBackgroundResource(R.mipmap.abc_btn_radio_to_on_mtrl_000);
            }
            rgPoints.addView(points[i]);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //显示当前图片
    @Override
    public void onPageSelected(int position) {
        viewPager.setCurrentItem(position);
        if (position == 6) {
            position = 0;
            viewPager.setCurrentItem(position);
        }
        //小圆点默认状态是为选中，然后再设置第一张为选中状态，顺序一定不可以反了。
        for (int i = 0; i < points.length; i++) {
            points[i].setBackgroundResource(R.mipmap.abc_btn_radio_to_on_mtrl_000);
        }
        points[position].setBackgroundResource(R.mipmap.abc_btn_radio_to_on_mtrl_015);
        Log.w("PPPPPPP", position + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
