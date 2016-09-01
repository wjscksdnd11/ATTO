package com.atto.developers.atto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.adapter.MyPagerAdapter;
import com.atto.developers.atto.networkdata.ResultMessage;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

//    FragmentTabHost tabHost;
    ResultMessage rm;

    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.pager) ViewPager pager;

    MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
/*

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabIndicator(this, getString(R.string.main_tab_one))),
                AttoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabIndicator(this, getString(R.string.main_tab_realtrade))),
                RealTimeTradeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabIndicator(this, getString(R.string.main_tab_maker))),
                MakerFragment.class, null);
*/
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, R.drawable.tab1_selector)));
        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, R.drawable.tab2_selector)));
        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, R.drawable.tab3_selector)));

/*        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, getString(R.string.main_tab_one))));
        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, getString(R.string.main_tab_realtrade))));
        tabs.addTab(tabs.newTab().setCustomView(getTabIndicator(this, getString(R.string.main_tab_maker))));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_noti) {
            Intent intent = new Intent(MainActivity.this, NoticeMainActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, UnifiedSearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
    }
/*
    private View getTabIndicator(Context context, String title, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(title);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(res);
        return view;
    }
    */

    private View getTabIndicator(Context context, int res) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(res);
        return view;
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        TextView titleView = (TextView)findViewById(R.id.toolbar_title);
        titleView.setText("atto");

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_account_circle_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
            }
        });

    }
}
