package com.strivestay.toolbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Fragment 中使用 Toolbar 示例
 * @author StriveStay
 * @date 2017/12/14
 */
public class ToolbarFragment extends Fragment {

    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("tag","onCreateView===========toolbarFragment");
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // 替换Activity中的 Actionbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tag","onCreate===========toolbarFragment");
        // 通知Activity会创建 optionmenu
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.e("tag","onCreateOptionsMenu============ToolbarFragment");
        menu.clear();
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //            case android.R.id.home:
            //                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
            //                break;
            case R.id.menu_menu:
                Toast.makeText(getActivity(), "菜单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_home:
                Toast.makeText(getActivity(), "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_self:
                Toast.makeText(getActivity(), "个人中心", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
