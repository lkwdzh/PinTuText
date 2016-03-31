package com.aglook.pintutext.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RelativeLayout.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.aglook.pintutext.R;
import com.aglook.pintutext.util.ImagesUtil;
import com.aglook.pintutext.util.ScreenUtil;

public class SecondActivity extends AppCompatActivity {

    private int mResId;
    private String mPicPath;
    //拼图完成时显示的最后一个图片
    public static Bitmap mLastBitmap;

    //设置N * N
    public static int TYPE = 2;

    //选择的图片
    private Bitmap mPicSelected;
    private Button mBtnBack;
    private Button mBtnImage;
    private Button mBtnRestart;
    private boolean mIsShowImg;
    private GridView mGvPuzzleMainDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //获取选择的图片
        Bitmap picSelectedTemp;
        //选择默认图片还是自定义图片
        mResId = getIntent().getExtras().getInt("picSelectedID");
        mPicPath = getIntent().getExtras().getString("mPicPath");
        if (mResId != 0) {
            picSelectedTemp = BitmapFactory.decodeResource(getResources(), mResId);
        } else {
            picSelectedTemp = BitmapFactory.decodeFile(mPicPath);
        }
        TYPE = getIntent().getExtras().getInt("mType", 2);
        //对图片处理
        handlerImage(picSelectedTemp);
        //初始化view


    }


    private void handlerImage(Bitmap bitmap) {
        //将图片放大到固定尺寸
        int screenWidth = ScreenUtil.getScreenSize(SecondActivity.this).widthPixels;
        int screenHeigt = ScreenUtil.getScreenSize(SecondActivity.this).heightPixels;
        mPicSelected = new ImagesUtil().resizeBitmap(
                screenWidth * 0.8f, screenHeigt * 0.6f, bitmap
        );
    }


    /**
     * 初始化
     */
    private void initViews(){
        mBtnBack = (Button) findViewById(R.id.btn_puzzle_main_back);
        mBtnImage = (Button) findViewById(R.id.btn_puzzle_main_img);
        mBtnRestart = (Button) findViewById(R.id.btn_puzzle_main_restart);
        //Flag是否已显示原图
        mIsShowImg = false;
        mGvPuzzleMainDetail = (GridView) findViewById(R.id.gv_puzzle_main_detail);
        //设置为N*N显示
        mGvPuzzleMainDetail.setNumColumns(TYPE);
        LayoutParams gridParams = new LayoutParams(
                mPicSelected.getWidth(),
                mPicSelected.getHeight());
        //水平居中
        gridParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

    }

}
