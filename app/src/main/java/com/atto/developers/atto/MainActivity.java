package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView)toolbar.findViewById(R.id.text_title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btn = (ImageButton)toolbar.findViewById(R.id.ic_mypage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });

        btn = (ImageButton)toolbar.findViewById(R.id.ic_notification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoticeMainActivity.class);
                startActivity(intent);
            }
        });

        btn = (ImageButton)toolbar.findViewById(R.id.ic_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UnifiedSearchActivity.class);
                startActivity(intent);
            }
        });

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getString(R.string.main_tab_one)),
                AttoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getString(R.string.main_tab_realtrade)),
                RealTimeTradeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getString(R.string.main_tab_maker)),
                MakerFragment.class, null);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.text_title:
                Toast.makeText(this, "ATTO", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }*/
}
