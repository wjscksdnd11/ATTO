package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);
        ButterKnife.bind(this);
        initToolBar();

    }

    @OnClick(R.id.text_add_trade_register_trade)
    public void onTradeRegister() {
        Intent intent = new Intent(AddTradeActivity.this, DetailTradeActivity.class);
        startActivity(intent);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_addtrade);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView textView = (TextView)toolbar.findViewById(R.id.text_title);
        textView.setText(R.string.activity_addtrade);

        Button btn = (Button)findViewById(R.id.text_add_trade_register_trade);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTradeActivity.this, DetailTradeActivity.class);
                startActivity(intent);

            }
        });
    }
}
