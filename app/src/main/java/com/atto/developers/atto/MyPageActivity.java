package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class MyPageActivity extends AppCompatActivity {

    TextView titleView;
    TextView moreTradeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ImageButton imageBtn = (ImageButton) toolbar.findViewById(R.id.ic_back);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageBtn = (ImageButton) toolbar.findViewById(R.id.ic_mypage_setting);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyPageSettingActivity.class);
                startActivity(intent);
            }
        });

        titleView = (TextView) toolbar.findViewById(R.id.text_title);
        titleView.setText(R.string.activity_my_page);
        View headerView = findViewById(R.id.mypage_header);

        // My 거래 페이지로 이동
        moreTradeView = (TextView) headerView.findViewById(R.id.text_mypage_more_trade);
        moreTradeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyPageMoreTradeActivity.class);
                startActivity(intent);
            }
        });

        // 설정 페이지로 이동
        Button btn = (Button) headerView.findViewById(R.id.btn_mypage_setting_myprofile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyPageSetProfileActivity.class);
                startActivity(intent);
            }
        });


        // 제작자일때만 생기는 페이지
        View footerView = findViewById(R.id.view_mypage_maker_footerview);
        btn = (Button) footerView.findViewById(R.id.btn_footer_move_maker_info);
        // 제작자 등록 정보
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MakerInfoActivity.class);
                startActivity(intent);
            }
        });
        btn = (Button) footerView.findViewById(R.id.btn_footer_move_maker_nego);
        // 협상중인 거래
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MakerNegoActivity.class);
                startActivity(intent);
            }
        });
        btn = (Button) footerView.findViewById(R.id.btn_footer_move_accept_wait);

        // 수락대기 중인 거래
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, AcceptWaitActivity.class);
                startActivity(intent);
            }
        });
    }
}