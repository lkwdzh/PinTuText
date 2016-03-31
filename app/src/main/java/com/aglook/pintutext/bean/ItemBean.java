package com.aglook.pintutext.bean;

import android.graphics.Bitmap;

/**
 * Created by aglook on 2016/3/31.
 * 拼图Item逻辑实体类：封装逻辑相关属性
 */
public class ItemBean {

    //Item的Id
    private int mItemId;
    //bitmap的Id
    private int mBitmapId;
    //mBitmap
    private Bitmap mBitmap;

    public ItemBean() {
    }

    public ItemBean(int mItemId, int mBitmapId, Bitmap mBitmap) {
        this.mItemId = mItemId;
        this.mBitmapId = mBitmapId;
        this.mBitmap = mBitmap;
    }

    public int getmItemId() {
        return mItemId;
    }

    public void setmItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public int getmBitmapId() {
        return mBitmapId;
    }

    public void setmBitmapId(int mBitmapId) {
        this.mBitmapId = mBitmapId;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "mItemId=" + mItemId +
                ", mBitmapId=" + mBitmapId +
                ", mBitmap=" + mBitmap +
                '}';
    }
}
