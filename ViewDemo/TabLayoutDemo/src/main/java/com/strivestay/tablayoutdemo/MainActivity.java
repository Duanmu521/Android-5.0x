package com.strivestay.tablayoutdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TabLayout示例
 * @author StriveStay
 * @date 2018/1/10
 */
public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // tablayout，Tab是TabLayout的内部类，且Tab的构造方法是包访问权限
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // tab通过设置自定义view，实现图文混排
        tabLayout.addTab(tabLayout.newTab().setCustomView(setCustomView(R.drawable.ic_home,"首页")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setCustomView(R.drawable.ic_info,"资讯")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setCustomView(R.drawable.ic_live,"直播")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setCustomView(R.drawable.ic_me,"我")));

        // spannableString设置图文混排
//        tabLayout.addTab(tabLayout.newTab().setText(setImageSpan("Tab1",R.drawable.ic_home)));
//        tabLayout.addTab(tabLayout.newTab().setText(setImageSpan("Tab2",R.drawable.ic_info)));
//        tabLayout.addTab(tabLayout.newTab().setText(setImageSpan("Tab3",R.drawable.ic_live)));
//        tabLayout.addTab(tabLayout.newTab().setText(setImageSpan("Tab4",R.drawable.ic_me)));

        // vp
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // 绑定，要在viewpager设置完数据后，调用此方法，否则不显示 tabs文本
        tabLayout.setupWithViewPager(mViewPager);

        // 为绑定viewpager后的TabLayout的tabs设置自定义view
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(mSectionsPagerAdapter.setCustomView(i));
        }

    }

    /**
     * tab设置自定义view
     * @param drawableId
     * @param tabText
     * @return
     */
    private View setCustomView(int drawableId,String tabText) {
        View view = View.inflate(this, R.layout.item_tab, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        iv.setImageResource(drawableId);
        tv.setText(tabText);
        return view;
    }

    /**
     * 通过SpannableString设置图文混排
     * @param string
     * @param drawableId
     * @return
     */
    @NonNull
    private SpannableString setImageSpan(String string,int drawableId) {
        SpannableString ss = new SpannableString("  "+string);
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        CenterImageSpan imageSpan = new CenterImageSpan(drawable);
        ss.setSpan(imageSpan,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * fragment
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION = "section";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(String section) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION, section);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_SECTION));
            return rootView;
        }
    }

    /**
     * pagerAdapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String[] tabs = {"首页","资讯","直播","我"};
        int[] imgs = {R.drawable.ic_home,R.drawable.ic_info,R.drawable.ic_live,R.drawable.ic_me};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(tabs[position]);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return tabs[position];
//            return setImageSpan(tabs[position],imgs[position]);
            return null;
        }

        /**
         * 设置自定义view
         * @param position
         * @return
         */
        public View setCustomView(int position) {
            View view = View.inflate(getApplicationContext(), R.layout.item_tab, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            iv.setImageResource(imgs[position]);
            tv.setText(tabs[position]);
            return view;
        }
    }
}
