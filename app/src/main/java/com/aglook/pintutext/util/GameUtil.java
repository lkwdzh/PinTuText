package com.aglook.pintutext.util;

import com.aglook.pintutext.activity.SecondActivity;
import com.aglook.pintutext.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2016/3/31.
 * 拼图工具类：实现拼图的交换与生成算法
 */
public class GameUtil {

    //游戏信息单元格Bean
    public static List<ItemBean> mItemBeans = new ArrayList<>();
    //空格单元格
    public static ItemBean mBlankItemBean = new ItemBean();


    /**
     * 判断点击的item是否可以移动
     *
     * @param position
     * @return
     */
    public static boolean isMoveAble(int position) {
        int type = SecondActivity.TYPE;
        //获取空格Item
        int blankId = GameUtil.mBlankItemBean.getmItemId() - 1;
        //不同行，相差为type;
        if (Math.abs(blankId - position) == type) {
            return true;
        }
        //相同行，相差为1
        if ((blankId / type == position / type) && Math.abs(blankId - position) == 1) {
            return true;
        }
        return false;
    }


    /**
     * 交换空格与点击Item的位置
     *
     * @param from
     * @param blank
     */
    public static void swapItems(ItemBean from, ItemBean blank) {
        ItemBean tempItemBean = new ItemBean();
        //交换BitmapId
        tempItemBean.setmBitmapId(from.getmBitmapId());
        from.setmBitmapId(blank.getmBitmapId());
        blank.setmBitmapId(tempItemBean.getmBitmapId());
        //交换Bitmap
        tempItemBean.setmBitmap(from.getmBitmap());
        from.setmBitmap(blank.getmBitmap());
        blank.setmBitmap(tempItemBean.getmBitmap());
        //设置新的Blank
        GameUtil.mBlankItemBean = from;
    }

    /**
     * 生成随机的Item
     */
    public static void getPuzzleGenerator() {
        int index = 0;
        //随机打乱顺序
        for (int i = 0; i < mItemBeans.size(); i++) {
            index = (int) (Math.random() * SecondActivity.TYPE * SecondActivity.TYPE);
            swapItems(mItemBeans.get(index), GameUtil.mBlankItemBean);
        }
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < mItemBeans.size(); i++) {
            data.add(mItemBeans.get(i).getmBitmapId());
        }

        //判断生成是否有解
        if (canSolve(data)) {
            return;
        } else {
            getPuzzleGenerator();
        }
    }

    /**
     * 是否拼图成功
     *
     * @return
     */
    public static boolean isSuccess() {
        for (ItemBean tempBean :
                GameUtil.mItemBeans) {
            if (tempBean.getmBitmapId() != 0 && (tempBean.getmItemId() == tempBean.getmBitmapId())) {
                continue;
            } else if (tempBean.getmBitmapId() == 0 && tempBean.getmItemId() == SecondActivity.TYPE * SecondActivity.TYPE) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该数据是否有解
     *
     * @param data
     * @return
     */
    public static boolean canSolve(List<Integer> data) {
        //获取空格Id
        int blankId = GameUtil.mBlankItemBean.getmItemId();
        //可行性原则
        if (data.size() % 2 == 1) {
            return getInversions(data) % 2 == 0;
        } else {
            //从底往上数，空格位于奇数行
            if (((blankId - 1) / SecondActivity.TYPE) % 2 == 1) {
                return getInversions(data) % 2 == 0;
            } else {
                //从底往上数，空格位于偶数行
                return getInversions(data) % 2 == 1;
            }
        }
    }

    /**
     * 计算倒置和算法
     *
     * @param data 拼图数组数据
     * @return
     */
    public static int getInversions(List<Integer> data) {
        int inversions = 0;
        int inversionCount = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int index = data.get(i);
                if (data.get(j) != 0 && data.get(j) < index) {
                    inversionCount++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }

}
