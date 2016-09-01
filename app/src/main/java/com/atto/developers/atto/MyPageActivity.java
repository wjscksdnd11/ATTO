package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.ProgressDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.userdata.MyProfile;
import com.atto.developers.atto.request.MyProfileRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class MyPageActivity extends AppCompatActivity {

    @BindView(R.id.text_mypage_nickname)
    TextView nickNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        ButterKnife.bind(this);
        initData();
        initToolBar();

    }


    @OnClick(R.id.text_mypage_more_trade)
    void onTradeViewClick() {
        Intent intent = new Intent(MyPageActivity.this, MyPageMoreTradeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_mypage_setting_myprofile)
    void onMyPageSettingClick() {
        Intent intent = new Intent(MyPageActivity.this, MyPageSetProfileActivity.class);
        startActivity(intent);
    }

    Intent intent;

    // 제작자일 때만 생기는 페이지
    @OnClick({R.id.btn_footer_move_maker_info, R.id.btn_footer_move_maker_nego, R.id.btn_footer_move_accept_wait})
    void onMovePage(View view) {
        switch (view.getId()) {
            case R.id.btn_footer_move_maker_info:
                intent = new Intent(MyPageActivity.this, MakerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_footer_move_maker_nego:
                intent = new Intent(MyPageActivity.this, MakerNegoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_footer_move_accept_wait:
                intent = new Intent(MyPageActivity.this, AcceptWaitActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_my_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            Intent intent = new Intent(MyPageActivity.this, MyPageSettingActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_my_page);
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

    private void initData() {
        final ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "progress");

        MyProfileRequest request = new MyProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<MyProfile>() {
            @Override
            public void onSuccess(NetworkRequest<MyProfile> request, MyProfile result) {

                String nickname = result.getData().getMember_alias();
                nickNameView.setText(nickname);
                dialogFragment.dismiss();
                Toast.makeText(MyPageActivity.this, "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail(NetworkRequest<MyProfile> request, int errorCode, String errorMessage, Throwable e) {
                Log.e("error", request + " , " + errorCode + " , " + errorMessage);

                dialogFragment.dismiss();
                Toast.makeText(MyPageActivity.this, "fail" + errorCode, Toast.LENGTH_SHORT).show();
            }


        });
    }
}