package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ImageButton imageBtn = (ImageButton)toolbar.findViewById(R.id.ic_back);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView textView = (TextView)toolbar.findViewById(R.id.text_title);
        textView.setText(R.string.activity_my_page);

        View headerView = findViewById(R.id.mypage_header);
        Button btn = (Button)headerView.findViewById(R.id.btn_mypage_setting_myprofile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyPageSettingActivity.class);
                startActivity(intent);

            }
        });

        View footerView = findViewById(R.id.view_mypage_maker_footerview);
        btn = (Button)footerView.findViewById(R.id.btn_footer_move_maker_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MakerInfoActivity.class);
                startActivity(intent);
            }
        });
        btn = (Button)footerView.findViewById(R.id.btn_footer_move_maker_nego);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MakerNegoActivity.class);
                startActivity(intent);
            }
        });
        btn = (Button)footerView.findViewById(R.id.btn_footer_move_accept_wait);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, AcceptWaitActivity.class);
                startActivity(intent);
            }
        });
    }
}
