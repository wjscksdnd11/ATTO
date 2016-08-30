package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.atto.developers.atto.fragment.CheckLogoutDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPageSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_setting);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.key_setting_profile)
    public void onSettingProfile() {
        Intent intent = new Intent(MyPageSettingActivity.this, MyPageSetProfileActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.key_change_password)
    public void onChangePassword() {
        Intent intent = new Intent(MyPageSettingActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.key_logout)
    public void onLogOut() {

        CheckLogoutDialogFragment dialogFragment = new CheckLogoutDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "logout");
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_mypage_setting);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
