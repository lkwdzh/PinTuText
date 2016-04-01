package com.aglook.pintutext.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by aglook on 2016/4/1.
 */
public class GridItemsAdapter extends BaseAdapter {
    private List<Bitmap> mBitmapItemLists;
    private Context context;

    public GridItemsAdapter(List<Bitmap> mBitmapItemLists, Context context) {
        this.mBitmapItemLists = mBitmapItemLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mBitmapItemLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mBitmapItemLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_pic_item = null;
        if (convertView == null) {
            iv_pic_item = new ImageView(context);
            //设置不就图片
            iv_pic_item.setLayoutParams(new GridView.LayoutParams(
                    mBitmapItemLists.get(position).getWidth(),
                    mBitmapItemLists.get(position).getHeight()
            ));
            //设置显示比例类型
            iv_pic_item.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else {
            iv_pic_item=(ImageView)convertView;
        }
        iv_pic_item.setImageBitmap(mBitmapItemLists.get(position));
        return iv_pic_item;
    }
}
