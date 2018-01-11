package com.strivestay.toolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Toolbar、ActionMenu示例
 *
 * @author StriveStay
 * @date 2018/1/10
 */
public class MainActivity extends AppCompatActivity {

    private ActionMenuView mAmv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除Actionbar的一种方式
        //        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 替换 actionbar
        setSupportActionBar(toolbar);

        // 不显示原标题，自定义标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //        toolbar.setTitle("");

        // 显示左侧图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 自定义左侧图标
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        //        toolbar.setNavigationIcon(R.drawable.arrow_left);

        // 左侧图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("tag", "onCreateOptionsMenu=============");

        //        getMenuInflater().inflate(R.menu.menu,menu);

        mAmv = (ActionMenuView) findViewById(R.id.amv);

        // 将 menu 内容，放到 ActionMenuView中
        getMenuInflater().inflate(R.menu.menu, mAmv.getMenu());

        // ActionMenuView 中 menuItem 点击事件
        mAmv.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //            case android.R.id.home:
            //                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
            //                break;
            case R.id.menu_menu:
                Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_home:
                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_self:
                Toast.makeText(this, "个人中心", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 先走onCreateOptionsMenu,再走这个方法
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e("tag", "onPrepareOptionsMenu=============");
        menu = mAmv.getMenu();
        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void jump(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
