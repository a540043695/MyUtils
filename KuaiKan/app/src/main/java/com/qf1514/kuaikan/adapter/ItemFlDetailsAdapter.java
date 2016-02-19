package com.qf1514.kuaikan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf1514.kuaikan.MyApplication;
import com.qf1514.kuaikan.R;
import com.qf1514.kuaikan.bean.FlDetails;

public class ItemFlDetailsAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemFlDetailsAdapter(Context context, List<T> list) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        objects = list;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_fl_details, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.ivItemFldetailImg = (ImageView) convertView.findViewById(R.id.iv_item_fldetail_img);
            viewHolder.biaoti = (TextView) convertView.findViewById(R.id.tv_item_fldetail_biaoti);
            viewHolder.zuozhe = (TextView) convertView.findViewById(R.id.tv_item_fldetail_zuozhe);
            viewHolder.dianzanshu = (TextView) convertView.findViewById(R.id.tv_item_fldetail_dzs);
            viewHolder.pinglunshu = (TextView) convertView.findViewById(R.id.tv_item_fldetail_pls);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        FlDetails.DataEntity.TopicsEntity topicsEntity = (FlDetails.DataEntity.TopicsEntity) object;
        holder.biaoti.setText(topicsEntity.getTitle());
        holder.zuozhe.setText(topicsEntity.getUser().getNickname());
        holder.dianzanshu.setText(topicsEntity.getLikes_count() + "");
        holder.pinglunshu.setText(topicsEntity.getComments_count() + "");
        MyApplication.bitmapUtils.display(holder.ivItemFldetailImg, topicsEntity.getCover_image_url());
    }

    protected class ViewHolder {
        private ImageView ivItemFldetailImg;
        private TextView biaoti, zuozhe, dianzanshu, pinglunshu;
    }
}
