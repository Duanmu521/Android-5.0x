package com.strivestay.viewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
/**
 * Recyclerview、SwipeRefrenshLayout、Cardview示例
 *
 * @author StriveStay
 * @date 2018/1/10
 */
public class MainActivity extends AppCompatActivity {

    private List<String> mDatas;
    private SimpleAdapter mAdapter;
    private RecyclerView mRecyclerView;

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 是否正在刷新
            if(mSwipeRefreshLayout.isRefreshing()){
                // 停止刷新
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
    }

    private void initData() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(""+(char)i);
            L.e(i+"=="+(char)i);
        }
    }

    private void initView() {

        initRecyclerview();

        initSwipeRefreshLayout();


    }

    private void initSwipeRefreshLayout() {
        // 获取swiperefreshlayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_layout);
        // 设置进度条颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 设置背景色
//        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.black);

        // 刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDatas.add(1,"刷新");
//                mDatas.add(2,"刷新2");

                // onCreateViewHolder(),onBindViewHolder()创建一个item，插入 1 位置，而原先 1 位置的item在视图结构上向后移一位，但取值会取position==1位置的数据
                mAdapter.notifyItemInserted(1);

                // 同上，插入多个item；一般情况我们是加载更多，加载到数据最后面，不用担心插入到中间位置，其他item没有重新走onBindViewHolder,还是取原先position位置的数据
//                mAdapter.notifyItemRangeInserted(1,2);

                // 直接改变该位置的视图
//                mAdapter.notifyItemChanged(1);

                // 刷新数据，复用item布局，重新onBindViewHolder()
                mAdapter.notifyDataSetChanged();

                mHandler.sendEmptyMessageDelayed(0,1000);

            }
        });
    }

    private void initRecyclerview() {
        // 获取recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 创建布局管理器，实现listview效果
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4, OrientationHelper.HORIZONTAL,false);
        //        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 确定item的大小是固定的，设置为true，recyclerview可以得到优化
        mRecyclerView.setHasFixedSize(true);
        // 添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // 设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 适配器
        mAdapter = new SimpleAdapter(this,mDatas);
        // 设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                mDatas.add(1,"新增条目");
                mAdapter.notifyDataSetChanged(); // 没有动画效果
                break;
            case R.id.menu_delete:
                mDatas.remove(1);
                mAdapter.notifyItemRemoved(1);
                break;
        }

        return true;
    }
}
