package com.aglook.pintutext.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.aglook.pintutext.R;
import com.aglook.pintutext.activity.SecondActivity;
import com.aglook.pintutext.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2016/3/31.
 * <p/>
 * 图像工具类：实现图像的分割与自适应
 */
public class ImagesUtil {

    public ItemBean itemBean;

    /**
     * 切图、初始状态（正常顺序）
     *
     * @param type
     * @param picSelected
     * @param context
     */
    public void createInitBitmaps(int type, Bitmap picSelected, Context context) {
        Bitmap bitmap = null;
        List<Bitmap> bitmapItems = new ArrayList<>();
        //每个Item的宽高
        int itemWidth = picSelected.getWidth() / type;
        int itemHeight = picSelected.getHeight() / type;
        for (int i = 1; i <= type; i++) {
            for (int j = 1; j <= type; j++) {
                bitmap = Bitmap.createBitmap(picSelected,
                        (j - 1) * itemWidth,
                        (i - 1) * itemHeight,
                        itemWidth,
                        itemHeight);
                bitmapItems.add(bitmap);
                itemBean = new ItemBean(
                        (i - 1) * type + j,
                        (i - 1) * type + j,
                        bitmap);
                GameUtil.mItemBeans.add(itemBean);
            }
        }
        //保存最后一个图片在拼图完成时填充
        SecondActivity.mLastBitmap = bitmapItems.get(type * type - 1);
        //设置最后一个为空Item
        bitmapItems.remove(type * type - 1);
        GameUtil.mItemBeans.remove(type * type - 1);
        Bitmap blankBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blank);
        blankBitmap = Bitmap.createBitmap(
                blankBitmap, 0, 0, itemWidth, itemHeight);

        bitmapItems.add(blankBitmap);
        GameUtil.mItemBeans.add(new ItemBean(type * type, 0, blankBitmap));
        GameUtil.mBlankItemBean = GameUtil.mItemBeans.get(type * type - 1);

    }

    /**
     * 处理图片 放大、缩小到合适位置
     * @param newWidth
     * @param newHeight
     * @param bitmap
     * @return
     */
    public Bitmap resizeBitmap(float newWidth, float newHeight, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth / bitmap.getWidth(),
                newHeight / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(
                bitmap, 0, 0,
                bitmap.getWidth(),
                bitmap.getHeight(),
                matrix, true
        );
        return newBitmap;
    }
}
