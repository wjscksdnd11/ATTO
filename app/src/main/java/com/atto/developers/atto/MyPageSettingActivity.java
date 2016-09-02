package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.fragment.CheckLogoutDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.MemberLeaveRequest;

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

    @OnClick(R.id.key_change_address)
    public void onChangeAddress() {
        Intent intent = new Intent(MyPageSettingActivity.this, SearchAddressActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.key_alert_setting)
    public void onAlertSetting() {

    }

    @OnClick(R.id.key_register_maker)
    public void onRegisterMaker() {
        Intent intent = new Intent(MyPageSettingActivity.this, RegisterMakerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.key_leave_atto)
    public void onLeaveAtto() {
        Intent intent = new Intent(MyPageSettingActivity.this, AccountLeaveActivity.class);
        startActivity(intent);

        MemberLeaveRequest request = new MemberLeaveRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                Toast.makeText(MyPageSettingActivity.this, "성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyPageSettingActivity.this, "실패" + errorCode, Toast.LENGTH_SHORT).show();

            }
        });


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
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_grey);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void startIntent() {

        Intent intent = new Intent(MyPageSettingActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}
