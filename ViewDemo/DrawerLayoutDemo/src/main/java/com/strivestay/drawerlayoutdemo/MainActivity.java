package com.strivestay.drawerlayoutdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.strivestay.drawerlayoutdemo.R.id.fab;

/**
 * DrawerLayout、NavigationView、FloatingActionButton、SnakeBar示例
 *
 * @author StriveStay
 * @date 2018/1/10
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置折叠标题栏背景图片
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {
                setPic(iv);
            }
        });

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawerlayout
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 开关设置，与toolbar的navigationIcon绑定作为开关按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 侧滑菜单，Navigationview
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        // 默认选中
        nav.setCheckedItem(R.id.nav_camera);
        // 去除NavigationView中Scrollbar；xml中使用android:scrollbars="none"无效
        NavigationMenuView navigationMenuView = (NavigationMenuView) nav.getChildAt(0);
        navigationMenuView.setVerticalScrollBarEnabled(false);
        // NavigationView中menu点击事件
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // fab
        mFab = (FloatingActionButton) findViewById(fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Snackbar.make(mFab,"点击了FloatingActionBar",2000)
                //                        .setActionTextColor(Color.RED)  // 默认红色
                //                        .setAction("ok", new View.OnClickListener() {
                //                            @Override
                //                            public void onClick(View v) {
                //                                Toast.makeText(MainActivity.this,"点击了Action按钮",Toast.LENGTH_SHORT).show();
                //                            }
                //                        })
                //                        .show();
                customSnackbar();
            }
        });
    }

    /**
     * 自定义snackbar
     */
    private void customSnackbar() {
        Snackbar snackbar = Snackbar.make(mFab, "点击了FloatingActionBar", 2000)
                .setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO
                    }
                });
        // 获取snackbar
        View view = snackbar.getView();
        // 提示文字的Textview
        TextView snackbarText = (TextView) view.findViewById(R.id.snackbar_text);
        // Action所在button
        Button snackbarAction = (Button) view.findViewById(R.id.snackbar_action);
        // 设置背景
        view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        // 提示文字颜色
        snackbarText.setTextColor(Color.BLACK);
        // Action颜色
        snackbarAction.setTextColor(Color.BLACK);

        snackbar.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //            case android.R.id.home:
            //                mDrawerLayout.openDrawer(GravityCompat.START);
            //                break;
        }

        return true;
    }


    public void setPic(ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        if (targetW == 0 || targetH == 0) {
            imageView.measure(0, 0);
            targetW = imageView.getMeasuredWidth();
            targetH = imageView.getMeasuredHeight();
        }

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.girl, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl, bmOptions);

        imageView.setImageBitmap(bitmap);
    }
}
