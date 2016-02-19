package com.qf1514.kuaikan;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 2016/2/10.
 */
public class MyApplication extends Application {
    public static RequestQueue requestQueue;
    public static BitmapUtils bitmapUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        bitmapUtils = new BitmapUtils(this);
    }
}
