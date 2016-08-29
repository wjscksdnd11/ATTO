package com.atto.developers.atto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.AttoFragment;
import com.atto.developers.atto.fragment.MakerFragment;
import com.atto.developers.atto.fragment.RealTimeTradeFragment;


public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ImageButton btn = (ImageButton) toolbar.findViewById(R.id.ic_mypage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
            }
        });

        btn = (ImageButton) toolbar.findViewById(R.id.ic_notification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoticeMainActivity.class);
                startActivity(intent);
            }
        });

        btn = (ImageButton) toolbar.findViewById(R.id.ic_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UnifiedSearchActivity.class);
                startActivity(intent);
            }
        });

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabIndicator(this, getString(R.string.main_tab_one))),
                AttoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabIndicator(this, getString(R.string.main_tab_realtrade))),
                RealTimeTradeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabIndicator(this, getString(R.string.main_tab_maker))),
                MakerFragment.class, null);

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

    private View getTabIndicator(Context context, String title) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(title);
        return view;
    }
}
