package com.strivestay.coordinatorlayoutdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * CoordinatorLayout、AppbarLayout、CollapsingToolbarLayout实现酷炫标题栏示例
 *
 * @author StriveStay
 * @date 2018/1/10
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 设置折叠标题栏背景图片
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {
                setPic(iv);
            }
        });
    }

    /**
     * 图片压缩
     * @param imageView
     */
    public void setPic(ImageView imageView) {
        // 获取imageview控件的宽高
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        if (targetW == 0 || targetH == 0) {
            imageView.measure(0, 0);
            targetW = imageView.getMeasuredWidth();
            targetH = imageView.getMeasuredHeight();
        }

        // 获取图片的宽高
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.drawable.girl,bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // 获得最小压缩比例
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // 解析为 bitmap
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl,bmOptions);

        imageView.setImageBitmap(bitmap);
    }
}
