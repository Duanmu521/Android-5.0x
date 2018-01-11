package com.strivestay.toolbardemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ToolbarFragment fragment = new ToolbarFragment();
        transaction.add(R.id.fragment,fragment);
        transaction.commit();
        Log.e("tag","oncreate==========SecondActivity");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("tag","onCreateOptionsMenu==========SecondActivity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false; // 返回true，则fragment中onOptionsItemSelected无效
    }
}
